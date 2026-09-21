package simulacro;

/**
 * SIMULACRO DE EXAMEN · RA4 (UT4) · 40 minutos
 *
 * mvn test -Dtest=SimulacroRa4Test
 */
public class SimulacroRa4 {

    /**
     * 1. Huella anual de un equipo en kg de CO2, redondeada al ENTERO más próximo:
     * fabricación / años de vida + uso anual.
     * Lanza IllegalArgumentException si los años no son positivos.
     */
    public static long huellaAnual(double kgFabricacion, double kgUsoAnual, int aniosVida) {
        throw new UnsupportedOperationException("TODO 1");
    }

    /**
     * 2. Transiciones de un ciclo de vida SIMPLIFICADO con tres estados:
     *   EN_USO        -> EN_REPARACION, RETIRADO
     *   EN_REPARACION -> EN_USO, RETIRADO
     *   RETIRADO      -> ninguno
     * Pasar al mismo estado no está permitido. Un estado desconocido devuelve false.
     */
    public static boolean transicionPermitida(String desde, String hasta) {
        throw new UnsupportedOperationException("TODO 2");
    }

    /**
     * 3. Tasa de circularidad, redondeada a 1 decimal:
     *   segunda vida = "REACONDICIONADO" + "DONADO"
     *   retirados    = segunda vida + "RECICLADO" + "VERTEDERO"
     * Los demás estados se ignoran. Si no hay retirados, 0.0.
     */
    public static double tasaCircularidad(String[] estados) {
        throw new UnsupportedOperationException("TODO 3");
    }
}
