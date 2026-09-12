package es.daw.sostenibilidad.plan.web;

import es.daw.sostenibilidad.plan.modelo.Accion;
import es.daw.sostenibilidad.plan.modelo.AspectoMaterial;
import es.daw.sostenibilidad.plan.modelo.Dimension;
import es.daw.sostenibilidad.plan.modelo.GrupoInteres;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Datos del plan de una empresa inventada, NubeVerde Hosting S.L.
 * En el laboratorio los sustituyes por los de la empresa real que analices.
 */
@Component
public class PlanRepositorio {

    public String empresa() {
        return "NubeVerde Hosting S.L.";
    }

    public List<GrupoInteres> grupos() {
        return List.of(
                new GrupoInteres("Clientes", 5, 4),
                new GrupoInteres("Plantilla", 3, 5),
                new GrupoInteres("Inversores", 5, 2),
                new GrupoInteres("Vecindario del CPD", 2, 4),
                new GrupoInteres("Proveedores de hardware", 2, 2));
    }

    public List<AspectoMaterial> aspectos() {
        return List.of(
                new AspectoMaterial("Consumo energético del CPD", Dimension.AMBIENTAL, 5, 5, 4.5),
                new AspectoMaterial("Uso de agua en refrigeración", Dimension.AMBIENTAL, 4, 2, 3.5),
                new AspectoMaterial("Residuos electrónicos", Dimension.AMBIENTAL, 4, 3, 3),
                new AspectoMaterial("Privacidad y seguridad de los datos", Dimension.GOBERNANZA, 4, 5, 5),
                new AspectoMaterial("Accesibilidad de los servicios", Dimension.SOCIAL, 3, 2, 3.5),
                new AspectoMaterial("Formación de la plantilla", Dimension.SOCIAL, 2, 3, 3),
                new AspectoMaterial("Patrocinio deportivo local", Dimension.SOCIAL, 1, 1, 2));
    }

    public List<Accion> acciones() {
        return List.of(
                new Accion("Consumo energético del CPD", "Contratar electricidad con garantía de origen renovable",
                        "Electricidad renovable (%)", 40, 100, 72),
                new Accion("Consumo energético del CPD", "Consolidar servidores infrautilizados",
                        "PUE del centro de datos", 1.8, 1.4, 1.6),
                new Accion("Residuos electrónicos", "Acuerdo de reacondicionamiento con una entidad social",
                        "Equipos retirados con segunda vida (%)", 10, 60, 38),
                new Accion("Privacidad y seguridad de los datos", "Certificación ISO/IEC 27001",
                        "Controles implantados (%)", 30, 100, 100));
    }
}
