package sostenibilidad;

/**
 * UT1 · Sostenibilidad, ODS y criterios ASG.
 *
 * Completa los ocho métodos. Ejecuta los tests con:  mvn test -Dtest=Ut1AsgTest
 */
public class Ut1Asg {

    /** Palabras que delatan cada dimensión (ya en minúsculas y sin tildes). */
    static final String[] AMBIENTAL = {"emisi", "energ", "agua", "residu", "clim", "carbon", "recicl"};
    static final String[] SOCIAL = {"emple", "formaci", "salud", "igualdad", "accesib", "brecha", "concilia"};
    static final String[] GOBERNANZA = {"etic", "corrupci", "consejo", "transparen", "fiscal", "soborno"};

    /**
     * Método 1. Clasifica un tema en su dimensión ASG.
     * Devuelve "AMBIENTAL", "SOCIAL", "GOBERNANZA" o "SIN CLASIFICAR".
     * Busca las palabras en ese orden. Ignora mayúsculas y tildes: usa {@link #normalizar(String)}.
     * Un texto nulo o vacío devuelve "SIN CLASIFICAR".
     */
    public static String clasificar(String tema) {
        // TODO: normaliza el tema y usa texto.contains(palabra) con los tres arrays
        throw new UnsupportedOperationException("TODO método 1");
    }

    /** Ya está hecho: pasa a minúsculas y quita las tildes. */
    static String normalizar(String texto) {
        String sinTildes = java.text.Normalizer.normalize(texto, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return sinTildes.toLowerCase().trim();
    }

    /**
     * Método 2. Grado de cumplimiento de un indicador, entre 0.0 y 1.0.
     * Si mayorEsMejor: valor / meta, con tope 1.0.
     * Si no: 1.0 cuando valor <= meta; en otro caso meta / valor.
     */
    public static double cumplimiento(double valor, double meta, boolean mayorEsMejor) {
        // TODO
        throw new UnsupportedOperationException("TODO método 2");
    }

    /**
     * Método 3. Semáforo del indicador a partir de su cumplimiento (0.0 a 1.0):
     * "VERDE" desde 0.9, "AMBAR" desde 0.7, "ROJO" por debajo.
     * Lanza IllegalArgumentException si el cumplimiento está fuera de [0, 1].
     */
    public static String semaforo(double cumplimiento) {
        // TODO
        throw new UnsupportedOperationException("TODO método 3");
    }

    /**
     * Método 4. Puntuación de una dimensión, de 0 a 100: media de los cumplimientos
     * multiplicada por 100 y redondeada a 1 decimal. Si el array está vacío, 0.0.
     * Redondeo a 1 decimal: Math.round(x * 10) / 10.0
     */
    public static double puntuacionDimension(double[] cumplimientos) {
        // TODO
        throw new UnsupportedOperationException("TODO método 4");
    }

    /**
     * Método 5. Puntuación ASG global: ambiental x 0.4 + social x 0.3 + gobernanza x 0.3,
     * redondeada a 1 decimal.
     */
    public static double puntuacionAsg(double ambiental, double social, double gobernanza) {
        // TODO
        throw new UnsupportedOperationException("TODO método 5");
    }

    /**
     * Método 6. Rating a partir de la puntuación (0 a 100):
     * >= 85 "AAA", >= 70 "AA", >= 55 "A", >= 40 "BBB", >= 25 "BB", resto "B".
     * Lanza IllegalArgumentException si está fuera de [0, 100].
     */
    public static String rating(double puntuacion) {
        // TODO
        throw new UnsupportedOperationException("TODO método 6");
    }

    /**
     * Método 7. Indica si un indicador ha mejorado respecto al año anterior,
     * teniendo en cuenta si conviene que suba o que baje.
     */
    public static boolean haMejorado(double anterior, double actual, boolean mayorEsMejor) {
        // TODO
        throw new UnsupportedOperationException("TODO método 7");
    }

    /**
     * Método 8. Números de ODS válidos (1 a 17), ordenados de menor a mayor y sin repetir.
     * Los que estén fuera de ese rango se descartan.
     * Pista: java.util.TreeSet, y para devolver el array usa un bucle o stream().mapToInt(...).toArray()
     */
    public static int[] odsCubiertos(int[] numeros) {
        // TODO
        throw new UnsupportedOperationException("TODO método 8");
    }
}
