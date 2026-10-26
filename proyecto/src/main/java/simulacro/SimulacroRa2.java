package simulacro;

/**
 * SIMULACRO DE EXAMEN · RA2 (UT2) · 40 minutos
 *
 * mvn test -Dtest=SimulacroRa2Test
 */
public class SimulacroRa2 {

    /** Tecnologías que emiten y su factor en t CO2/MWh. ¡OJO! LA TABLA NO ES LA DE CLASE. */
    static final String[] TECNOLOGIAS = {"Carbón", "Ciclo combinado", "Turbina de gas"};
    static final double[] FACTORES = {0.90, 0.40, 0.60};

    /**
     * 1. Factor de emisión de una tecnología; 0.0 si no está en la tabla.
     * Ejemplos: "Turbina de gas" -> 0.60 · "Cogeneración" -> 0.0 (aquí no está)
     */
    public static double factorEmision(String tecnologia) {
        throw new UnsupportedOperationException("TODO 1");
    }

    /**
     * 2. Intensidad de carbono en g CO2/kWh, redondeada a 1 decimal.
     * toneladas / MWh totales x 1000. Si el total es 0, devuelve 0.0.
     */
    public static double intensidad(String[] tecnologias, double[] mwh) {
        throw new UnsupportedOperationException("TODO 2");
    }

    /**
     * 3. Kilos de CO2 de un consumo, redondeados a 1 DECIMAL (en clase eran 2).
     * Lanza IllegalArgumentException si kwh o la intensidad son negativos.
     */
    public static double kgCo2(double kwh, double gramosPorKwh) {
        throw new UnsupportedOperationException("TODO 3");
    }
}
