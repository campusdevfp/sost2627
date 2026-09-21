package simulacro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Simulacro RA3")
class SimulacroRa3Test {

    @ParameterizedTest(name = "1 · {0} -> {1}")
    @CsvSource(delimiter = '|', value = {
            "text/html; charset=UTF-8|true", "TEXT/CSS|true", "application/json|true",
            "application/javascript|true", "image/svg+xml|false", "image/webp|false"})
    void comprimible(String tipo, boolean esperado) {
        assertEquals(esperado, SimulacroRa3.esComprimible(tipo));
    }

    @Test @DisplayName("1 · tipo nulo")
    void nulo() {
        assertFalse(SimulacroRa3.esComprimible(null));
    }

    @Test @DisplayName("2 · recurso bien servido")
    void recursoBueno() {
        assertEquals(100, SimulacroRa3.puntuacionRecurso("text/css", 10_000, true, 86_400));
    }

    @Test @DisplayName("2 · caché de menos de una hora penaliza")
    void cacheCorta() {
        assertEquals(75, SimulacroRa3.puntuacionRecurso("text/css", 10_000, true, 600));
        assertEquals(100, SimulacroRa3.puntuacionRecurso("text/css", 10_000, true, 3_600));
    }

    @Test @DisplayName("2 · todas las penalizaciones")
    void todoMal() {
        assertEquals(25, SimulacroRa3.puntuacionRecurso("application/json", 2_000_000, false, 0));
        assertEquals(50, SimulacroRa3.puntuacionRecurso("image/jpeg", 1_500_000, false, 0));
    }

    @Test @DisplayName("3 · desplazamiento con teletrabajo")
    void teletrabajo() {
        // 0,16 x 10 x 2 x (5 - 2) x 40 = 384
        assertEquals(384.0, SimulacroRa3.kgDesplazamiento(0.16, 10, 5, 2, 40), 1e-9);
        assertEquals(0.0, SimulacroRa3.kgDesplazamiento(0.16, 10, 5, 5, 40), 1e-9);
    }

    @Test @DisplayName("3 · datos imposibles lanzan excepción")
    void teletrabajoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> SimulacroRa3.kgDesplazamiento(0.16, 10, 3, 4, 40));
        assertThrows(IllegalArgumentException.class, () -> SimulacroRa3.kgDesplazamiento(0.16, -10, 5, 0, 40));
    }
}
