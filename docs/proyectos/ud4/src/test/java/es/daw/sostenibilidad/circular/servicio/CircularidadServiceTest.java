package es.daw.sostenibilidad.circular.servicio;

import es.daw.sostenibilidad.circular.modelo.Equipo;
import es.daw.sostenibilidad.circular.modelo.EstadoEquipo;
import es.daw.sostenibilidad.circular.modelo.OpcionR;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Set;

import static es.daw.sostenibilidad.circular.modelo.EstadoEquipo.*;
import static org.junit.jupiter.api.Assertions.*;

/** Tests de ReUsa (RA4). Son la especificación: no se modifican. */
class CircularidadServiceTest {

    private final CircularidadService servicio = new CircularidadService();

    private static Equipo equipo(String etiqueta, int anio, EstadoEquipo estado) {
        return new Equipo(etiqueta, "Portátil", anio, estado, 300, 12);
    }

    @ParameterizedTest
    @CsvSource({
            "EN_USO,EN_REPARACION,true", "EN_USO,REACONDICIONADO,true", "EN_USO,DONADO,true", "EN_USO,RECICLADO,true",
            "EN_REPARACION,EN_USO,true", "EN_REPARACION,REACONDICIONADO,true", "EN_REPARACION,RECICLADO,true",
            "EN_REPARACION,DONADO,false",
            "REACONDICIONADO,EN_USO,true", "REACONDICIONADO,DONADO,true", "REACONDICIONADO,RECICLADO,false",
            "DONADO,EN_USO,false", "RECICLADO,EN_USO,false", "EN_USO,EN_USO,false"})
    void transicionesDelCicloDeVida(EstadoEquipo desde, EstadoEquipo hasta, boolean esperado) {
        assertEquals(esperado, servicio.transicionPermitida(desde, hasta));
    }

    @Test
    void cambiaElEstadoSiLaTransicionEsValida() {
        Equipo e = equipo("PORT-001", 2019, EN_USO);
        servicio.cambiarEstado(e, EN_REPARACION);
        assertEquals(EN_REPARACION, e.getEstado());
    }

    @Test
    void transicionNoPermitidaLanzaIllegalStateExceptionConLosEstados() {
        Equipo e = equipo("MONI-004", 2014, RECICLADO);
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> servicio.cambiarEstado(e, EN_USO));
        assertTrue(ex.getMessage().contains("RECICLADO"));
        assertTrue(ex.getMessage().contains("EN_USO"));
        assertEquals(RECICLADO, e.getEstado(), "el estado no debe cambiar si la transición falla");
    }

    @Test
    void huellaAnualReparteLaFabricacion() {
        // 300 / 4 + 12 = 87.0
        assertEquals(87.0, servicio.huellaAnual(300, 12, 4), 1e-9);
        // 350 / 3 + 45 = 161.666... -> 161.7
        assertEquals(161.7, servicio.huellaAnual(350, 45, 3), 1e-9);
    }

    @Test
    void huellaAnualConVidaNoPositivaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> servicio.huellaAnual(300, 12, 0));
    }

    @Test
    void ahorroAnualAlAlargarLaVida() {
        // 300/4 - 300/6 = 75 - 50 = 25.0
        assertEquals(25.0, servicio.ahorroAnualAmpliandoVida(300, 4, 2), 1e-9);
        // 280/3 - 280/5 = 93.33 - 56 = 37.33 -> 37.3
        assertEquals(37.3, servicio.ahorroAnualAmpliandoVida(280, 3, 2), 1e-9);
        assertEquals(0.0, servicio.ahorroAnualAmpliandoVida(280, 3, 0), 1e-9);
    }

    @Test
    void ahorroConParametrosInvalidosLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> servicio.ahorroAnualAmpliandoVida(300, 0, 2));
        assertThrows(IllegalArgumentException.class, () -> servicio.ahorroAnualAmpliandoVida(300, 4, -1));
    }

    @Test
    void mejorOpcionSigueLaJerarquiaDeLasR() {
        assertEquals(OpcionR.REPARAR, servicio.mejorOpcion(Set.of(OpcionR.RECICLAR, OpcionR.REPARAR, OpcionR.VALORIZAR)));
        assertEquals(OpcionR.REUTILIZAR, servicio.mejorOpcion(Set.of(OpcionR.REACONDICIONAR, OpcionR.REUTILIZAR)));
        assertEquals(OpcionR.ELIMINAR, servicio.mejorOpcion(Set.of()));
    }

    @Test
    void tasaDeCircularidad() {
        List<Equipo> inventario = List.of(
                equipo("A", 2015, DONADO),
                equipo("B", 2016, REACONDICIONADO),
                equipo("C", 2014, RECICLADO),
                equipo("D", 2020, EN_USO));          // no está retirado: no cuenta
        // 2 con segunda vida de 3 retirados -> 66.7 %
        assertEquals(66.7, servicio.tasaCircularidad(inventario), 1e-9);
    }

    @Test
    void tasaDeCircularidadSinRetiradosEsCero() {
        assertEquals(0.0, servicio.tasaCircularidad(List.of(equipo("D", 2020, EN_USO))), 1e-9);
    }

    @Test
    void candidatosAReacondicionarOrdenados() {
        List<Equipo> inventario = List.of(
                equipo("PORT-B", 2019, EN_USO),
                equipo("PORT-A", 2019, EN_REPARACION),
                equipo("SOBR-1", 2017, EN_USO),
                equipo("PORT-N", 2024, EN_USO),          // demasiado nuevo
                equipo("MONI-1", 2010, DONADO));         // ya no está en el inventario activo
        List<String> etiquetas = servicio.candidatosReacondicionar(inventario, 2026, 5).stream()
                .map(Equipo::getEtiqueta).toList();
        assertEquals(List.of("SOBR-1", "PORT-A", "PORT-B"), etiquetas);
    }
}
