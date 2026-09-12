package es.daw.sostenibilidad.asg.servicio;

import es.daw.sostenibilidad.asg.modelo.Dimension;
import es.daw.sostenibilidad.asg.modelo.Indicador;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Lógica del Radar ASG: clasifica temas, mide el cumplimiento de los indicadores
 * y calcula una puntuación y un rating ASG de la empresa.
 *
 * Completa cada TODO y borra el {@code throw new UnsupportedOperationException}.
 */
@Service
public class AsgService {

    /** Pesos de cada dimensión en la puntuación global (suman 1). */
    public static final double PESO_AMBIENTAL = 0.4;
    public static final double PESO_SOCIAL = 0.3;
    public static final double PESO_GOBERNANZA = 0.3;

    // Raíces de palabras que delatan cada dimensión (ya normalizadas: minúsculas y sin tildes).
    // Se comprueban en este orden: primero ambiental, luego social y por último gobernanza.
    static final Set<String> RAICES_AMBIENTALES =
            Set.of("emisi", "energ", "agua", "residu", "biodivers", "clim", "carbon", "recicl");
    static final Set<String> RAICES_SOCIALES =
            Set.of("emple", "formaci", "salud", "igualdad", "divers", "accesib", "brecha", "concilia", "comunidad");
    static final Set<String> RAICES_GOBERNANZA =
            Set.of("etic", "corrupci", "consejo", "transparen", "cumplimiento", "fiscal", "soborno", "blanqueo");

    /**
     * Pasa un texto a minúsculas, le quita las tildes y los espacios de los extremos.
     * Ya está hecho: úsalo en {@link #clasificar(String)}.
     */
    static String normalizar(String texto) {
        String sinTildes = Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return sinTildes.toLowerCase().trim();
    }

    /**
     * Clasifica un tema en su dimensión ASG buscando las raíces de cada conjunto.
     * Devuelve vacío si el tema es nulo, está en blanco o no contiene ninguna raíz.
     */
    public Optional<Dimension> clasificar(String tema) {
        // TODO: si tema es null o está en blanco -> Optional.empty()
        //       normaliza el tema y comprueba, EN ORDEN, si contiene alguna raíz
        //       ambiental, social o de gobernanza (texto.contains(raiz))
        throw new UnsupportedOperationException("TODO");
    }

    /** Indica si un número corresponde a un ODS de la Agenda 2030 (del 1 al 17). */
    public boolean odsValido(int numero) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Grado de cumplimiento del indicador respecto a su meta, entre 0.0 y 1.0.
     * <ul>
     *   <li>Mayor es mejor: valor / meta, con máximo 1.0. Si la meta es 0, el cumplimiento es 1.0.</li>
     *   <li>Menor es mejor: si valor &lt;= meta, 1.0; si no, meta / valor.</li>
     * </ul>
     */
    public double gradoCumplimiento(Indicador indicador) {
        // TODO: distingue los dos casos con indicador.mayorEsMejor()
        //       usa Math.min / Math.max para acotar entre 0.0 y 1.0
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Puntuación de 0 a 100 de cada dimensión: media del grado de cumplimiento de sus
     * indicadores multiplicada por 100 y redondeada a 1 decimal.
     * El mapa tiene SIEMPRE las tres dimensiones; si una no tiene indicadores vale 0.0.
     */
    public Map<Dimension, Double> puntuacionPorDimension(List<Indicador> indicadores) {
        // TODO: crea un EnumMap<Dimension, Double> (criterio del RA1)
        //       para cada dimensión, filtra sus indicadores y calcula la media
        //       redondeo a 1 decimal: Math.round(x * 10) / 10.0
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Puntuación ASG global: media ponderada con PESO_AMBIENTAL, PESO_SOCIAL y
     * PESO_GOBERNANZA, redondeada a 1 decimal. Una dimensión ausente del mapa cuenta como 0.
     */
    public double puntuacionAsg(Map<Dimension, Double> porDimension) {
        // TODO: usa porDimension.getOrDefault(..., 0.0)
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Rating del curso, inspirado en la escala de letras de las agencias ASG:
     * &gt;= 85 "AAA", &gt;= 70 "AA", &gt;= 55 "A", &gt;= 40 "BBB", &gt;= 25 "BB" y el resto "B".
     *
     * @throws IllegalArgumentException si la puntuación es menor que 0 o mayor que 100
     */
    public String rating(double puntuacion) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * ODS a los que contribuye el conjunto de indicadores, ordenados y sin repetir.
     * Los números que no son ODS válidos se ignoran.
     */
    public SortedSet<Integer> odsCubiertos(List<Indicador> indicadores) {
        // TODO: recorre los ods de cada indicador y añade los válidos a un TreeSet
        throw new UnsupportedOperationException("TODO");
    }
}
