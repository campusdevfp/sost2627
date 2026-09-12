package es.daw.sostenibilidad.plan.servicio;

import es.daw.sostenibilidad.plan.modelo.Accion;
import es.daw.sostenibilidad.plan.modelo.AspectoMaterial;
import es.daw.sostenibilidad.plan.modelo.GrupoInteres;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Lógica del plan de sostenibilidad: grupos de interés, doble materialidad,
 * priorización de aspectos y seguimiento de acciones.
 *
 * Completa cada TODO y borra el {@code throw new UnsupportedOperationException}.
 */
@Service
public class PlanService {

    /** Valor a partir del cual una puntuación de 1 a 5 se considera alta. */
    public static final int ALTO = 3;

    /**
     * Estrategia con cada grupo según la matriz de poder e interés (Mendelow):
     * <ul>
     *   <li>influencia alta e interés alto: "GESTIONAR DE CERCA"</li>
     *   <li>influencia alta e interés bajo: "MANTENER SATISFECHO"</li>
     *   <li>influencia baja e interés alto: "MANTENER INFORMADO"</li>
     *   <li>influencia baja e interés bajo: "MONITORIZAR"</li>
     * </ul>
     * "Alto" significa mayor o igual que ALTO.
     *
     * @throws IllegalArgumentException si influencia o interés no están entre 1 y 5
     */
    public String estrategiaGrupo(GrupoInteres grupo) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Doble materialidad: un aspecto es material si su impacto O su materialidad financiera
     * alcanzan el umbral.
     */
    public boolean esMaterial(AspectoMaterial aspecto, double umbral) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Prioridad de un aspecto: media entre la mayor de sus dos materialidades y la importancia
     * que le dan los grupos de interés, redondeada a 1 decimal.
     */
    public double prioridad(AspectoMaterial aspecto) {
        // TODO: (Math.max(impacto, financiero) + importanciaGrupos) / 2
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Aspectos materiales ordenados por prioridad de mayor a menor y, a igual prioridad,
     * por nombre alfabéticamente. Los no materiales se descartan.
     */
    public List<AspectoMaterial> aspectosPrioritarios(List<AspectoMaterial> aspectos, double umbral) {
        // TODO: filter(esMaterial) + sorted con un Comparator (criterio del RA6)
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Progreso de una acción entre 0.0 y 1.0, redondeado a 2 decimales:
     * (valorActual - lineaBase) / (meta - lineaBase), acotado entre 0 y 1.
     * Funciona tanto si la meta es subir como si es bajar.
     *
     * @throws IllegalArgumentException si la meta es igual a la línea base
     */
    public double progreso(Accion accion) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Estado de una acción según su progreso:
     * &gt;= 1.0 "COMPLETADA", &gt;= 0.5 "AVANZADA", &gt; 0 "INICIADA" y 0 "SIN EMPEZAR".
     */
    public String estado(double progreso) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Nombres de los aspectos que no tienen ninguna acción en el plan, en el mismo orden en
     * que vienen. La comparación de nombres no distingue mayúsculas de minúsculas.
     */
    public List<String> aspectosSinAccion(List<AspectoMaterial> aspectos, List<Accion> acciones) {
        // TODO: equalsIgnoreCase
        throw new UnsupportedOperationException("TODO");
    }
}
