package simulacro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Simulacro RA2")
class SimulacroRa2Test {

    @Test @DisplayName("1 · factor normalizando el nombre")
    void factor() {
        assertEquals(0.90, SimulacroRa2.factorEmision("CARBÓN"), 1e-9);
        assertEquals(0.60, SimulacroRa2.factorEmision("  Turbina de gas "), 1e-9);
        assertEquals(0.40, SimulacroRa2.factorEmision("Ciclo Combinado"), 1e-9);
    }

    @Test @DisplayName("1 · tecnología que no está en la tabla")
    void factorDesconocido() {
        assertEquals(0.0, SimulacroRa2.factorEmision("Eólica"), 1e-9);
        assertEquals(0.0, SimulacroRa2.factorEmision("Cogeneración"), 1e-9);
    }

    @Test @DisplayName("2 · intensidad de un día")
    void intensidad() {
        // 250 x 0,40 = 100 t en 1000 MWh
        assertEquals(100.0, SimulacroRa2.intensidad(
                new String[]{"Eólica", "Ciclo combinado", "Nuclear"},
                new double[]{250, 250, 500}), 1e-9);
    }

    @Test @DisplayName("2 · intensidad con varias tecnologías que emiten")
    void intensidadMixta() {
        // 100 x 0,90 + 200 x 0,60 = 210 t en 700 MWh -> 300 g/kWh
        assertEquals(300.0, SimulacroRa2.intensidad(
                new String[]{"Carbón", "Turbina de gas", "Solar fotovoltaica"},
                new double[]{100, 200, 400}), 1e-9);
    }

    @Test @DisplayName("2 · sin generación devuelve 0")
    void intensidadVacia() {
        assertEquals(0.0, SimulacroRa2.intensidad(new String[]{}, new double[]{}), 1e-9);
    }

    @Test @DisplayName("3 · kg de CO2 con 1 decimal")
    void kg() {
        assertEquals(30.0, SimulacroRa2.kgCo2(250, 120), 1e-9);
        assertEquals(0.4, SimulacroRa2.kgCo2(3.3, 123.4), 1e-9);
    }

    @Test @DisplayName("3 · valores negativos lanzan excepción")
    void kgNegativo() {
        assertThrows(IllegalArgumentException.class, () -> SimulacroRa2.kgCo2(-1, 100));
        assertThrows(IllegalArgumentException.class, () -> SimulacroRa2.kgCo2(10, -100));
    }
}
