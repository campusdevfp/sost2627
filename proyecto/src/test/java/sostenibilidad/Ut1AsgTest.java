package sostenibilidad;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UT1 · Sostenibilidad, ODS y criterios ASG")
class Ut1AsgTest {

    @Test
    @DisplayName("E1 · clasifica temas en su dimensión ASG")
    void clasifica() {
        assertEquals("AMBIENTAL", Ut1Asg.clasificar("Emisiones del centro de datos"));
        assertEquals("SOCIAL", Ut1Asg.clasificar("HORAS DE FORMACIÓN POR PERSONA"));
        assertEquals("GOBERNANZA", Ut1Asg.clasificar("Canal ético de denuncias"));
        assertEquals("SIN CLASIFICAR", Ut1Asg.clasificar("Color del logotipo"));
        assertEquals("SIN CLASIFICAR", Ut1Asg.clasificar("   "));
        assertEquals("SIN CLASIFICAR", Ut1Asg.clasificar(null));
    }

    @Test
    @DisplayName("E2 · cumplimiento cuando mayor es mejor")
    void cumplimientoMayorEsMejor() {
        assertEquals(0.75, Ut1Asg.cumplimiento(60, 80, true), 1e-9);
        assertEquals(1.0, Ut1Asg.cumplimiento(95, 80, true), 1e-9);
        assertEquals(1.0, Ut1Asg.cumplimiento(3, 0, true), 1e-9);
    }

    @Test
    @DisplayName("E2 · cumplimiento cuando menor es mejor")
    void cumplimientoMenorEsMejor() {
        assertEquals(0.8, Ut1Asg.cumplimiento(125, 100, false), 1e-9);
        assertEquals(1.0, Ut1Asg.cumplimiento(4, 5, false), 1e-9);
        assertEquals(0.0, Ut1Asg.cumplimiento(10, 0, false), 1e-9);
    }

    @ParameterizedTest(name = "E3 · cumplimiento {0} -> {1}")
    @CsvSource({"1.0,VERDE", "0.9,VERDE", "0.89,AMBAR", "0.7,AMBAR", "0.69,ROJO", "0.0,ROJO"})
    void semaforo(double cumplimiento, String esperado) {
        assertEquals(esperado, Ut1Asg.semaforo(cumplimiento));
    }

    @Test
    @DisplayName("E3 · cumplimiento fuera de rango lanza excepción")
    void semaforoFueraDeRango() {
        assertThrows(IllegalArgumentException.class, () -> Ut1Asg.semaforo(-0.1));
        assertThrows(IllegalArgumentException.class, () -> Ut1Asg.semaforo(1.5));
    }

    @Test
    @DisplayName("E4 · puntuación de una dimensión")
    void puntuacionDimension() {
        assertEquals(75.0, Ut1Asg.puntuacionDimension(new double[]{0.5, 1.0}), 1e-9);
        assertEquals(54.2, Ut1Asg.puntuacionDimension(new double[]{0.75, 1.0 / 3}), 1e-9);
        assertEquals(0.0, Ut1Asg.puntuacionDimension(new double[]{}), 1e-9);
    }

    @Test
    @DisplayName("E5 · puntuación ASG ponderada")
    void puntuacionAsg() {
        assertEquals(71.0, Ut1Asg.puntuacionAsg(50, 80, 90), 1e-9);
        assertEquals(0.0, Ut1Asg.puntuacionAsg(0, 0, 0), 1e-9);
        assertEquals(73.2, Ut1Asg.puntuacionAsg(73.6, 60.2, 85.5), 1e-9);
    }

    @ParameterizedTest(name = "E6 · {0} -> {1}")
    @CsvSource({"100,AAA", "85,AAA", "84.9,AA", "70,AA", "55,A", "40,BBB", "25,BB", "24.9,B", "0,B"})
    void rating(double puntuacion, String esperado) {
        assertEquals(esperado, Ut1Asg.rating(puntuacion));
    }

    @Test
    @DisplayName("E6 · puntuación fuera de rango lanza excepción")
    void ratingFueraDeRango() {
        assertThrows(IllegalArgumentException.class, () -> Ut1Asg.rating(-1));
        assertThrows(IllegalArgumentException.class, () -> Ut1Asg.rating(100.5));
    }

    @Test
    @DisplayName("E7 · detecta si el indicador ha mejorado")
    void haMejorado() {
        assertTrue(Ut1Asg.haMejorado(65, 72, true));      // renovables suben: mejora
        assertFalse(Ut1Asg.haMejorado(380, 410, false));  // emisiones suben: empeora
        assertTrue(Ut1Asg.haMejorado(410, 380, false));
        assertFalse(Ut1Asg.haMejorado(72, 72, true));     // igual no es mejorar
    }

    @Test
    @DisplayName("E8 · ODS ordenados, sin repetir y sin inválidos")
    void odsCubiertos() {
        assertArrayEquals(new int[]{5, 7, 13, 16},
                Ut1Asg.odsCubiertos(new int[]{13, 7, 5, 13, 99, 16, 0}));
        assertArrayEquals(new int[]{}, Ut1Asg.odsCubiertos(new int[]{0, 18, -2}));
    }
}
