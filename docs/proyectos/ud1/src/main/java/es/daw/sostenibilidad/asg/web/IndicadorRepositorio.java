package es.daw.sostenibilidad.asg.web;

import es.daw.sostenibilidad.asg.modelo.Dimension;
import es.daw.sostenibilidad.asg.modelo.Indicador;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Carga los indicadores de la empresa desde {@code datos/indicadores.csv}.
 * Formato: codigo;nombre;dimension;valor;meta;mayorEsMejor;ods (ods separados por |).
 * Sustituye el CSV por los datos de la empresa real que analices.
 */
@Component
public class IndicadorRepositorio {

    private final List<Indicador> indicadores;

    public IndicadorRepositorio() {
        this.indicadores = cargar("/datos/indicadores.csv");
    }

    public List<Indicador> todos() {
        return indicadores;
    }

    private static List<Indicador> cargar(String ruta) {
        try (InputStream in = IndicadorRepositorio.class.getResourceAsStream(ruta)) {
            if (in == null) {
                throw new IllegalStateException("No se encuentra " + ruta);
            }
            BufferedReader lector = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            return lector.lines()
                    .skip(1)                                  // cabecera
                    .filter(linea -> !linea.isBlank())
                    .map(IndicadorRepositorio::parsear)
                    .toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static Indicador parsear(String linea) {
        String[] c = linea.split(";");
        Set<Integer> ods = Arrays.stream(c[6].split("\\|"))
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
        return new Indicador(c[0], c[1], Dimension.valueOf(c[2]),
                Double.parseDouble(c[3]), Double.parseDouble(c[4]),
                Boolean.parseBoolean(c[5]), ods);
    }
}
