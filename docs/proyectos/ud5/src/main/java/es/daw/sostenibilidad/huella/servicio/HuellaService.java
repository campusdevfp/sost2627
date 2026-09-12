package es.daw.sostenibilidad.huella.servicio;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * Estima la huella de carbono del software y ayuda a decidir cuándo ejecutar tareas.
 *
 * Completa cada TODO y borra el {@code throw new UnsupportedOperationException}.
 */
@Service
public class HuellaService {

    /**
     * Energía por GB transferido según el modelo Sustainable Web Design (versión 3, simplificado).
     * Incluye centros de datos, redes y dispositivos. Es una estimación, no una medida.
     */
    public static final double KWH_POR_GB = 0.81;

    /** Un gigabyte en bytes (sistema decimal, como en el modelo). */
    public static final double BYTES_POR_GB = 1_000_000_000.0;

    /** kWh estimados para transferir una cantidad de bytes (sin redondear). */
    public double kwhTransferencia(long bytes) {
        // TODO: bytes / BYTES_POR_GB * KWH_POR_GB
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Gramos de CO2 de transferir una cantidad de bytes con una intensidad de red dada
     * (g CO2/kWh, la que calculaste en la UD2). Sin redondear.
     */
    public double gramosCo2(long bytes, double gramosPorKwh) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Software Carbon Intensity (especificación SCI de la Green Software Foundation):
     * SCI = (E x I + M) / R, en gramos de CO2 por unidad funcional.
     *
     * @param energiaKwh         E: energía consumida por el software en el periodo
     * @param gramosPorKwh       I: intensidad de carbono de la electricidad
     * @param embebidoGramos     M: parte de la huella de fabricación del hardware asignada al periodo
     * @param unidadesFuncionales R: peticiones, usuarios, informes... atendidos en el periodo
     * @throws IllegalArgumentException si R no es positivo
     */
    public double sci(double energiaKwh, double gramosPorKwh, double embebidoGramos, long unidadesFuncionales) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Porcentaje de reducción entre una medida antes y después de optimizar, redondeado a 1 decimal.
     * Puede ser negativo si empeoró.
     *
     * @throws IllegalArgumentException si el valor de antes no es positivo
     */
    public double reduccionPorcentual(double antes, double despues) {
        // TODO: (antes - despues) / antes x 100
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Sospecha de problema N+1: se devolvieron al menos 2 elementos y se lanzaron al menos
     * tantas consultas como elementos + 1 (una para la lista y otra por cada elemento).
     */
    public boolean sospechaNMasUno(long consultas, int elementosDevueltos) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Mejor hora (la de menor intensidad) dentro de la ventana [desde, hasta], ambos incluidos.
     * Si hay empate gana la hora más temprana. Vacío si no hay datos en la ventana.
     *
     * @param intensidadPorHora hora del día (0-23) -&gt; g CO2/kWh previstos
     */
    public Optional<Integer> mejorHora(Map<Integer, Double> intensidadPorHora, int desde, int hasta) {
        // TODO: recorre las horas de desde a hasta y quédate con la de menor intensidad
        throw new UnsupportedOperationException("TODO");
    }

    /** Carbon-aware: ejecutar ahora solo si la intensidad actual no supera el umbral. */
    public boolean ejecutarAhora(double intensidadActual, double umbral) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Etiqueta energética del curso para una petición o visita, según sus gramos de CO2:
     * &lt;= 0.1 "A", &lt;= 0.2 "B", &lt;= 0.4 "C", &lt;= 0.8 "D", &lt;= 1.6 "E" y el resto "F".
     */
    public String etiqueta(double gramosPorPeticion) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }
}
