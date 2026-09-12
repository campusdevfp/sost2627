package es.daw.sostenibilidad.plan.modelo;

/**
 * Acción del plan de sostenibilidad ligada a un aspecto material y medida con un indicador.
 *
 * @param aspecto     nombre del aspecto material al que responde
 * @param descripcion qué se va a hacer
 * @param indicador   cómo se mide, con su unidad
 * @param lineaBase   valor del indicador al empezar
 * @param meta        valor que se quiere alcanzar (puede ser menor que la línea base)
 * @param valorActual último valor medido
 */
public record Accion(String aspecto, String descripcion, String indicador,
                     double lineaBase, double meta, double valorActual) {
}
