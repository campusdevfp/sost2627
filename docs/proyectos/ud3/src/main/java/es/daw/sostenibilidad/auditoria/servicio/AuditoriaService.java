package es.daw.sostenibilidad.auditoria.servicio;

import es.daw.sostenibilidad.auditoria.modelo.Hallazgo;
import es.daw.sostenibilidad.auditoria.modelo.Recurso;
import es.daw.sostenibilidad.auditoria.modelo.Transporte;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Aplica criterios de sostenibilidad al trabajo diario de un desarrollador web:
 * audita los recursos que sirve una aplicación y calcula la huella de ir a trabajar.
 *
 * Completa cada TODO y borra el {@code throw new UnsupportedOperationException}.
 */
@Service
public class AuditoriaService {

    /** A partir de este tamaño un recurso se considera demasiado pesado. */
    public static final long PESO_MAXIMO_BYTES = 500_000;

    /** A partir de este tamaño un PNG o GIF debería convertirse a un formato moderno. */
    public static final long PESO_MAXIMO_IMAGEN_CLASICA = 100_000;

    /** Codificaciones de compresión que aceptamos como buenas. */
    public static final Set<String> COMPRESIONES = Set.of("gzip", "br", "zstd");

    /**
     * Indica si merece la pena comprimir un tipo de contenido: todo lo que empieza por "text/",
     * "application/json", "application/javascript" e "image/svg+xml".
     * Ignora los parámetros tras el punto y coma y las mayúsculas: "Text/HTML; charset=UTF-8" es comprimible.
     * Un tipo nulo no es comprimible.
     */
    public boolean esComprimible(String tipoContenido) {
        // TODO: quédate con lo que hay antes de ';', pásalo a minúsculas y quita espacios
        throw new UnsupportedOperationException("TODO");
    }

    /** True si el recurso llegó con una compresión de COMPRESIONES (sin distinguir mayúsculas). */
    public boolean usaCompresion(Recurso recurso) {
        // TODO: cuidado con contentEncoding nulo
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Segundos de max-age de una cabecera Cache-Control, p. ej. "public, max-age=3600" -> 3600.
     * Devuelve 0 si la cabecera es nula, no tiene max-age o contiene "no-store".
     */
    public long maxAge(String cacheControl) {
        // TODO: separa por comas, recorta cada parte y busca la que empieza por "max-age="
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Hallazgos de un recurso, en el orden en que están declarados en {@link Hallazgo}:
     * <ol>
     *   <li>SIN_COMPRESION: es comprimible y no usa compresión.</li>
     *   <li>SIN_CACHE: su max-age es 0.</li>
     *   <li>DEMASIADO_PESADO: pesa más de PESO_MAXIMO_BYTES.</li>
     *   <li>FORMATO_IMAGEN_INEFICIENTE: es image/bmp o image/tiff (siempre), o image/png o
     *       image/gif con más de PESO_MAXIMO_IMAGEN_CLASICA bytes.</li>
     * </ol>
     */
    public List<Hallazgo> auditar(Recurso recurso) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Puntuación de 0 a 100 de un conjunto de recursos: parte de 100 y resta la penalización
     * de cada hallazgo de cada recurso. Nunca baja de 0.
     */
    public int puntuacion(List<Recurso> recursos) {
        // TODO: usa auditar(...) y Hallazgo.penalizacion()
        throw new UnsupportedOperationException("TODO");
    }

    /** Bytes totales transferidos. */
    public long pesoTotal(List<Recurso> recursos) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Kg de CO2 de ir y volver al trabajo: factor x km de ida x 2 x días por semana x semanas,
     * redondeado a 1 decimal.
     *
     * @throws IllegalArgumentException si algún número es negativo
     */
    public double kgCo2Desplazamiento(Transporte transporte, double kmIda, int diasPorSemana, int semanas) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Kg de CO2 que se ahorran cambiando de medio de transporte (puede ser negativo si el
     * nuevo contamina más), para los mismos km, días y semanas. Redondeado a 1 decimal.
     */
    public double ahorroCambioTransporte(Transporte actual, Transporte nuevo,
                                         double kmIda, int diasPorSemana, int semanas) {
        // TODO: reutiliza kgCo2Desplazamiento
        throw new UnsupportedOperationException("TODO");
    }
}
