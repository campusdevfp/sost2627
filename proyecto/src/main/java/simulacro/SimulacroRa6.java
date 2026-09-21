package simulacro;

/**
 * SIMULACRO DE EXAMEN · RA6 (UT6) · 40 minutos
 *
 * mvn test -Dtest=SimulacroRa6Test
 */
public class SimulacroRa6 {

    /**
     * 1. Estrategia con un grupo de interés. En este simulacro "alto" significa 4 O MÁS:
     *   influencia alta e interés alto -> "GESTIONAR DE CERCA"
     *   influencia alta e interés bajo -> "MANTENER SATISFECHO"
     *   influencia baja e interés alto -> "MANTENER INFORMADO"
     *   ambos bajos                    -> "MONITORIZAR"
     * Lanza IllegalArgumentException si algún valor no está entre 1 y 5.
     */
    public static String estrategiaGrupo(int influencia, int interes) {
        throw new UnsupportedOperationException("TODO 1");
    }

    /**
     * 2. Prioridad de un aspecto con una FÓRMULA NUEVA: media de las tres valoraciones
     * (impacto, financiero e importancia para los grupos), redondeada a 1 decimal.
     */
    public static double prioridad(double impacto, double financiero, double importanciaGrupos) {
        throw new UnsupportedOperationException("TODO 2");
    }

    /**
     * 3. Estado de una acción con CINCO tramos:
     * >= 1.0 "COMPLETADA", >= 0.75 "CASI", >= 0.25 "EN MARCHA", > 0 "INICIADA", 0 "SIN EMPEZAR".
     */
    public static String estado(double progreso) {
        throw new UnsupportedOperationException("TODO 3");
    }
}
