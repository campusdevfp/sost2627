package es.daw.sostenibilidad.huella.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/** Ficha ambiental de un producto reacondicionado: su huella y lo reparable que es. */
@Entity
public class FichaAmbiental {

    @Id
    @GeneratedValue
    private Long id;

    private double kgCo2Fabricacion;   // huella de fabricación del equipo original
    private int reparabilidad;         // índice de 1 a 10
    private String origen;             // de dónde procede el equipo

    protected FichaAmbiental() {
    }

    public FichaAmbiental(double kgCo2Fabricacion, int reparabilidad, String origen) {
        this.kgCo2Fabricacion = kgCo2Fabricacion;
        this.reparabilidad = reparabilidad;
        this.origen = origen;
    }

    public Long getId() { return id; }
    public double getKgCo2Fabricacion() { return kgCo2Fabricacion; }
    public int getReparabilidad() { return reparabilidad; }
    public String getOrigen() { return origen; }
}
