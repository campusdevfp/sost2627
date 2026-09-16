package sostenibilidad;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UT5 · Huella de carbono del software")
class Ut5HuellaTest {

    @Test
    @DisplayName("E1 · energía de una transferencia")
    void kwhTransferencia() {
        assertEquals(0.81, Ut5Huella.kwhTransferencia(1_000_000_000L), 1e-9);
        assertEquals(0.00162, Ut5Huella.kwhTransferencia(2_000_000L), 1e-12);
        assertEquals(0.0, Ut5Huella.kwhTransferencia(0), 1e-12);
    }

    @Test
    @DisplayName("E2 · gramos de CO2 de una respuesta")
    void gramosCo2() {
        assertEquals(0.243, Ut5Huella.gramosCo2(2_000_000L, 150), 1e-9);
        assertEquals(0.0333, Ut5Huella.gramosCo2(274_000L, 150), 1e-4);
    }

    @Test
    @DisplayName("E3 · kg de CO2 al año de un endpoint")
    void kgAnuales() {
        assertEquals(121.5, Ut5Huella.kgAnuales(274_000L, 10_000L, 150), 1e-9);
        assertEquals(0.0, Ut5Huella.kgAnuales(0L, 10_000L, 150), 1e-9);
    }

    @Test
    @DisplayName("E4 · cálculo del SCI")
    void sci() {
        assertEquals(2.0, Ut5Huella.sci(10, 200, 0, 1000), 1e-9);
        assertEquals(0.5, Ut5Huella.sci(5, 150, 4250, 10_000), 1e-9);
        assertThrows(IllegalArgumentException.class, () -> Ut5Huella.sci(5, 150, 0, 0));
    }

    @Test
    @DisplayName("E5 · reducción porcentual entre dos medidas")
    void reduccionPorcentual() {
        assertEquals(75.0, Ut5Huella.reduccionPorcentual(480_000, 120_000), 1e-9);
        assertEquals(33.3, Ut5Huella.reduccionPorcentual(3, 2), 1e-9);
        assertEquals(-50.0, Ut5Huella.reduccionPorcentual(100, 150), 1e-9);
        assertThrows(IllegalArgumentException.class, () -> Ut5Huella.reduccionPorcentual(0, 10));
    }

    @ParameterizedTest(name = "E6 · {0} consultas para {1} elementos -> {2}")
    @CsvSource({"2001,2000,true", "21,20,true", "3,2,true", "2,2,false", "2,1,false", "50,2000,false"})
    void hayNMasUno(long consultas, int elementos, boolean esperado) {
        assertEquals(esperado, Ut5Huella.hayNMasUno(consultas, elementos));
    }

    @Test
    @DisplayName("E7 · mejor hora dentro de una ventana")
    void mejorHora() {
        double[] prevision = new double[24];
        for (int h = 0; h < 24; h++) prevision[h] = 200 - h;      // va bajando
        prevision[14] = 55;                                       // la más limpia del día
        prevision[3] = 150;
        assertEquals(14, Ut5Huella.mejorHora(prevision, 0, 23));
        assertEquals(3, Ut5Huella.mejorHora(prevision, 0, 6));     // la 14 queda fuera de la ventana
        assertEquals(-1, Ut5Huella.mejorHora(prevision, 10, 5));   // rango no válido
    }

    @Test
    @DisplayName("E7 · en caso de empate gana la hora más temprana")
    void mejorHoraEmpate() {
        double[] prevision = new double[24];
        java.util.Arrays.fill(prevision, 100);
        prevision[9] = 80;
        prevision[10] = 80;
        assertEquals(9, Ut5Huella.mejorHora(prevision, 0, 23));
    }

    @ParameterizedTest(name = "E8 · {0} g -> {1}")
    @CsvSource({"0.05,A", "0.1,A", "0.15,B", "0.2,B", "0.3,C", "0.8,D", "1.2,E", "1.61,F"})
    void etiqueta(double gramos, String esperada) {
        assertEquals(esperada, Ut5Huella.etiqueta(gramos));
    }
}
