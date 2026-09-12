package es.daw.sostenibilidad.huella.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/**
 * Producto del catálogo. Cada uno tiene su propia ficha ambiental, cargada de forma LAZY:
 * si recorres 2000 productos y pides la ficha de cada uno, Hibernate lanza 1 consulta
 * para la lista y 2000 más, una por ficha (problema N+1).
 */
@Entity
public class Producto {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private double precio;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private FichaAmbiental ficha;

    protected Producto() {
    }

    public Producto(String nombre, double precio, FichaAmbiental ficha) {
        this.nombre = nombre;
        this.precio = precio;
        this.ficha = ficha;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public FichaAmbiental getFicha() { return ficha; }
}
