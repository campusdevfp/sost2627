package es.daw.sostenibilidad.huella.web;

import es.daw.sostenibilidad.huella.servicio.HuellaService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

/**
 * Filtro que mide cada respuesta de /api: tiempo, bytes y gramos de CO2 estimados.
 * Mira la consola al llamar a los endpoints. Ya está completo.
 */
@Component
public class MedicionFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(MedicionFilter.class);

    private final HuellaService huella;
    private final double intensidad;

    public MedicionFilter(HuellaService huella,
                          @Value("${huella.intensidad-g-kwh:150}") double intensidad) {
        this.huella = huella;
        this.intensidad = intensidad;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().startsWith("/api");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        ContentCachingResponseWrapper envoltorio = new ContentCachingResponseWrapper(response);
        long inicio = System.nanoTime();
        try {
            chain.doFilter(request, envoltorio);
        } finally {
            long ms = (System.nanoTime() - inicio) / 1_000_000;
            long bytes = envoltorio.getContentSize();
            double gramos = huella.gramosCo2(bytes, intensidad);
            log.info("[HUELLA] {} {} -> {} ms, {} bytes (sin comprimir), {} g CO2, etiqueta {}",
                    request.getMethod(), request.getRequestURI(), ms, bytes,
                    String.format("%.4f", gramos), huella.etiqueta(gramos));
            envoltorio.copyBodyToResponse();
        }
    }
}
