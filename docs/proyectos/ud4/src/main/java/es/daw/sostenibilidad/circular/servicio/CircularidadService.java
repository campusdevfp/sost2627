package es.daw.sostenibilidad.circular.servicio;

import es.daw.sostenibilidad.circular.modelo.Equipo;
import es.daw.sostenibilidad.circular.modelo.EstadoEquipo;
import es.daw.sostenibilidad.circular.modelo.OpcionR;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Reglas de economía circular de ReUsa: ciclo de vida de los equipos, huella
 * anualizada, jerarquía de las R y tasa de circularidad.
 *
 * Completa cada TODO y borra el {@code throw new UnsupportedOperationException}.
 */
@Service
public class CircularidadService {

    /**
     * Indica si un equipo puede pasar de un estado a otro:
     * <ul>
     *   <li>EN_USO -&gt; EN_REPARACION, REACONDICIONADO, DONADO o RECICLADO</li>
     *   <li>EN_REPARACION -&gt; EN_USO, REACONDICIONADO o RECICLADO</li>
     *   <li>REACONDICIONADO -&gt; EN_USO o DONADO</li>
     *   <li>DONADO y RECICLADO son finales: no pueden pasar a ningún estado</li>
     * </ul>
     * Pasar al mismo estado en el que ya está no es una transición permitida.
     */
    public boolean transicionPermitida(EstadoEquipo desde, EstadoEquipo hasta) {
        // TODO: un switch sobre 'desde' que devuelva si 'hasta' está entre los permitidos
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Cambia el estado del equipo si la transición está permitida.
     *
     * @throws IllegalStateException si no lo está (criterio del RA4); el mensaje debe
     *                               incluir el nombre de los dos estados
     */
    public void cambiarEstado(Equipo equipo, EstadoEquipo nuevo) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Huella anual del equipo en kg de CO2: fabricación repartida entre los años de vida
     * más la huella de un año de uso. Redondeada a 1 decimal.
     *
     * @throws IllegalArgumentException si los años de vida no son positivos
     */
    public double huellaAnual(double kgFabricacion, double kgUsoAnual, int aniosVida) {
        // TODO: kgFabricacion / aniosVida + kgUsoAnual
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Kg de CO2 al año que se ahorran alargando la vida del equipo: diferencia entre la
     * fabricación repartida en los años actuales y repartida en los años actuales + extra.
     * Redondeado a 1 decimal.
     *
     * @throws IllegalArgumentException si aniosActuales no es positivo o aniosExtra es negativo
     */
    public double ahorroAnualAmpliandoVida(double kgFabricacion, int aniosActuales, int aniosExtra) {
        // TODO: F / L - F / (L + x)
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Mejor opción circular entre las viables: la de menor ordinal en {@link OpcionR}.
     * Si no hay ninguna viable, la única salida es ELIMINAR.
     */
    public OpcionR mejorOpcion(Set<OpcionR> viables) {
        // TODO: stream().min(Comparator.naturalOrder()) o recorrer OpcionR.values() en orden
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Porcentaje de equipos retirados que siguieron teniendo una segunda vida:
     * (REACONDICIONADO + DONADO) / (REACONDICIONADO + DONADO + RECICLADO) x 100,
     * redondeado a 1 decimal. Si no hay ninguno retirado, 0.0.
     */
    public double tasaCircularidad(List<Equipo> equipos) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Equipos que conviene revisar para reacondicionar en lugar de sustituir: los que están
     * EN_USO o EN_REPARACION y tienen al menos {@code edadMinima} años en {@code anioActual}.
     * Ordenados del más antiguo al más nuevo y, a igual edad, por etiqueta.
     */
    public List<Equipo> candidatosReacondicionar(List<Equipo> equipos, int anioActual, int edadMinima) {
        // TODO: filter + sorted(Comparator.comparingInt(...).thenComparing(...))
        throw new UnsupportedOperationException("TODO");
    }
}
