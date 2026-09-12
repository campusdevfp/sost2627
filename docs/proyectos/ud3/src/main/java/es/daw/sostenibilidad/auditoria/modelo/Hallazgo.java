package es.daw.sostenibilidad.auditoria.modelo;

/** Problemas de sostenibilidad que detecta el auditor y los puntos que resta cada uno. */
public enum Hallazgo {
    SIN_COMPRESION(10, "Activa gzip o Brotli en el servidor"),
    SIN_CACHE(5, "Añade Cache-Control con max-age a los recursos estáticos"),
    DEMASIADO_PESADO(15, "Reduce el tamaño: minifica, pagina o recorta"),
    FORMATO_IMAGEN_INEFICIENTE(10, "Convierte la imagen a WebP o AVIF");

    private final int penalizacion;
    private final String recomendacion;

    Hallazgo(int penalizacion, String recomendacion) {
        this.penalizacion = penalizacion;
        this.recomendacion = recomendacion;
    }

    public int penalizacion() {
        return penalizacion;
    }

    public String recomendacion() {
        return recomendacion;
    }
}
