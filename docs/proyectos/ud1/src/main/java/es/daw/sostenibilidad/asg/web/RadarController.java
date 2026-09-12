package es.daw.sostenibilidad.asg.web;

import es.daw.sostenibilidad.asg.modelo.Dimension;
import es.daw.sostenibilidad.asg.modelo.Indicador;
import es.daw.sostenibilidad.asg.servicio.AsgService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * API REST del Radar ASG. Ya está completa: funciona en cuanto el servicio pase los tests.
 * <ul>
 *   <li>GET /api/indicadores</li>
 *   <li>GET /api/radar</li>
 *   <li>GET /api/clasificar?tema=Horas de formación</li>
 * </ul>
 */
@RestController
@RequestMapping("/api")
public class RadarController {

    private final AsgService servicio;
    private final IndicadorRepositorio repositorio;

    public RadarController(AsgService servicio, IndicadorRepositorio repositorio) {
        this.servicio = servicio;
        this.repositorio = repositorio;
    }

    @GetMapping("/indicadores")
    public List<Indicador> indicadores() {
        return repositorio.todos();
    }

    @GetMapping("/radar")
    public Map<String, Object> radar() {
        List<Indicador> todos = repositorio.todos();
        Map<Dimension, Double> porDimension = servicio.puntuacionPorDimension(todos);
        double puntuacion = servicio.puntuacionAsg(porDimension);
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("porDimension", porDimension);
        respuesta.put("puntuacion", puntuacion);
        respuesta.put("rating", servicio.rating(puntuacion));
        respuesta.put("odsCubiertos", servicio.odsCubiertos(todos));
        return respuesta;
    }

    @GetMapping("/clasificar")
    public Map<String, String> clasificar(@RequestParam String tema) {
        String dimension = servicio.clasificar(tema).map(Enum::name).orElse("SIN CLASIFICAR");
        return Map.of("tema", tema, "dimension", dimension);
    }
}
