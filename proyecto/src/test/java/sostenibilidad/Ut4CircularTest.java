package sostenibilidad;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UT4 · Economía circular y ecodiseño")
class Ut4CircularTest {

    @ParameterizedTest(name = "M1 · {0} -> {1} = {2}")
    @CsvSource({
            "EN_USO,EN_REPARACION,true", "EN_USO,REACONDICIONADO,true",
            "EN_USO,DONADO,true", "EN_USO,RECICLADO,true", "EN_USO,EN_USO,false",
            "EN_REPARACION,EN_USO,true", "EN_REPARACION,RECICLADO,true", "EN_REPARACION,DONADO,false",
            "REACONDICIONADO,EN_USO,true", "REACONDICIONADO,DONADO,true", "REACONDICIONADO,RECICLADO,false",
            "DONADO,EN_USO,false", "RECICLADO,EN_USO,false", "RECICLADO,REACONDICIONADO,false"})
    void transiciones(String desde, String hasta, boolean esperado) {
        assertEquals(esperado, Ut4Circular.transicionPermitida(desde, hasta));
    }

    @Test
    @DisplayName("M2 · cambia el estado si la transición es válida")
    void cambioValido() {
        assertEquals("EN_REPARACION", Ut4Circular.cambiarEstado("EN_USO", "EN_REPARACION"));
    }

    @Test
    @DisplayName("M2 · transición no permitida lanza IllegalStateException con los dos estados")
    void cambioInvalido() {
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> Ut4Circular.cambiarEstado("RECICLADO", "EN_USO"));
        assertTrue(e.getMessage().contains("RECICLADO"), "el mensaje debe citar el estado de origen");
        assertTrue(e.getMessage().contains("EN_USO"), "el mensaje debe citar el estado de destino");
    }

    @Test
    @DisplayName("M3 · huella anual del equipo")
    void huellaAnual() {
        assertEquals(87.0, Ut4Circular.huellaAnual(300, 12, 4), 1e-9);
        assertEquals(161.7, Ut4Circular.huellaAnual(350, 45, 3), 1e-9);
        assertThrows(IllegalArgumentException.class, () -> Ut4Circular.huellaAnual(300, 12, 0));
    }

    @Test
    @DisplayName("M4 · ahorro anual al alargar la vida")
    void ahorroAlargarVida() {
        assertEquals(25.0, Ut4Circular.ahorroAlargarVida(300, 4, 2), 1e-9);
        assertEquals(37.3, Ut4Circular.ahorroAlargarVida(280, 3, 2), 1e-9);
        assertEquals(0.0, Ut4Circular.ahorroAlargarVida(280, 3, 0), 1e-9);
        assertThrows(IllegalArgumentException.class, () -> Ut4Circular.ahorroAlargarVida(300, 0, 2));
        assertThrows(IllegalArgumentException.class, () -> Ut4Circular.ahorroAlargarVida(300, 4, -1));
    }

    @Test
    @DisplayName("M5 · qué parte de la huella es la fabricación")
    void porcentajeFabricacion() {
        assertEquals(86.2, Ut4Circular.porcentajeFabricacion(300, 12, 4), 1e-9);
        assertEquals(56.5, Ut4Circular.porcentajeFabricacion(350, 45, 6), 1e-9);
    }

    @Test
    @DisplayName("M6 · mejor opción de la jerarquía de las R")
    void mejorOpcionR() {
        assertEquals("REPARAR", Ut4Circular.mejorOpcionR(new String[]{"RECICLAR", "REPARAR", "VALORIZAR"}));
        assertEquals("REUTILIZAR", Ut4Circular.mejorOpcionR(new String[]{"REACONDICIONAR", "REUTILIZAR"}));
        assertEquals("ELIMINAR", Ut4Circular.mejorOpcionR(new String[]{}));
        assertThrows(IllegalArgumentException.class, () -> Ut4Circular.mejorOpcionR(new String[]{"TIRAR"}));
    }

    @Test
    @DisplayName("M7 · tasa de circularidad")
    void tasaCircularidad() {
        assertEquals(66.7, Ut4Circular.tasaCircularidad(
                new String[]{"DONADO", "REACONDICIONADO", "RECICLADO", "EN_USO"}), 1e-9);
        assertEquals(50.0, Ut4Circular.tasaCircularidad(
                new String[]{"DONADO", "REACONDICIONADO", "RECICLADO", "RECICLADO"}), 1e-9);
        assertEquals(0.0, Ut4Circular.tasaCircularidad(new String[]{"EN_USO", "EN_REPARACION"}), 1e-9);
    }

    @Test
    @DisplayName("M8 · años en amortizar un equipo nuevo")
    void aniosParaAmortizar() {
        assertEquals(3, Ut4Circular.aniosParaAmortizar(900, 300));
        assertEquals(70, Ut4Circular.aniosParaAmortizar(280, 4));
        assertEquals(4, Ut4Circular.aniosParaAmortizar(1000, 300));   // 3.33 -> 4
        assertEquals(-1, Ut4Circular.aniosParaAmortizar(280, 0));
    }
}
