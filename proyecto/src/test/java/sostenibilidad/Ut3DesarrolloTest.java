package sostenibilidad;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UT3 · Sostenibilidad en el trabajo del desarrollador")
class Ut3DesarrolloTest {

    private static final String UN_ANIO = "public, max-age=31536000, immutable";

    @ParameterizedTest(name = "E1 · {0} -> {1}")
    @CsvSource(delimiter = '|', value = {
            "text/html; charset=UTF-8|true", "Text/CSS|true", "application/json|true",
            "application/javascript|true", "image/svg+xml|true",
            "image/png|false", "image/jpeg|false", "video/mp4|false"})
    void esComprimible(String tipo, boolean esperado) {
        assertEquals(esperado, Ut3Desarrollo.esComprimible(tipo));
    }

    @Test
    @DisplayName("E1 · un tipo nulo no es comprimible")
    void tipoNulo() {
        assertFalse(Ut3Desarrollo.esComprimible(null));
    }

    @Test
    @DisplayName("E2 · lee el max-age de Cache-Control")
    void maxAge() {
        assertEquals(31536000L, Ut3Desarrollo.maxAge(UN_ANIO));
        assertEquals(3600L, Ut3Desarrollo.maxAge("max-age=3600"));
        assertEquals(0L, Ut3Desarrollo.maxAge("no-store, max-age=3600"));
        assertEquals(0L, Ut3Desarrollo.maxAge("no-cache"));
        assertEquals(0L, Ut3Desarrollo.maxAge(null));
    }

    @Test
    @DisplayName("E3 · puntuación de un recurso bien servido")
    void recursoBueno() {
        assertEquals(100, Ut3Desarrollo.puntuacionRecurso("text/css", 12_000, true, UN_ANIO));
    }

    @Test
    @DisplayName("E3 · puntuación con hallazgos")
    void recursoConProblemas() {
        // JSON grande sin comprimir y sin caché: -30 -20 -25
        assertEquals(25, Ut3Desarrollo.puntuacionRecurso("application/json", 650_000, false, null));
        // PNG pequeño sin caché: solo -20 (no es comprimible)
        assertEquals(80, Ut3Desarrollo.puntuacionRecurso("image/png", 3_000, false, null));
        // Nunca baja de 0
        assertEquals(25, Ut3Desarrollo.puntuacionRecurso("text/html", 900_000, false, "no-store"));
    }

    @Test
    @DisplayName("E4 · peso total de la página")
    void pesoTotal() {
        assertEquals(870_000L, Ut3Desarrollo.pesoTotal(new long[]{20_000, 850_000}));
        assertEquals(0L, Ut3Desarrollo.pesoTotal(new long[]{}));
    }

    @Test
    @DisplayName("E5 · reducción porcentual al comprimir")
    void reduccion() {
        assertEquals(92.9, Ut3Desarrollo.reduccion(366_000, 26_000), 1e-9);
        assertEquals(0.0, Ut3Desarrollo.reduccion(1000, 1000), 1e-9);
        assertThrows(IllegalArgumentException.class, () -> Ut3Desarrollo.reduccion(0, 10));
    }

    @Test
    @DisplayName("E6 · huella anual del desplazamiento")
    void desplazamiento() {
        assertEquals(806.4, Ut3Desarrollo.kgDesplazamiento("coche", 12, 5, 42), 1e-9);
        assertEquals(403.2, Ut3Desarrollo.kgDesplazamiento("AUTOBÚS", 12, 5, 42), 1e-9);
        assertEquals(0.0, Ut3Desarrollo.kgDesplazamiento("bici", 12, 5, 42), 1e-9);
        assertEquals(14.5, Ut3Desarrollo.kgDesplazamiento("metro", 7.3, 3, 11), 1e-9);
    }

    @Test
    @DisplayName("E6 · medio desconocido o datos negativos lanzan excepción")
    void desplazamientoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ut3Desarrollo.kgDesplazamiento("helicóptero", 5, 5, 40));
        assertThrows(IllegalArgumentException.class, () -> Ut3Desarrollo.kgDesplazamiento("coche", -1, 5, 40));
        assertThrows(IllegalArgumentException.class, () -> Ut3Desarrollo.kgDesplazamiento("coche", 10, -5, 40));
    }

    @Test
    @DisplayName("E7 · ahorro al cambiar de transporte")
    void ahorroCambio() {
        assertEquals(403.2, Ut3Desarrollo.ahorroCambio("coche", "autobus", 12, 5, 42), 1e-9);
        assertEquals(806.4, Ut3Desarrollo.ahorroCambio("coche", "bici", 12, 5, 42), 1e-9);
        assertEquals(-403.2, Ut3Desarrollo.ahorroCambio("autobus", "coche", 12, 5, 42), 1e-9);
    }

    @ParameterizedTest(name = "E8 · {0} bytes -> {1}")
    @CsvSource({"120000,LIGERA", "500000,LIGERA", "500001,NORMAL", "2000000,NORMAL", "2000001,PESADA"})
    void etiquetaPeso(long bytes, String esperada) {
        assertEquals(esperada, Ut3Desarrollo.etiquetaPeso(bytes));
    }
}
