package es.daw.sostenibilidad.circular.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Equipo informático del inventario (portátil, sobremesa, monitor...).
 * Guarda su huella de fabricación y de uso para poder razonar sobre su ciclo de vida.
 */
@Entity
public class Equipo {

    @Id
    @GeneratedValue
    private Long id;

    private String etiqueta;          // p. ej. "PORT-017"
    private String tipo;              // "Portátil", "Sobremesa", "Monitor"...
    private int anioCompra;

    @Enumerated(EnumType.STRING)
    private EstadoEquipo estado;

    private double kgFabricacion;  // huella embebida, de la ficha PCF del fabricante
    private double kgUsoAnual;     // electricidad de un año de uso x intensidad de la red

    protected Equipo() {
        // constructor vacío que exige JPA
    }

    public Equipo(String etiqueta, String tipo, int anioCompra, EstadoEquipo estado,
                  double kgFabricacion, double kgUsoAnual) {
        this.etiqueta = etiqueta;
        this.tipo = tipo;
        this.anioCompra = anioCompra;
        this.estado = estado;
        this.kgFabricacion = kgFabricacion;
        this.kgUsoAnual = kgUsoAnual;
    }

    public Long getId() { return id; }
    public String getEtiqueta() { return etiqueta; }
    public String getTipo() { return tipo; }
    public int getAnioCompra() { return anioCompra; }
    public EstadoEquipo getEstado() { return estado; }
    public void setEstado(EstadoEquipo estado) { this.estado = estado; }
    public double getKgFabricacion() { return kgFabricacion; }
    public double getKgUsoAnual() { return kgUsoAnual; }
}
