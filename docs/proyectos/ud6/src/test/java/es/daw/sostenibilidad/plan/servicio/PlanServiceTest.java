package es.daw.sostenibilidad.plan.servicio;

import es.daw.sostenibilidad.plan.modelo.Accion;
import es.daw.sostenibilidad.plan.modelo.AspectoMaterial;
import es.daw.sostenibilidad.plan.modelo.Dimension;
import es.daw.sostenibilidad.plan.modelo.GrupoInteres;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Tests del plan de sostenibilidad (RA6). Son la especificación: no se modifican. */
class PlanServiceTest {

    private final PlanService servicio = new PlanService();

    private static AspectoMaterial aspecto(String nombre, double impacto, double financiero, double grupos) {
        return new AspectoMaterial(nombre, Dimension.AMBIENTAL, impacto, financiero, grupos);
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "5|4|GESTIONAR DE CERCA",
            "3|3|GESTIONAR DE CERCA",
            "5|2|MANTENER SATISFECHO",
            "2|4|MANTENER INFORMADO",
            "1|2|MONITORIZAR"})
    void estrategiaSegunInfluenciaEInteres(int influencia, int interes, String esperada) {
        assertEquals(esperada, servicio.estrategiaGrupo(new GrupoInteres("G", influencia, interes)));
    }

    @Test
    void grupoConValoresFueraDeRangoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> servicio.estrategiaGrupo(new GrupoInteres("G", 0, 3)));
        assertThrows(IllegalArgumentException.class, () -> servicio.estrategiaGrupo(new GrupoInteres("G", 3, 6)));
    }

    @Test
    void dobleMaterialidadBastaConUnaDeLasDos() {
        assertTrue(servicio.esMaterial(aspecto("Solo impacto", 4, 1, 2), 3.5));
        assertTrue(servicio.esMaterial(aspecto("Solo financiero", 1, 4, 2), 3.5));
        assertTrue(servicio.esMaterial(aspecto("En el umbral", 3.5, 1, 2), 3.5));
        assertFalse(servicio.esMaterial(aspecto("Ninguna", 3, 3, 5), 3.5));
    }

    @Test
    void prioridadCombinaMaterialidadYGruposDeInteres() {
        // (max(4, 5) + 4.5) / 2 = 4.75 -> 4.8
        assertEquals(4.8, servicio.prioridad(aspecto("A", 4, 5, 4.5)), 1e-9);
        // (max(3, 2) + 2) / 2 = 2.5
        assertEquals(2.5, servicio.prioridad(aspecto("B", 3, 2, 2)), 1e-9);
    }

    @Test
    void aspectosPrioritariosFiltradosYOrdenados() {
        List<AspectoMaterial> aspectos = List.of(
                aspecto("Residuos electrónicos", 4, 3, 3),        // 3.5
                aspecto("Patrocinio local", 1, 1, 5),             // no material
                aspecto("Privacidad de datos", 4, 5, 5),          // 5.0
                aspecto("Agua de refrigeración", 4, 2, 3),        // 3.5
                aspecto("Consumo energético", 5, 5, 4.5));        // 4.8
        List<String> nombres = servicio.aspectosPrioritarios(aspectos, 3.5).stream()
                .map(AspectoMaterial::nombre).toList();
        assertEquals(List.of("Privacidad de datos", "Consumo energético",
                "Agua de refrigeración", "Residuos electrónicos"), nombres);
    }

    @Test
    void progresoCuandoLaMetaEsSubir() {
        // renovables: de 40 % a 100 %, vamos por 72 % -> 32 / 60 = 0.5333 -> 0.53
        assertEquals(0.53, servicio.progreso(new Accion("E", "d", "i", 40, 100, 72)), 1e-9);
    }

    @Test
    void progresoCuandoLaMetaEsBajar() {
        // PUE: de 1.8 a 1.4, vamos por 1.6 -> 0.5
        assertEquals(0.5, servicio.progreso(new Accion("E", "d", "i", 1.8, 1.4, 1.6)), 1e-9);
    }

    @Test
    void progresoAcotadoEntreCeroYUno() {
        assertEquals(1.0, servicio.progreso(new Accion("E", "d", "i", 10, 60, 75)), 1e-9);
        assertEquals(0.0, servicio.progreso(new Accion("E", "d", "i", 10, 60, 5)), 1e-9);
    }

    @Test
    void progresoConMetaIgualALaBaseLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> servicio.progreso(new Accion("E", "d", "i", 50, 50, 50)));
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {"1.0|COMPLETADA", "0.5|AVANZADA", "0.99|AVANZADA", "0.01|INICIADA", "0.0|SIN EMPEZAR"})
    void estadoSegunProgreso(double progreso, String esperado) {
        assertEquals(esperado, servicio.estado(progreso));
    }

    @Test
    void aspectosSinAccionEnOrdenYSinDistinguirMayusculas() {
        List<AspectoMaterial> materiales = List.of(
                aspecto("Consumo energético", 5, 5, 4.5),
                aspecto("Agua de refrigeración", 4, 2, 3),
                aspecto("Privacidad de datos", 4, 5, 5));
        List<Accion> acciones = List.of(
                new Accion("CONSUMO ENERGÉTICO", "Renovables", "%", 40, 100, 72),
                new Accion("Consumo energético", "Consolidar servidores", "PUE", 1.8, 1.4, 1.6));
        assertEquals(List.of("Agua de refrigeración", "Privacidad de datos"),
                servicio.aspectosSinAccion(materiales, acciones));
    }
}
