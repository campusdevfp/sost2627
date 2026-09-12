package es.daw.sostenibilidad.huella.web;

import es.daw.sostenibilidad.huella.modelo.FichaAmbiental;
import es.daw.sostenibilidad.huella.modelo.Producto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/** Carga 2000 productos con su ficha ambiental al arrancar, para que las mediciones se noten. */
@Component
public class DatosIniciales implements CommandLineRunner {

    private static final String[] ORIGENES = {"Empresa", "Administración pública", "Centro educativo", "Particular"};

    private final ProductoRepository productos;

    public DatosIniciales(ProductoRepository productos) {
        this.productos = productos;
    }

    @Override
    public void run(String... args) {
        List<Producto> lista = new ArrayList<>();
        for (int i = 1; i <= 2000; i++) {
            FichaAmbiental ficha = new FichaAmbiental(200 + i % 250, 1 + i % 10, ORIGENES[i % ORIGENES.length]);
            lista.add(new Producto("Portátil reacondicionado " + i, 150 + i % 500, ficha));
        }
        productos.saveAll(lista);   // la ficha se guarda en cascada
    }
}
