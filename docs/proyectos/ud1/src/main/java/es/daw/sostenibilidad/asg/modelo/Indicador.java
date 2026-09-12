package es.daw.sostenibilidad.asg.modelo;

import java.util.Set;

/**
 * Un indicador de sostenibilidad de una empresa.
 *
 * @param codigo       identificador corto, p. ej. "A-01"
 * @param nombre       qué mide, p. ej. "Electricidad renovable en el CPD (%)"
 * @param dimension    dimensión ASG a la que pertenece
 * @param valor        valor alcanzado en el periodo
 * @param meta         objetivo que la empresa se ha fijado
 * @param mayorEsMejor true si conviene que el valor suba (renovables); false si conviene que baje (emisiones)
 * @param ods          números de los ODS (1-17) a los que contribuye
 */
public record Indicador(String codigo, String nombre, Dimension dimension,
                        double valor, double meta, boolean mayorEsMejor, Set<Integer> ods) {
}
