package simulacro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Simulacro RA4")
class SimulacroRa4Test {

    @Test @DisplayName("1 · huella anual redondeada al entero")
    void huella() {
        assertEquals(87L, SimulacroRa4.huellaAnual(300, 12, 4));
        assertEquals(162L, SimulacroRa4.huellaAnual(350, 45, 3));    // 161,67
        assertEquals(55L, SimulacroRa4.huellaAnual(300, 12, 7));     // 54,86
    }

    @Test @DisplayName("1 · años no positivos lanzan excepción")
    void huellaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> SimulacroRa4.huellaAnual(300, 12, 0));
    }

    @ParameterizedTest(name = "2 · {0} -> {1} = {2}")
    @CsvSource({"EN_USO,EN_REPARACION,true", "EN_USO,RETIRADO,true", "EN_USO,EN_USO,false",
                "EN_REPARACION,EN_USO,true", "EN_REPARACION,RETIRADO,true",
                "RETIRADO,EN_USO,false", "RETIRADO,EN_REPARACION,false", "DONADO,EN_USO,false"})
    void transiciones(String desde, String hasta, boolean esperado) {
        assertEquals(esperado, SimulacroRa4.transicionPermitida(desde, hasta));
    }

    @Test @DisplayName("3 · tasa de circularidad con vertedero")
    void tasa() {
        assertEquals(50.0, SimulacroRa4.tasaCircularidad(
                new String[]{"DONADO", "REACONDICIONADO", "RECICLADO", "VERTEDERO", "EN_USO"}), 1e-9);
        assertEquals(33.3, SimulacroRa4.tasaCircularidad(
                new String[]{"DONADO", "VERTEDERO", "VERTEDERO"}), 1e-9);
    }

    @Test @DisplayName("3 · sin retirados devuelve 0")
    void tasaVacia() {
        assertEquals(0.0, SimulacroRa4.tasaCircularidad(new String[]{"EN_USO", "EN_REPARACION"}), 1e-9);
    }
}
