package es.daw.sostenibilidad.circular.web;

import es.daw.sostenibilidad.circular.modelo.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repositorio JPA de equipos: CRUD completo sin escribir SQL. */
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}
