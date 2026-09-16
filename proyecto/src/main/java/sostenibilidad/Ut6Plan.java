package sostenibilidad;

/**
 * UT6 · Plan de sostenibilidad de una empresa.
 *
 * Completa los ocho métodos. Ejecuta los tests con:  mvn test -Dtest=Ut6PlanTest
 */
public class Ut6Plan {

    /** A partir de este valor (escala 1 a 5) se considera alto. */
    public static final int ALTO = 3;

    /**
     * Ejercicio 1. Estrategia con un grupo de interés según la matriz de poder e interés:
     *   influencia alta e interés alto -> "GESTIONAR DE CERCA"
     *   influencia alta e interés bajo -> "MANTENER SATISFECHO"
     *   influencia baja e interés alto -> "MANTENER INFORMADO"
     *   ambos bajos                    -> "MONITORIZAR"
     * Lanza IllegalArgumentException si algún valor no está entre 1 y 5.
     */
    public static String estrategiaGrupo(int influencia, int interes) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 1");
    }

    /**
     * Ejercicio 2. Doble materialidad: un aspecto es material si su impacto O su
     * materialidad financiera alcanzan el umbral.
     */
    public static boolean esMaterial(double impacto, double financiero, double umbral) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 2");
    }

    /**
     * Ejercicio 3. Tipo de materialidad: "AMBAS", "IMPACTO", "FINANCIERA" o "NO MATERIAL".
     */
    public static String tipoMaterialidad(double impacto, double financiero, double umbral) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 3");
    }

    /**
     * Ejercicio 4. Prioridad de un aspecto, redondeada a 1 decimal:
     * media entre la mayor de sus dos materialidades y la importancia que le dan los grupos.
     */
    public static double prioridad(double impacto, double financiero, double importanciaGrupos) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 4");
    }

    /**
     * Ejercicio 5. Progreso de una acción, entre 0.0 y 1.0 y redondeado a 2 decimales:
     * (actual - base) / (meta - base), acotado. Funciona igual si la meta sube o baja.
     * Lanza IllegalArgumentException si la meta es igual a la línea base.
     */
    public static double progreso(double lineaBase, double meta, double valorActual) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 5");
    }

    /**
     * Ejercicio 6. Estado de una acción según su progreso:
     * >= 1.0 "COMPLETADA", >= 0.5 "AVANZADA", > 0 "INICIADA", 0 "SIN EMPEZAR".
     */
    public static String estado(double progreso) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 6");
    }

    /**
     * Ejercicio 7. Indica si una acción va en plazo: su progreso es al menos la
     * fracción de tiempo ya consumida (meses transcurridos / meses totales, con tope 1).
     * Lanza IllegalArgumentException si los meses totales no son positivos.
     */
    public static boolean vaEnPlazo(double progreso, int mesesTranscurridos, int mesesTotales) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 7");
    }

    /**
     * Ejercicio 8. Nombres de los aspectos que no tienen ninguna acción, en el mismo orden
     * en que llegan. La comparación no distingue mayúsculas (equalsIgnoreCase).
     * Pista: cuenta primero cuántos faltan o usa java.util.ArrayList y luego toArray(new String[0]).
     */
    public static String[] aspectosSinAccion(String[] aspectos, String[] aspectosConAccion) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 8");
    }
}
