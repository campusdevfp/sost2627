package simulacro;

/**
 * SIMULACRO DE EXAMEN · RA5 (UT5) · 40 minutos
 *
 * mvn test -Dtest=SimulacroRa5Test
 */
public class SimulacroRa5 {

    /** Energía por GB con un MODELO DISTINTO al de clase. */
    public static final double KWH_POR_GB = 0.5;
    public static final double BYTES_POR_GB = 1_000_000_000.0;

    /** 1. kWh necesarios para transferir esos bytes con este modelo (sin redondear). */
    public static double kwhTransferencia(long bytes) {
        throw new UnsupportedOperationException("TODO 1");
    }

    /**
     * 2. Kg de CO2 al año de un servicio que solo funciona los DÍAS LABORABLES (250 al año),
     * redondeados a 1 decimal:
     *   kWh por petición x intensidad x peticiones al día x 250, pasado a kilos.
     */
    public static double kgAnualesLaborables(long bytesPorPeticion, long peticionesDia, double gramosPorKwh) {
        throw new UnsupportedOperationException("TODO 2");
    }

    /**
     * 3. Sospecha del problema N+1 con un CRITERIO NUEVO:
     * hay al menos 5 elementos y el número de consultas es mayor o igual que el de elementos.
     */
    public static boolean hayNMasUno(long consultas, int elementos) {
        throw new UnsupportedOperationException("TODO 3");
    }
}
