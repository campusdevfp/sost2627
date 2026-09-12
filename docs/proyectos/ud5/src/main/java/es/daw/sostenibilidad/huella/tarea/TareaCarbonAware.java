package es.daw.sostenibilidad.huella.tarea;

import es.daw.sostenibilidad.huella.servicio.HuellaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Tarea pesada (generar informes, copias, reindexar...) que debería ejecutarse solo
 * cuando la electricidad es más limpia.
 */
@Component
public class TareaCarbonAware {

    private static final Logger log = LoggerFactory.getLogger(TareaCarbonAware.class);

    private final HuellaService huella;
    private final double umbral;

    public TareaCarbonAware(HuellaService huella, @Value("${huella.umbral-g-kwh:120}") double umbral) {
        this.huella = huella;
        this.umbral = umbral;
    }

    /**
     * TODO RA5 (criterio del RA):
     * 1. Programa este método con @Scheduled para que se compruebe cada hora
     *    (en clase, para verlo funcionar, usa fixedRate = 30000).
     * 2. Obtén la intensidad actual con intensidadActual().
     * 3. Usa huella.ejecutarAhora(...) para decidir si generar el informe o aplazarlo,
     *    y deja constancia en el log de lo que se decide y por qué.
     */
    public void generarInformeSiLaRedEstaLimpia() {
        // TODO
    }

    /**
     * Intensidad actual de la red. En el laboratorio la simulamos; en la ampliación la
     * obtienes de REE con el cliente de la UD2.
     */
    double intensidadActual() {
        return 60 + Math.random() * 180;
    }
}
