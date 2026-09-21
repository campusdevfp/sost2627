package sostenibilidad;

/**
 * UT5 · Huella de carbono del software.
 *
 * Completa los ocho métodos. Ejecuta los tests con:  mvn test -Dtest=Ut5HuellaTest
 */
public class Ut5Huella {

    /** Energía por GB transferido, modelo Sustainable Web Design (simplificado). */
    public static final double KWH_POR_GB = 0.81;

    /** Un gigabyte en bytes. */
    public static final double BYTES_POR_GB = 1_000_000_000.0;

    /** Método 1. kWh necesarios para transferir esos bytes (sin redondear). */
    public static double kwhTransferencia(long bytes) {
        // TODO: bytes / BYTES_POR_GB * KWH_POR_GB
        throw new UnsupportedOperationException("TODO método 1");
    }

    /**
     * Método 2. Gramos de CO2 de transferir esos bytes con una intensidad de red
     * dada en g CO2/kWh (sin redondear).
     */
    public static double gramosCo2(long bytes, double gramosPorKwh) {
        // TODO
        throw new UnsupportedOperationException("TODO método 2");
    }

    /**
     * Método 3. Kg de CO2 al año de un endpoint, redondeados a 1 decimal:
     * gramos por petición x peticiones al día x 365, pasado a kilos.
     */
    public static double kgAnuales(long bytesPorPeticion, long peticionesDia, double gramosPorKwh) {
        // TODO
        throw new UnsupportedOperationException("TODO método 3");
    }

    /**
     * Método 4. SCI (Software Carbon Intensity): (energia x intensidad + embebido) / unidades.
     * Lanza IllegalArgumentException si las unidades funcionales no son positivas.
     */
    public static double sci(double energiaKwh, double gramosPorKwh,
                             double embebidoGramos, long unidadesFuncionales) {
        // TODO
        throw new UnsupportedOperationException("TODO método 4");
    }

    /**
     * Método 5. Porcentaje de reducción entre una medida antes y después,
     * redondeado a 1 decimal. Puede ser negativo si ha empeorado.
     * Lanza IllegalArgumentException si antes no es mayor que 0.
     */
    public static double reduccionPorcentual(double antes, double despues) {
        // TODO
        throw new UnsupportedOperationException("TODO método 5");
    }

    /**
     * Método 6. Sospecha del problema N+1: hay al menos 2 elementos y el número de
     * consultas es mayor o igual que elementos + 1.
     */
    public static boolean hayNMasUno(long consultas, int elementos) {
        // TODO
        throw new UnsupportedOperationException("TODO método 6");
    }

    /**
     * Método 7. Hora del día (posición en el array, de 0 a 23) con menor intensidad
     * dentro del rango [desde, hasta], ambos incluidos. En caso de empate, la más temprana.
     * Devuelve -1 si el rango no es válido.
     */
    public static int mejorHora(double[] intensidadPorHora, int desde, int hasta) {
        // TODO
        throw new UnsupportedOperationException("TODO método 7");
    }

    /**
     * Método 8. Etiqueta energética de una petición según sus gramos de CO2:
     * <= 0.1 "A", <= 0.2 "B", <= 0.4 "C", <= 0.8 "D", <= 1.6 "E", resto "F".
     */
    public static String etiqueta(double gramosPorPeticion) {
        // TODO
        throw new UnsupportedOperationException("TODO método 8");
    }
}
