package es.daw.sostenibilidad.energia.web;

import es.daw.sostenibilidad.energia.modelo.Generacion;
import es.daw.sostenibilidad.energia.servicio.IntensidadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * API del observatorio. Ya está completa.
 * <ul>
 *   <li>GET /api/intensidad            con los datos de ejemplo (sin conexión)</li>
 *   <li>GET /api/intensidad/ree?desde=2026-03-01&amp;hasta=2026-03-07   con datos reales de REE</li>
 *   <li>GET /api/consumo?kwh=250&amp;intensidad=120</li>
 * </ul>
 */
@RestController
@RequestMapping("/api")
public class ObservatorioController {

    private final IntensidadService servicio;
    private final ReeCliente ree;

    public ObservatorioController(IntensidadService servicio, ReeCliente ree) {
        this.servicio = servicio;
        this.ree = ree;
    }

    @GetMapping("/intensidad")
    public Map<String, Object> ejemplo() {
        return resumen(servicio.leerCsv(lineasDeEjemplo()));
    }

    @GetMapping("/intensidad/ree")
    public Map<String, Object> real(@RequestParam LocalDate desde, @RequestParam LocalDate hasta) {
        return resumen(ree.estructuraGeneracion(desde, hasta));
    }

    @GetMapping("/consumo")
    public Map<String, Object> consumo(@RequestParam double kwh, @RequestParam double intensidad) {
        return Map.of("kwh", kwh, "gCO2kWh", intensidad, "kgCO2", servicio.kgCo2DeConsumo(kwh, intensidad));
    }

    private Map<String, Object> resumen(List<Generacion> datos) {
        double intensidad = servicio.intensidad(datos);
        Map<String, Object> r = new LinkedHashMap<>();
        r.put("intensidadMedia_gCO2kWh", intensidad);
        r.put("semaforo", servicio.semaforo(intensidad));
        r.put("renovable_pct", servicio.porcentajeRenovable(datos));
        r.put("emisiones_t", Math.round(servicio.emisionesToneladas(datos)));
        r.put("porDia", servicio.intensidadDiaria(datos));
        r.put("diaMasLimpio", servicio.diaMasLimpio(datos).map(LocalDate::toString).orElse("-"));
        return r;
    }

    private List<String> lineasDeEjemplo() {
        try (InputStream in = getClass().getResourceAsStream("/datos/generacion-ejemplo.csv")) {
            if (in == null) {
                throw new IllegalStateException("Falta datos/generacion-ejemplo.csv");
            }
            return new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)).lines().toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
