package es.daw.sostenibilidad.huella.web;

import es.daw.sostenibilidad.huella.modelo.Producto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Catálogo del laboratorio.
 * <ul>
 *   <li>GET /api/v1/productos  versión INEFICIENTE: los 2000 productos de golpe y N+1.</li>
 *   <li>GET /api/v2/productos  versión que optimizas tú en el laboratorio (TODO LAB).</li>
 * </ul>
 */
@RestController
@RequestMapping("/api")
public class CatalogoController {

    private final ProductoRepository productos;

    public CatalogoController(ProductoRepository productos) {
        this.productos = productos;
    }

    /** Punto de partida: NO lo cambies, lo necesitas para medir el "antes". */
    @GetMapping("/v1/productos")
    @Transactional(readOnly = true)
    public List<Map<String, Object>> v1() {
        return productos.findAll().stream()
                .map(p -> Map.<String, Object>of(
                        "id", p.getId(),
                        "nombre", p.getNombre(),
                        "precio", p.getPrecio(),
                        "kgCo2Fabricacion", p.getFicha().getKgCo2Fabricacion(),   // <- una consulta por producto
                        "reparabilidad", p.getFicha().getReparabilidad(),
                        "origen", p.getFicha().getOrigen()))
                .toList();
    }

    /**
     * TODO LAB: versión sostenible del mismo listado.
     * 1. Usa la consulta con @EntityGraph para evitar el N+1.
     * 2. Pagina con Pageable (p. ej. 20 elementos por página).
     * 3. Devuelve solo los campos que la vista necesita (un record DTO).
     * 4. (Ampliación) cachea el resultado con @Cacheable.
     */
    @GetMapping("/v2/productos")
    public List<Map<String, Object>> v2() {
        return v1();
    }
}
