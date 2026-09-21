package simulacro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Simulacro RA5")
class SimulacroRa5Test {

    @Test @DisplayName("1 · energía con el modelo de 0,5 kWh/GB")
    void kwh() {
        assertEquals(0.5, SimulacroRa5.kwhTransferencia(1_000_000_000L), 1e-9);
        assertEquals(0.001, SimulacroRa5.kwhTransferencia(2_000_000L), 1e-12);
        assertEquals(0.0, SimulacroRa5.kwhTransferencia(0L), 1e-12);
    }

    @Test @DisplayName("2 · kg al año solo en días laborables")
    void laborables() {
        // 400 000 bytes -> 0,0002 kWh x 200 g = 0,04 g x 10 000 x 250 = 100 000 g
        assertEquals(100.0, SimulacroRa5.kgAnualesLaborables(400_000L, 10_000L, 200), 1e-9);
    }

    @Test @DisplayName("2 · redondeo a un decimal")
    void laborablesRedondeo() {
        // 274 000 bytes -> 0,000137 kWh x 150 = 0,02055 g x 10 000 x 250 = 51 375 g
        assertEquals(51.4, SimulacroRa5.kgAnualesLaborables(274_000L, 10_000L, 150), 1e-9);
    }

    @ParameterizedTest(name = "3 · {0} consultas, {1} elementos -> {2}")
    @CsvSource({"2000,2000,true", "5,5,true", "4,4,false", "6,5,true",
                "4,5,false", "2,2000,false", "100,3,false"})
    void nMasUno(long consultas, int elementos, boolean esperado) {
        assertEquals(esperado, SimulacroRa5.hayNMasUno(consultas, elementos));
    }
}
