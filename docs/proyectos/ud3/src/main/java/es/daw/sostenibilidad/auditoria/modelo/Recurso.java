package es.daw.sostenibilidad.auditoria.modelo;

/**
 * Un recurso descargado por el navegador al abrir una página (HTML, CSS, JS, imagen, JSON...).
 * Los datos salen de la pestaña Red (Network) de las herramientas de desarrollo o de {@code curl -I}.
 *
 * @param url             ruta del recurso
 * @param tipoContenido   cabecera Content-Type, p. ej. "text/css; charset=UTF-8"
 * @param bytes           bytes transferidos
 * @param contentEncoding cabecera Content-Encoding ("gzip", "br"...) o null si no viene
 * @param cacheControl    cabecera Cache-Control o null si no viene
 */
public record Recurso(String url, String tipoContenido, long bytes,
                      String contentEncoding, String cacheControl) {
}
