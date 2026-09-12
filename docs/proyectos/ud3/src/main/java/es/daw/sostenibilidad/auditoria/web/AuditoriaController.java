package es.daw.sostenibilidad.auditoria.web;

import es.daw.sostenibilidad.auditoria.modelo.Hallazgo;
import es.daw.sostenibilidad.auditoria.modelo.Recurso;
import es.daw.sostenibilidad.auditoria.servicio.AuditoriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * API del auditor. Ya está completa.
 * <ul>
 *   <li>POST /api/auditoria  con una lista JSON de recursos -&gt; hallazgos y puntuación</li>
 *   <li>GET  /api/catalogo    respuesta JSON grande para observar el efecto de la compresión</li>
 * </ul>
 */
@RestController
@RequestMapping("/api")
public class AuditoriaController {

    private final AuditoriaService servicio;

    public AuditoriaController(AuditoriaService servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/auditoria")
    public Map<String, Object> auditar(@RequestBody List<Recurso> recursos) {
        List<Map<String, Object>> detalle = new ArrayList<>();
        for (Recurso r : recursos) {
            List<Hallazgo> hallazgos = servicio.auditar(r);
            detalle.add(Map.of(
                    "url", r.url(),
                    "hallazgos", hallazgos,
                    "recomendaciones", hallazgos.stream().map(Hallazgo::recomendacion).toList()));
        }
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("puntuacion", servicio.puntuacion(recursos));
        respuesta.put("pesoTotalBytes", servicio.pesoTotal(recursos));
        respuesta.put("detalle", detalle);
        return respuesta;
    }

    /** 3000 productos inventados: unos 360 kB sin comprimir. */
    @GetMapping("/catalogo")
    public List<Map<String, Object>> catalogo() {
        return IntStream.rangeClosed(1, 3000)
                .mapToObj(i -> Map.<String, Object>of(
                        "id", i,
                        "nombre", "Producto reacondicionado número " + i,
                        "categoria", "Portátiles y equipos de sobremesa",
                        "precio", 100 + i % 400))
                .toList();
    }
}
