package es.daw.sostenibilidad.energia.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.daw.sostenibilidad.energia.modelo.Generacion;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Cliente de la API pública REData de Red Eléctrica (https://www.ree.es/es/datos/apidatos).
 * Pide la estructura de generación diaria y la convierte en registros {@link Generacion}.
 * Si la API cambia de formato o no hay conexión, usa el CSV de ejemplo.
 */
@Component
public class ReeCliente {

    private final RestClient http = RestClient.create("https://apidatos.ree.es");

    public List<Generacion> estructuraGeneracion(LocalDate desde, LocalDate hasta) {
        ReeRespuesta respuesta = http.get()
                .uri(uri -> uri.path("/es/datos/generacion/estructura-generacion")
                        .queryParam("start_date", desde + "T00:00")
                        .queryParam("end_date", hasta + "T23:59")
                        .queryParam("time_trunc", "day")
                        .build())
                .retrieve()
                .body(ReeRespuesta.class);

        List<Generacion> datos = new ArrayList<>();
        if (respuesta == null || respuesta.included() == null) {
            return datos;
        }
        for (Incluido serie : respuesta.included()) {
            String tecnologia = serie.attributes().title();
            if (tecnologia.toLowerCase().startsWith("generaci")) {
                continue; // "Generación total" no es una tecnología: la saltamos
            }
            for (Valor v : serie.attributes().values()) {
                LocalDate dia = LocalDate.parse(v.datetime().substring(0, 10));
                datos.add(new Generacion(dia, tecnologia, v.value()));
            }
        }
        return datos;
    }

    // Registros que reflejan la parte del JSON (formato JSON:API) que nos interesa
    @JsonIgnoreProperties(ignoreUnknown = true)
    record ReeRespuesta(List<Incluido> included) { }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Incluido(String type, Atributos attributes) { }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Atributos(String title, List<Valor> values) { }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Valor(double value, double percentage, String datetime) { }
}
