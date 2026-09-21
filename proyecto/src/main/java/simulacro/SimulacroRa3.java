package simulacro;

/**
 * SIMULACRO DE EXAMEN · RA3 (UT3) · 40 minutos
 *
 * mvn test -Dtest=SimulacroRa3Test
 */
public class SimulacroRa3 {

    /**
     * 1. Indica si merece la pena comprimir un tipo de contenido.
     * Son comprimibles los que empiezan por "text/" y además "application/json"
     * y "application/javascript". En este simulacro "image/svg+xml" NO cuenta.
     * Ignora lo que venga tras ';' y las mayúsculas. Un tipo nulo devuelve false.
     */
    public static boolean esComprimible(String tipoContenido) {
        throw new UnsupportedOperationException("TODO 1");
    }

    /**
     * 2. Puntuación de un recurso, de 0 a 100. Parte de 100 y resta:
     *   25 si es comprimible y no viene comprimido,
     *   25 si su caché dura MENOS DE UNA HORA (maxAgeSegundos < 3600),
     *   25 si pesa más de 1000000 bytes.
     */
    public static int puntuacionRecurso(String tipoContenido, long bytes,
                                        boolean vieneComprimido, long maxAgeSegundos) {
        throw new UnsupportedOperationException("TODO 2");
    }

    /**
     * 3. Kg de CO2 al año de ir y volver al trabajo, teniendo en cuenta el TELETRABAJO,
     * redondeados a 1 decimal:
     *   factor x kmIda x 2 x (diasPorSemana - diasTeletrabajo) x semanas
     * Lanza IllegalArgumentException si algún valor es negativo o si
     * diasTeletrabajo es mayor que diasPorSemana.
     */
    public static double kgDesplazamiento(double kgCo2PorKm, double kmIda, int diasPorSemana,
                                          int diasTeletrabajo, int semanas) {
        throw new UnsupportedOperationException("TODO 3");
    }
}
