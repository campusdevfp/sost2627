package es.daw.sostenibilidad.circular.modelo;

/**
 * Etapas del ciclo de vida de un equipo en ReUsa.
 * DONADO y RECICLADO son estados finales: el equipo sale del inventario.
 */
public enum EstadoEquipo {
    EN_USO,
    EN_REPARACION,
    REACONDICIONADO,
    DONADO,
    RECICLADO
}
