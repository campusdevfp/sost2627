package es.daw.sostenibilidad.plan.web;

import es.daw.sostenibilidad.plan.modelo.Accion;
import es.daw.sostenibilidad.plan.modelo.AspectoMaterial;
import es.daw.sostenibilidad.plan.modelo.GrupoInteres;
import es.daw.sostenibilidad.plan.servicio.PlanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Genera el informe de sostenibilidad en HTML (listo para imprimir o guardar como PDF).
 * GET /informe?umbral=3.5
 */
@Controller
public class InformeController {

    private final PlanService servicio;
    private final PlanRepositorio datos;

    public InformeController(PlanService servicio, PlanRepositorio datos) {
        this.servicio = servicio;
        this.datos = datos;
    }

    @GetMapping({"/", "/informe"})
    public String informe(@RequestParam(defaultValue = "3.5") double umbral, Model modelo) {
        List<AspectoMaterial> prioritarios = servicio.aspectosPrioritarios(datos.aspectos(), umbral);

        List<Map<String, Object>> grupos = datos.grupos().stream()
                .map((GrupoInteres g) -> Map.<String, Object>of(
                        "nombre", g.nombre(), "influencia", g.influencia(),
                        "interes", g.interes(), "estrategia", servicio.estrategiaGrupo(g)))
                .toList();

        List<Map<String, Object>> materiales = prioritarios.stream()
                .map(a -> Map.<String, Object>of(
                        "nombre", a.nombre(), "dimension", a.dimension(),
                        "impacto", a.impacto(), "financiero", a.financiero(),
                        "prioridad", servicio.prioridad(a)))
                .toList();

        List<Map<String, Object>> acciones = datos.acciones().stream()
                .map((Accion ac) -> {
                    double p = servicio.progreso(ac);
                    return Map.<String, Object>of(
                            "aspecto", ac.aspecto(), "descripcion", ac.descripcion(),
                            "indicador", ac.indicador(), "lineaBase", ac.lineaBase(),
                            "meta", ac.meta(), "valorActual", ac.valorActual(),
                            "progreso", Math.round(p * 100), "estado", servicio.estado(p));
                })
                .toList();

        modelo.addAttribute("empresa", datos.empresa());
        modelo.addAttribute("umbral", umbral);
        modelo.addAttribute("grupos", grupos);
        modelo.addAttribute("materiales", materiales);
        modelo.addAttribute("acciones", acciones);
        modelo.addAttribute("sinAccion", servicio.aspectosSinAccion(prioritarios, datos.acciones()));
        return "informe";
    }
}
