package sostenibilidad;

/**
 * UT2 · Retos ambientales y sociales: la huella de la electricidad.
 *
 * Completa los ocho métodos. Ejecuta los tests con:  mvn test -Dtest=Ut2EnergiaTest
 */
public class Ut2Energia {

    /** Tecnologías que emiten CO2 y su factor en toneladas por MWh (valores didácticos). */
    static final String[] TECNOLOGIAS = {"carbon", "ciclo combinado", "cogeneracion", "motores diesel"};
    static final double[] FACTORES = {0.95, 0.37, 0.38, 0.77};

    /** Tecnologías renovables (ya normalizadas). */
    static final String[] RENOVABLES = {"eolica", "solar fotovoltaica", "solar termica", "hidraulica"};

    /**
     * Ejercicio 1. Factor de emisión de una tecnología, en t CO2/MWh.
     * Devuelve 0.0 si no está en la tabla (nuclear, eólica, solar...).
     * Normaliza el nombre con Ut1Asg.normalizar antes de comparar.
     */
    public static double factorEmision(String tecnologia) {
        // TODO: recorre TECNOLOGIAS y devuelve el FACTORES de la misma posición
        throw new UnsupportedOperationException("TODO ejercicio 1");
    }

    /** Ejercicio 2. Indica si una tecnología es renovable. */
    public static boolean esRenovable(String tecnologia) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 2");
    }

    /**
     * Ejercicio 3. Toneladas de CO2 emitidas: suma de mwh[i] x factor de tecnologias[i].
     * Los dos arrays tienen la misma longitud.
     */
    public static double toneladas(String[] tecnologias, double[] mwh) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 3");
    }

    /**
     * Ejercicio 4. Intensidad de carbono en g CO2/kWh, redondeada a 1 decimal.
     * Fórmula: toneladas / MWh totales x 1000. Si el total de MWh es 0, devuelve 0.0.
     */
    public static double intensidad(String[] tecnologias, double[] mwh) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 4");
    }

    /**
     * Ejercicio 5. Porcentaje de la generación que es renovable, redondeado a 1 decimal.
     * Si el total es 0, devuelve 0.0.
     */
    public static double porcentajeRenovable(String[] tecnologias, double[] mwh) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 5");
    }

    /**
     * Ejercicio 6. Semáforo de la red: "VERDE" por debajo de 100 g/kWh,
     * "AMBAR" por debajo de 200, "ROJO" a partir de 200.
     * Lanza IllegalArgumentException si la intensidad es negativa.
     */
    public static String semaforoRed(double gramosPorKwh) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 6");
    }

    /**
     * Ejercicio 7. Kilos de CO2 de consumir unos kWh con una intensidad dada,
     * redondeados a 2 decimales. (kwh x g/kWh) / 1000.
     */
    public static double kgCo2(double kwh, double gramosPorKwh) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 7");
    }

    /**
     * Ejercicio 8. Posición del día con menor intensidad. En caso de empate, el primero.
     * Si el array está vacío, devuelve -1.
     */
    public static int diaMasLimpio(double[] intensidades) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 8");
    }
}
