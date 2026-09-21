package simulacro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Simulacro RA1")
class SimulacroRa1Test {

    @Test @DisplayName("1 · cumplimiento cuando mayor es mejor")
    void mayor() {
        assertEquals(0.45, SimulacroRa1.cumplimiento(45, 100, true), 1e-9);
        assertEquals(1.0, SimulacroRa1.cumplimiento(130, 100, true), 1e-9);
    }

    @Test @DisplayName("1 · cumplimiento cuando menor es mejor")
    void menor() {
        assertEquals(0.8, SimulacroRa1.cumplimiento(500, 400, false), 1e-9);
        assertEquals(1.0, SimulacroRa1.cumplimiento(300, 400, false), 1e-9);
    }

    @Test @DisplayName("1 · meta cero cuando menor es mejor")
    void metaCero() {
        assertEquals(1.0, SimulacroRa1.cumplimiento(0, 0, false), 1e-9);
        assertEquals(0.0, SimulacroRa1.cumplimiento(7, 0, false), 1e-9);
    }

    @Test @DisplayName("2 · puntuación ASG con pesos 0,3 / 0,3 / 0,4")
    void asg() {
        assertEquals(71.0, SimulacroRa1.puntuacionAsg(50, 80, 80), 1e-9);
        assertEquals(72.3, SimulacroRa1.puntuacionAsg(60.4, 73.8, 80.0), 1e-9);
    }

    @ParameterizedTest(name = "3 · {0} -> {1}")
    @CsvSource({"100,LIDER", "75,LIDER", "74.9,AVANZADO", "50,AVANZADO",
                "49.9,INICIAL", "25,INICIAL", "24.9,REZAGADO", "0,REZAGADO"})
    void nivel(double p, String esperado) {
        assertEquals(esperado, SimulacroRa1.nivel(p));
    }

    @Test @DisplayName("3 · fuera de rango lanza excepción")
    void nivelFueraDeRango() {
        assertThrows(IllegalArgumentException.class, () -> SimulacroRa1.nivel(-0.1));
        assertThrows(IllegalArgumentException.class, () -> SimulacroRa1.nivel(100.1));
    }
}
