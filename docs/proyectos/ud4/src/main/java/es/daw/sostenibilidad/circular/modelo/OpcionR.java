package es.daw.sostenibilidad.circular.modelo;

/**
 * Jerarquía de las "R" de la economía circular, de MÁS a MENOS preferible.
 * El orden de declaración importa: {@code ordinal()} 0 es la mejor opción.
 */
public enum OpcionR {
    REDUCIR,
    REUTILIZAR,
    REPARAR,
    REACONDICIONAR,
    RECICLAR,
    VALORIZAR,
    ELIMINAR
}
