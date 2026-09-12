package es.daw.sostenibilidad.circular.web;

import es.daw.sostenibilidad.circular.modelo.Equipo;
import es.daw.sostenibilidad.circular.modelo.EstadoEquipo;
import es.daw.sostenibilidad.circular.servicio.CircularidadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * API de ReUsa. Ya está completa.
 * <ul>
 *   <li>GET   /api/equipos</li>
 *   <li>PATCH /api/equipos/{id}/estado?nuevo=DONADO   (409 si la transición no está permitida)</li>
 *   <li>GET   /api/circularidad</li>
 *   <li>GET   /api/candidatos?edadMinima=4</li>
 * </ul>
 */
@RestController
@RequestMapping("/api")
public class EquipoController {

    private final EquipoRepository repositorio;
    private final CircularidadService servicio;

    public EquipoController(EquipoRepository repositorio, CircularidadService servicio) {
        this.repositorio = repositorio;
        this.servicio = servicio;
    }

    @GetMapping("/equipos")
    public List<Equipo> equipos() {
        return repositorio.findAll();
    }

    @PatchMapping("/equipos/{id}/estado")
    @Transactional
    public Equipo cambiarEstado(@PathVariable Long id, @RequestParam EstadoEquipo nuevo) {
        Equipo equipo = repositorio.findById(id).orElseThrow();
        servicio.cambiarEstado(equipo, nuevo);
        return repositorio.save(equipo);
    }

    @GetMapping("/circularidad")
    public Map<String, Object> circularidad() {
        return Map.of("tasaCircularidad_pct", servicio.tasaCircularidad(repositorio.findAll()));
    }

    @GetMapping("/candidatos")
    public List<Equipo> candidatos(@RequestParam(defaultValue = "4") int edadMinima) {
        return servicio.candidatosReacondicionar(repositorio.findAll(), Year.now().getValue(), edadMinima);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> transicionNoPermitida(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> noEncontrado() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Equipo no encontrado"));
    }
}
