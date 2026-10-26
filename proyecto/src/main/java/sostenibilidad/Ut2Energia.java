package sostenibilidad;

/**
 * UT2 · La huella de la electricidad.
 *
 * Completa los ocho métodos. Cada uno está explicado paso a paso en la
 * batería de la unidad (apartado 2 de la documentación).
 *
 * Todos los tests:      mvn test -Dtest=Ut2EnergiaTest
 * Solo un método:       mvn test -Dtest=Ut2EnergiaTest#factorEmision
 */
public class Ut2Energia {

    /*
     * Tabla de factores de emisión. Son dos arrays "paralelos":
     * la posición i de uno va con la posición i del otro.
     *
     *   posición   TECNOLOGIAS[i]      FACTORES[i]  (toneladas de CO2 por MWh)
     *      0       "Carbón"            0.95
     *      1       "Ciclo combinado"   0.37
     *      2       "Cogeneración"      0.38
     *      3       "Motores diésel"    0.77
     *
     * Las tecnologías que NO emiten (eólica, solar, hidráulica, nuclear) no están.
     */
    static final String[] TECNOLOGIAS = {"Carbón", "Ciclo combinado", "Cogeneración", "Motores diésel"};
    static final double[] FACTORES = {0.95, 0.37, 0.38, 0.77};

    /** Tecnologías renovables. La nuclear NO está: no emite, pero no es renovable. */
    static final String[] RENOVABLES = {"Eólica", "Solar fotovoltaica", "Solar térmica", "Hidráulica"};

    /**
     * Método 1. Factor de emisión de una tecnología, en t CO2/MWh.
     * Si no está en TECNOLOGIAS, devuelve 0.0.
     * Ejemplos: "Carbón" -> 0.95 · "Nuclear" -> 0.0
     */
    public static double factorEmision(String tecnologia) {
        // TODO: recorre TECNOLOGIAS; si TECNOLOGIAS[i] es igual, devuelve FACTORES[i]
        throw new UnsupportedOperationException("TODO método 1");
    }

    /**
     * Método 2. Indica si una tecnología está en RENOVABLES.
     * Ejemplos: "Eólica" -> true · "Nuclear" -> false
     */
    public static boolean esRenovable(String tecnologia) {
        // TODO
        throw new UnsupportedOperationException("TODO método 2");
    }

    /**
     * Método 3. Toneladas de CO2 de un día: suma de mwh[i] x factorEmision(tecnologias[i]).
     * Ejemplo: {"Eólica", "Ciclo combinado"} con {300, 200} -> 0 + 74 = 74.0
     */
    public static double toneladas(String[] tecnologias, double[] mwh) {
        // TODO
        throw new UnsupportedOperationException("TODO método 3");
    }

    /**
     * Método 4. Intensidad de carbono en g CO2/kWh, redondeada a 1 decimal.
     * Fórmula: toneladas / MWh totales x 1000. Si el total de MWh es 0, devuelve 0.0.
     * Ejemplo: 74 t en 1000 MWh -> 74.0
     */
    public static double intensidad(String[] tecnologias, double[] mwh) {
        // TODO
        throw new UnsupportedOperationException("TODO método 4");
    }

    /**
     * Método 5. Porcentaje de los MWh que son renovables, redondeado a 1 decimal.
     * Si el total es 0, devuelve 0.0.
     * Ejemplo: 300 MWh eólicos de 1000 -> 30.0
     */
    public static double porcentajeRenovable(String[] tecnologias, double[] mwh) {
        // TODO
        throw new UnsupportedOperationException("TODO método 5");
    }

    /**
     * Método 6. Semáforo de la red: "VERDE" por debajo de 100 g/kWh,
     * "AMBAR" por debajo de 200, "ROJO" a partir de 200.
     * Lanza IllegalArgumentException si la intensidad es negativa.
     */
    public static String semaforoRed(double gramosPorKwh) {
        // TODO
        throw new UnsupportedOperationException("TODO método 6");
    }

    /**
     * Método 7. Kilos de CO2 de consumir unos kWh con una intensidad dada,
     * redondeados a 2 decimales: kwh x g/kWh / 1000.
     * Ejemplo: 250 kWh a 120 g/kWh -> 30.0 kg
     */
    public static double kgCo2(double kwh, double gramosPorKwh) {
        // TODO
        throw new UnsupportedOperationException("TODO método 7");
    }

    /**
     * Método 8. POSICIÓN del día con menor intensidad. En caso de empate, el primero.
     * Si el array está vacío, devuelve -1.
     * Ejemplo: {90, 60, 120} -> 1
     */
    public static int diaMasLimpio(double[] intensidades) {
        // TODO
        throw new UnsupportedOperationException("TODO método 8");
    }
}
