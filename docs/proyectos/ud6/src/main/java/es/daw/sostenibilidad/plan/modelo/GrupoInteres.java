package es.daw.sostenibilidad.plan.modelo;

/**
 * Grupo de interés (stakeholder) valorado de 1 a 5.
 *
 * @param nombre      p. ej. "Clientes", "Plantilla", "Administración"
 * @param influencia  cuánto puede afectar a las decisiones de la empresa (1-5)
 * @param interes     cuánto le afectan o le importan esas decisiones (1-5)
 */
public record GrupoInteres(String nombre, int influencia, int interes) {
}
