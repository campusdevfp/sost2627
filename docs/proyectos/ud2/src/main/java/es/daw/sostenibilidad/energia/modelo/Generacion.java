package es.daw.sostenibilidad.energia.modelo;

import java.time.LocalDate;

/**
 * Electricidad generada por una tecnología en un día.
 *
 * @param fecha      día
 * @param tecnologia nombre tal como lo publica Red Eléctrica, p. ej. "Ciclo combinado"
 * @param mwh        energía generada en megavatios hora
 */
public record Generacion(LocalDate fecha, String tecnologia, double mwh) {
}
