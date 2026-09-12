package es.daw.sostenibilidad.plan.modelo;

/**
 * Aspecto de sostenibilidad candidato a ser material, valorado de 1 a 5.
 *
 * @param nombre            p. ej. "Consumo energético del CPD"
 * @param dimension         dimensión ASG
 * @param impacto           materialidad de impacto: cuánto afecta la empresa a las personas y al planeta
 * @param financiero        materialidad financiera: cuánto afecta el aspecto al negocio
 * @param importanciaGrupos importancia media que le dan los grupos de interés consultados
 */
public record AspectoMaterial(String nombre, Dimension dimension,
                              double impacto, double financiero, double importanciaGrupos) {
}
