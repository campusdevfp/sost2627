package es.daw.sostenibilidad.auditoria.servicio;

import es.daw.sostenibilidad.auditoria.modelo.Hallazgo;
import es.daw.sostenibilidad.auditoria.modelo.Recurso;
import es.daw.sostenibilidad.auditoria.modelo.Transporte;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Tests del auditor web sostenible (RA3). Son la especificación: no se modifican. */
class AuditoriaServiceTest {

    private final AuditoriaService servicio = new AuditoriaService();

    private static final String UN_ANIO = "public, max-age=31536000, immutable";

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "text/html; charset=UTF-8|true",
            "Text/CSS|true",
            "application/json|true",
            "application/javascript|true",
            "image/svg+xml|true",
            "image/png|false",
            "video/mp4|false"})
    void detectaTiposComprimibles(String tipo, boolean esperado) {
        assertEquals(esperado, servicio.esComprimible(tipo));
    }

    @Test
    void tipoNuloNoEsComprimible() {
        assertFalse(servicio.esComprimible(null));
    }

    @Test
    void detectaSiUsaCompresion() {
        assertTrue(servicio.usaCompresion(new Recurso("/a.css", "text/css", 900, "gzip", UN_ANIO)));
        assertTrue(servicio.usaCompresion(new Recurso("/a.js", "application/javascript", 900, "BR", UN_ANIO)));
        assertFalse(servicio.usaCompresion(new Recurso("/a.js", "application/javascript", 900, null, UN_ANIO)));
        assertFalse(servicio.usaCompresion(new Recurso("/a.js", "application/javascript", 900, "identity", UN_ANIO)));
    }

    @Test
    void leeElMaxAgeDeCacheControl() {
        assertEquals(31536000, servicio.maxAge(UN_ANIO));
        assertEquals(3600, servicio.maxAge("max-age=3600"));
        assertEquals(0, servicio.maxAge("no-store, max-age=3600"));
        assertEquals(0, servicio.maxAge("no-cache"));
        assertEquals(0, servicio.maxAge(null));
    }

    @Test
    void recursoBienServidoNoTieneHallazgos() {
        Recurso css = new Recurso("/estilo.css", "text/css", 12_000, "br", UN_ANIO);
        assertEquals(List.of(), servicio.auditar(css));
    }

    @Test
    void jsonGrandeSinCompresionNiCache() {
        Recurso json = new Recurso("/api/catalogo", "application/json", 650_000, null, null);
        assertEquals(List.of(Hallazgo.SIN_COMPRESION, Hallazgo.SIN_CACHE, Hallazgo.DEMASIADO_PESADO),
                servicio.auditar(json));
    }

    @Test
    void bmpSiempreEsFormatoIneficiente() {
        Recurso bmp = new Recurso("/logo.bmp", "image/bmp", 40_000, null, UN_ANIO);
        assertEquals(List.of(Hallazgo.FORMATO_IMAGEN_INEFICIENTE), servicio.auditar(bmp));
    }

    @Test
    void pngPequenoEsAceptablePeroPngGrandeNo() {
        Recurso icono = new Recurso("/icono.png", "image/png", 3_000, null, UN_ANIO);
        Recurso foto = new Recurso("/foto.png", "image/png", 850_000, null, UN_ANIO);
        assertEquals(List.of(), servicio.auditar(icono));
        assertEquals(List.of(Hallazgo.DEMASIADO_PESADO, Hallazgo.FORMATO_IMAGEN_INEFICIENTE), servicio.auditar(foto));
    }

    @Test
    void puntuacionRestaLasPenalizaciones() {
        List<Recurso> pagina = List.of(
                new Recurso("/", "text/html", 20_000, null, null),              // -10 -5
                new Recurso("/estilo.css", "text/css", 8_000, "gzip", UN_ANIO),  // 0
                new Recurso("/foto.png", "image/png", 850_000, null, UN_ANIO));  // -15 -10
        assertEquals(60, servicio.puntuacion(pagina));
        assertEquals(100, servicio.puntuacion(List.of()));
    }

    @Test
    void puntuacionNuncaBajaDeCero() {
        Recurso malo = new Recurso("/api/todo", "application/json", 900_000, null, null); // -30
        assertEquals(0, servicio.puntuacion(List.of(malo, malo, malo, malo)));
    }

    @Test
    void pesoTotal() {
        List<Recurso> pagina = List.of(
                new Recurso("/", "text/html", 20_000, null, null),
                new Recurso("/foto.png", "image/png", 850_000, null, UN_ANIO));
        assertEquals(870_000L, servicio.pesoTotal(pagina));
    }

    @Test
    void huellaDeIrAlTrabajoEnCoche() {
        // 0.16 x 15 km x 2 x 5 días x 40 semanas = 960.0 kg
        assertEquals(960.0, servicio.kgCo2Desplazamiento(Transporte.COCHE, 15, 5, 40), 1e-9);
        assertEquals(0.0, servicio.kgCo2Desplazamiento(Transporte.BICI, 15, 5, 40), 1e-9);
    }

    @Test
    void huellaRedondeadaAUnDecimal() {
        // 0.03 x 7.3 x 2 x 3 x 11 = 14.454 -> 14.5
        assertEquals(14.5, servicio.kgCo2Desplazamiento(Transporte.METRO, 7.3, 3, 11), 1e-9);
    }

    @Test
    void desplazamientoConValoresNegativosLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> servicio.kgCo2Desplazamiento(Transporte.COCHE, -1, 5, 40));
        assertThrows(IllegalArgumentException.class,
                () -> servicio.kgCo2Desplazamiento(Transporte.COCHE, 10, -5, 40));
    }

    @Test
    void ahorroAlCambiarDeTransporte() {
        // coche 960.0 - tren 180.0 = 780.0
        assertEquals(780.0, servicio.ahorroCambioTransporte(Transporte.COCHE, Transporte.TREN, 15, 5, 40), 1e-9);
        assertEquals(-180.0, servicio.ahorroCambioTransporte(Transporte.A_PIE, Transporte.METRO, 15, 5, 40), 1e-9);
    }
}
