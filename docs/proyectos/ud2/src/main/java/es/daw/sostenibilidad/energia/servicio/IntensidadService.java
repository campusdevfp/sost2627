package es.daw.sostenibilidad.energia.servicio;

import es.daw.sostenibilidad.energia.modelo.Generacion;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Calcula cuánto CO2 emite la electricidad que consumimos a partir de la
 * estructura de generación del sistema eléctrico.
 *
 * Completa cada TODO y borra el {@code throw new UnsupportedOperationException}.
 */
@Service
public class IntensidadService {

    /**
     * Factores de emisión en toneladas de CO2 equivalente por MWh generado.
     * Valores APROXIMADOS con fines didácticos; los oficiales los publica Red Eléctrica
     * y pueden cambiar. Las claves están normalizadas (minúsculas y sin tildes).
     * Cualquier tecnología que no aparezca aquí (nuclear, eólica, solar...) emite 0.
     */
    public static final Map<String, Double> FACTOR_T_CO2_POR_MWH = Map.of(
            "carbon", 0.95,
            "ciclo combinado", 0.37,
            "cogeneracion", 0.38,
            "motores diesel", 0.77,
            "turbina de gas", 0.62,
            "residuos no renovables", 0.24);

    /** Tecnologías renovables (normalizadas). */
    public static final Set<String> RENOVABLES = Set.of(
            "eolica", "solar fotovoltaica", "solar termica", "hidraulica",
            "hidroeolica", "otras renovables", "residuos renovables");

    /** Minúsculas, sin tildes y sin espacios en los extremos: "Eólica " -> "eolica". */
    public String normalizar(String tecnologia) {
        // TODO: Normalizer.normalize(texto, Normalizer.Form.NFD) y quita las marcas con replaceAll("\\p{M}", "")
        throw new UnsupportedOperationException("TODO");
    }

    /** Factor de emisión (t CO2/MWh) de una tecnología; 0.0 si no está en la tabla. */
    public double factorEmision(String tecnologia) {
        // TODO: normaliza y usa getOrDefault
        throw new UnsupportedOperationException("TODO");
    }

    /** Toneladas de CO2 emitidas: suma de mwh x factor de cada registro (sin redondear). */
    public double emisionesToneladas(List<Generacion> datos) {
        // TODO: usa la API de Streams (criterio del RA2): stream().mapToDouble(...).sum()
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Intensidad de carbono en gramos de CO2 por kWh, redondeada a 1 decimal.
     * Fórmula: toneladas / MWh totales x 1000. Si no hay datos o el total es 0, devuelve 0.0.
     */
    public double intensidad(List<Generacion> datos) {
        // TODO: 1 t/MWh = 1000 g/kWh
        throw new UnsupportedOperationException("TODO");
    }

    /** Porcentaje de la generación que es renovable, redondeado a 1 decimal (0.0 si no hay datos). */
    public double porcentajeRenovable(List<Generacion> datos) {
        // TODO: filtra por RENOVABLES.contains(normalizar(...))
        throw new UnsupportedOperationException("TODO");
    }

    /** Intensidad de cada día, ordenada por fecha. */
    public Map<LocalDate, Double> intensidadDiaria(List<Generacion> datos) {
        // TODO: Collectors.groupingBy(Generacion::fecha, TreeMap::new, Collectors.toList())
        //       y después calcula la intensidad de la lista de cada día
        throw new UnsupportedOperationException("TODO");
    }

    /** Día con menor intensidad de carbono; vacío si no hay datos. */
    public Optional<LocalDate> diaMasLimpio(List<Generacion> datos) {
        // TODO: recorre intensidadDiaria(...) y quédate con la clave de menor valor
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Semáforo del curso: menos de 100 g/kWh "VERDE", menos de 200 "AMBAR", el resto "ROJO".
     *
     * @throws IllegalArgumentException si la intensidad es negativa
     */
    public String semaforo(double gramosPorKwh) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /** Kilos de CO2 de un consumo en kWh con una intensidad dada, redondeados a 2 decimales. */
    public double kgCo2DeConsumo(double kwh, double gramosPorKwh) {
        // TODO: kwh x g/kWh / 1000; redondeo a 2 decimales: Math.round(x * 100) / 100.0
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Convierte las líneas de un CSV "fecha;tecnologia;mwh" en registros.
     * La primera línea es la cabecera y se salta; las líneas en blanco se ignoran.
     */
    public List<Generacion> leerCsv(List<String> lineas) {
        // TODO: LocalDate.parse(...) para la fecha, Double.parseDouble(...) para los MWh
        throw new UnsupportedOperationException("TODO");
    }
}
