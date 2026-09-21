package simulacro;

/**
 * SIMULACRO DE EXAMEN · RA1 (UT1) · 40 minutos
 *
 * Mismo formato que el examen: 3 métodos, reglas parecidas a las de clase
 * pero NO iguales. Lee el Javadoc con atención.
 *
 * mvn test -Dtest=SimulacroRa1Test
 */
public class SimulacroRa1 {

    /**
     * 1. Grado de cumplimiento de un indicador, entre 0.0 y 1.0.
     * Si mayorEsMejor: valor / meta, con tope 1.0 (meta 0 -> 1.0).
     * Si no: 1.0 cuando valor <= meta; en otro caso meta / valor.
     */
    public static double cumplimiento(double valor, double meta, boolean mayorEsMejor) {
        throw new UnsupportedOperationException("TODO 1");
    }

    /**
     * 2. Puntuación ASG global con estos PESOS:
     * ambiental x 0.3, social x 0.3, gobernanza x 0.4. Redondea a 1 decimal.
     */
    public static double puntuacionAsg(double ambiental, double social, double gobernanza) {
        throw new UnsupportedOperationException("TODO 2");
    }

    /**
     * 3. Nivel de madurez de la empresa según su puntuación:
     * >= 75 "LIDER", >= 50 "AVANZADO", >= 25 "INICIAL", resto "REZAGADO".
     * Lanza IllegalArgumentException si la puntuación no está entre 0 y 100.
     */
    public static String nivel(double puntuacion) {
        throw new UnsupportedOperationException("TODO 3");
    }
}
