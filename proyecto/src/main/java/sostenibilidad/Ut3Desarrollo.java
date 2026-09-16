package sostenibilidad;

/**
 * UT3 · Sostenibilidad en el trabajo del desarrollador.
 *
 * Completa los ocho métodos. Ejecuta los tests con:  mvn test -Dtest=Ut3DesarrolloTest
 */
public class Ut3Desarrollo {

    /** Medios de transporte y sus kg de CO2 por km y persona (valores didácticos). */
    static final String[] MEDIOS = {"coche", "moto", "autobus", "metro", "tren", "bici", "a pie"};
    static final double[] KG_POR_KM = {0.16, 0.10, 0.08, 0.03, 0.03, 0.0, 0.0};

    /**
     * Ejercicio 1. Indica si merece la pena comprimir un tipo de contenido.
     * Son comprimibles los que empiezan por "text/" y también "application/json",
     * "application/javascript" e "image/svg+xml".
     * Ignora lo que venga después de ';' y las mayúsculas. Un tipo nulo devuelve false.
     */
    public static boolean esComprimible(String tipoContenido) {
        // TODO: tipoContenido.split(";")[0].trim().toLowerCase()
        throw new UnsupportedOperationException("TODO ejercicio 1");
    }

    /**
     * Ejercicio 2. Segundos de max-age de una cabecera Cache-Control.
     * "public, max-age=3600" devuelve 3600. Devuelve 0 si es nula, no tiene max-age
     * o contiene "no-store".
     */
    public static long maxAge(String cacheControl) {
        // TODO: separa por comas con split(","), recorta cada parte y busca "max-age="
        throw new UnsupportedOperationException("TODO ejercicio 2");
    }

    /**
     * Ejercicio 3. Puntuación de sostenibilidad de un recurso, de 0 a 100.
     * Parte de 100 y resta: 30 si es comprimible y no viene comprimido,
     * 20 si su max-age es 0, y 25 si pesa más de 500000 bytes. Nunca baja de 0.
     */
    public static int puntuacionRecurso(String tipoContenido, long bytes,
                                        boolean vieneComprimido, String cacheControl) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 3");
    }

    /** Ejercicio 4. Suma de los bytes de todos los recursos de una página. */
    public static long pesoTotal(long[] bytes) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 4");
    }

    /**
     * Ejercicio 5. Porcentaje de bytes ahorrado al comprimir, redondeado a 1 decimal.
     * Lanza IllegalArgumentException si antes no es mayor que 0.
     */
    public static double reduccion(long antes, long despues) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 5");
    }

    /**
     * Ejercicio 6. Kg de CO2 al año de ir y volver al trabajo, redondeados a 1 decimal.
     * factor x kmIda x 2 x diasPorSemana x semanas.
     * Lanza IllegalArgumentException si el medio no existe o si algún número es negativo.
     */
    public static double kgDesplazamiento(String medio, double kmIda, int diasPorSemana, int semanas) {
        // TODO: busca el medio en MEDIOS (normalizado) para obtener su factor
        throw new UnsupportedOperationException("TODO ejercicio 6");
    }

    /**
     * Ejercicio 7. Kg de CO2 que se ahorran al año cambiando de medio de transporte
     * (negativo si el nuevo contamina más). Redondeado a 1 decimal.
     */
    public static double ahorroCambio(String actual, String nuevo, double kmIda,
                                      int diasPorSemana, int semanas) {
        // TODO: reutiliza kgDesplazamiento
        throw new UnsupportedOperationException("TODO ejercicio 7");
    }

    /**
     * Ejercicio 8. Etiqueta del peso de una página:
     * hasta 500000 bytes "LIGERA", hasta 2000000 "NORMAL", por encima "PESADA".
     */
    public static String etiquetaPeso(long bytes) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 8");
    }
}
