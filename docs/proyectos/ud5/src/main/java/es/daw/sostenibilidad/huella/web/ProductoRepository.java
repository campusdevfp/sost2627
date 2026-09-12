package es.daw.sostenibilidad.huella.web;

import es.daw.sostenibilidad.huella.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repositorio de productos. */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // TODO LAB: añade una consulta paginada que traiga cada producto CON su ficha en una sola SELECT.
    // Pista:
    //   @EntityGraph(attributePaths = "ficha")
    //   Page<Producto> findAllBy(Pageable pageable);
}
