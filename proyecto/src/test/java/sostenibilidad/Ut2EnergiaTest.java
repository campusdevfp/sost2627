package sostenibilidad;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UT2 · Retos ambientales: la huella de la electricidad")
class Ut2EnergiaTest {

    // Día con viento: 300 MWh eólicos, 200 de ciclo combinado, 500 nucleares
    private static final String[] TEC_DIA1 = {"Eólica", "Ciclo combinado", "Nuclear"};
    private static final double[] MWH_DIA1 = {300, 200, 500};

    // Día sin viento: 100 MWh de carbón, 300 de ciclo combinado, 100 solares
    private static final String[] TEC_DIA2 = {"Carbón", "Ciclo combinado", "Solar fotovoltaica"};
    private static final double[] MWH_DIA2 = {100, 300, 100};

    @Test
    @DisplayName("E1 · factor de emisión de cada tecnología")
    void factorEmision() {
        assertEquals(0.95, Ut2Energia.factorEmision("Carbón"), 1e-9);
        assertEquals(0.37, Ut2Energia.factorEmision("ciclo combinado"), 1e-9);
        assertEquals(0.38, Ut2Energia.factorEmision("COGENERACIÓN"), 1e-9);
        assertEquals(0.0, Ut2Energia.factorEmision("Nuclear"), 1e-9);
        assertEquals(0.0, Ut2Energia.factorEmision("Tecnología inventada"), 1e-9);
    }

    @Test
    @DisplayName("E2 · reconoce las tecnologías renovables")
    void esRenovable() {
        assertTrue(Ut2Energia.esRenovable("Eólica"));
        assertTrue(Ut2Energia.esRenovable("HIDRÁULICA"));
        assertFalse(Ut2Energia.esRenovable("Nuclear"));
        assertFalse(Ut2Energia.esRenovable("Carbón"));
    }

    @Test
    @DisplayName("E3 · toneladas de CO2 emitidas")
    void toneladas() {
        assertEquals(74.0, Ut2Energia.toneladas(TEC_DIA1, MWH_DIA1), 1e-9);
        assertEquals(206.0, Ut2Energia.toneladas(TEC_DIA2, MWH_DIA2), 1e-9);
        assertEquals(0.0, Ut2Energia.toneladas(new String[]{"Eólica"}, new double[]{500}), 1e-9);
    }

    @Test
    @DisplayName("E4 · intensidad de carbono en g CO2/kWh")
    void intensidad() {
        assertEquals(74.0, Ut2Energia.intensidad(TEC_DIA1, MWH_DIA1), 1e-9);
        assertEquals(412.0, Ut2Energia.intensidad(TEC_DIA2, MWH_DIA2), 1e-9);
        assertEquals(0.0, Ut2Energia.intensidad(new String[]{}, new double[]{}), 1e-9);
    }

    @Test
    @DisplayName("E5 · porcentaje renovable")
    void porcentajeRenovable() {
        assertEquals(30.0, Ut2Energia.porcentajeRenovable(TEC_DIA1, MWH_DIA1), 1e-9);
        assertEquals(20.0, Ut2Energia.porcentajeRenovable(TEC_DIA2, MWH_DIA2), 1e-9);
        assertEquals(0.0, Ut2Energia.porcentajeRenovable(new String[]{}, new double[]{}), 1e-9);
    }

    @ParameterizedTest(name = "E6 · {0} g/kWh -> {1}")
    @CsvSource({"0,VERDE", "99.9,VERDE", "100,AMBAR", "199.9,AMBAR", "200,ROJO", "412,ROJO"})
    void semaforoRed(double intensidad, String esperado) {
        assertEquals(esperado, Ut2Energia.semaforoRed(intensidad));
    }

    @Test
    @DisplayName("E6 · intensidad negativa lanza excepción")
    void semaforoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> Ut2Energia.semaforoRed(-5));
    }

    @Test
    @DisplayName("E7 · kilos de CO2 de un consumo")
    void kgCo2() {
        assertEquals(30.0, Ut2Energia.kgCo2(250, 120), 1e-9);
        assertEquals(0.41, Ut2Energia.kgCo2(3.3, 123.4), 1e-9);
        assertEquals(0.0, Ut2Energia.kgCo2(0, 400), 1e-9);
    }

    @Test
    @DisplayName("E8 · día más limpio de la semana")
    void diaMasLimpio() {
        assertEquals(5, Ut2Energia.diaMasLimpio(new double[]{72.1, 83.8, 113.0, 131.9, 104.8, 64.4, 78.8}));
        assertEquals(0, Ut2Energia.diaMasLimpio(new double[]{50.0, 50.0, 90.0}));   // empate: el primero
        assertEquals(-1, Ut2Energia.diaMasLimpio(new double[]{}));
    }
}
