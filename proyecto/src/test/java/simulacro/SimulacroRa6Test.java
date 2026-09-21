package simulacro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Simulacro RA6")
class SimulacroRa6Test {

    @ParameterizedTest(name = "1 · influencia {0}, interés {1} -> {2}")
    @CsvSource(delimiter = '|', value = {
            "5|5|GESTIONAR DE CERCA", "4|4|GESTIONAR DE CERCA", "3|5|MANTENER INFORMADO",
            "5|3|MANTENER SATISFECHO", "3|3|MONITORIZAR"})
    void estrategia(int inf, int intr, String esperada) {
        assertEquals(esperada, SimulacroRa6.estrategiaGrupo(inf, intr));
    }

    @Test @DisplayName("1 · valores fuera de escala")
    void estrategiaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> SimulacroRa6.estrategiaGrupo(6, 1));
        assertThrows(IllegalArgumentException.class, () -> SimulacroRa6.estrategiaGrupo(2, 0));
    }

    @Test @DisplayName("2 · prioridad como media de las tres")
    void prioridad() {
        assertEquals(4.8, SimulacroRa6.prioridad(5, 5, 4.5), 1e-9);    // 4,83
        assertEquals(3.0, SimulacroRa6.prioridad(4, 2, 3), 1e-9);
        assertEquals(1.3, SimulacroRa6.prioridad(1, 1, 2), 1e-9);      // 1,33
    }

    @ParameterizedTest(name = "3 · progreso {0} -> {1}")
    @CsvSource({"1.0,COMPLETADA", "0.75,CASI", "0.74,EN MARCHA", "0.25,EN MARCHA",
                "0.24,INICIADA", "0.01,INICIADA", "0.0,SIN EMPEZAR"})
    void estado(double p, String esperado) {
        assertEquals(esperado, SimulacroRa6.estado(p));
    }
}
