package sostenibilidad;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UT6 · Plan de sostenibilidad de una empresa")
class Ut6PlanTest {

    @ParameterizedTest(name = "M1 · influencia {0}, interés {1} -> {2}")
    @CsvSource(delimiter = '|', value = {
            "5|4|GESTIONAR DE CERCA", "3|3|GESTIONAR DE CERCA",
            "5|2|MANTENER SATISFECHO", "2|4|MANTENER INFORMADO", "1|2|MONITORIZAR"})
    void estrategiaGrupo(int influencia, int interes, String esperada) {
        assertEquals(esperada, Ut6Plan.estrategiaGrupo(influencia, interes));
    }

    @Test
    @DisplayName("M1 · valores fuera de la escala 1-5 lanzan excepción")
    void grupoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ut6Plan.estrategiaGrupo(0, 3));
        assertThrows(IllegalArgumentException.class, () -> Ut6Plan.estrategiaGrupo(3, 6));
    }

    @Test
    @DisplayName("M2 · doble materialidad: basta con una de las dos")
    void esMaterial() {
        assertTrue(Ut6Plan.esMaterial(4, 1, 3.5));
        assertTrue(Ut6Plan.esMaterial(1, 4, 3.5));
        assertTrue(Ut6Plan.esMaterial(3.5, 1, 3.5));
        assertFalse(Ut6Plan.esMaterial(3, 3, 3.5));
    }

    @ParameterizedTest(name = "M3 · impacto {0}, financiero {1} -> {2}")
    @CsvSource({"5,5,AMBAS", "4,2,IMPACTO", "2,4,FINANCIERA", "1,1,NO MATERIAL"})
    void tipoMaterialidad(double impacto, double financiero, String esperado) {
        assertEquals(esperado, Ut6Plan.tipoMaterialidad(impacto, financiero, 3.5));
    }

    @Test
    @DisplayName("M4 · prioridad de un aspecto")
    void prioridad() {
        assertEquals(4.8, Ut6Plan.prioridad(4, 5, 4.5), 1e-9);
        assertEquals(2.5, Ut6Plan.prioridad(3, 2, 2), 1e-9);
        assertEquals(5.0, Ut6Plan.prioridad(4, 5, 5), 1e-9);
    }

    @Test
    @DisplayName("M5 · progreso cuando la meta es subir")
    void progresoSube() {
        assertEquals(0.53, Ut6Plan.progreso(40, 100, 72), 1e-9);
        assertEquals(1.0, Ut6Plan.progreso(10, 60, 75), 1e-9);
        assertEquals(0.0, Ut6Plan.progreso(10, 60, 5), 1e-9);
    }

    @Test
    @DisplayName("M5 · progreso cuando la meta es bajar")
    void progresoBaja() {
        assertEquals(0.5, Ut6Plan.progreso(1.8, 1.4, 1.6), 1e-9);
        assertEquals(1.0, Ut6Plan.progreso(1.8, 1.4, 1.3), 1e-9);
        assertThrows(IllegalArgumentException.class, () -> Ut6Plan.progreso(50, 50, 50));
    }

    @ParameterizedTest(name = "M6 · progreso {0} -> {1}")
    @CsvSource({"1.0,COMPLETADA", "0.5,AVANZADA", "0.99,AVANZADA", "0.01,INICIADA", "0.0,SIN EMPEZAR"})
    void estado(double progreso, String esperado) {
        assertEquals(esperado, Ut6Plan.estado(progreso));
    }

    @Test
    @DisplayName("M7 · comprueba si una acción va en plazo")
    void vaEnPlazo() {
        assertTrue(Ut6Plan.vaEnPlazo(0.53, 18, 36));     // 0.53 >= 0.50
        assertFalse(Ut6Plan.vaEnPlazo(0.30, 18, 36));
        assertTrue(Ut6Plan.vaEnPlazo(1.0, 40, 36));      // ya completada, aunque pasado el plazo
        assertThrows(IllegalArgumentException.class, () -> Ut6Plan.vaEnPlazo(0.5, 6, 0));
    }

    @Test
    @DisplayName("M8 · aspectos materiales que no tienen acción")
    void aspectosSinAccion() {
        String[] aspectos = {"Consumo energético", "Agua de refrigeración", "Privacidad de datos"};
        String[] conAccion = {"CONSUMO ENERGÉTICO"};
        assertArrayEquals(new String[]{"Agua de refrigeración", "Privacidad de datos"},
                Ut6Plan.aspectosSinAccion(aspectos, conAccion));
        assertArrayEquals(new String[]{}, Ut6Plan.aspectosSinAccion(new String[]{}, conAccion));
    }
}
