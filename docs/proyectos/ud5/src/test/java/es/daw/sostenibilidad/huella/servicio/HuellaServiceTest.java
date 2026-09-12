package es.daw.sostenibilidad.huella.servicio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/** Tests del laboratorio de huella del software (RA5). Son la especificación: no se modifican. */
class HuellaServiceTest {

    private final HuellaService servicio = new HuellaService();

    @Test
    void energiaDeUnGigabyte() {
        assertEquals(0.81, servicio.kwhTransferencia(1_000_000_000L), 1e-9);
    }

    @Test
    void energiaDeUnaPaginaDeDosMegas() {
        // 2 MB = 0.002 GB -> 0.00162 kWh
        assertEquals(0.00162, servicio.kwhTransferencia(2_000_000L), 1e-12);
        assertEquals(0.0, servicio.kwhTransferencia(0), 1e-12);
    }

    @Test
    void gramosDeCo2DeUnaTransferencia() {
        // 2 MB con 150 g/kWh -> 0.00162 x 150 = 0.243 g
        assertEquals(0.243, servicio.gramosCo2(2_000_000L, 150), 1e-9);
    }

    @Test
    void sciSinHuellaEmbebida() {
        // (10 kWh x 200 g/kWh + 0) / 1000 peticiones = 2.0 g por petición
        assertEquals(2.0, servicio.sci(10, 200, 0, 1000), 1e-9);
    }

    @Test
    void sciConHuellaEmbebida() {
        // (5 x 150 + 4250) / 10000 = 0.5 g por petición
        assertEquals(0.5, servicio.sci(5, 150, 4250, 10_000), 1e-9);
    }

    @Test
    void sciConUnidadesNoPositivasLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> servicio.sci(5, 150, 0, 0));
    }

    @Test
    void reduccionPorcentual() {
        assertEquals(75.0, servicio.reduccionPorcentual(480_000, 120_000), 1e-9);
        assertEquals(33.3, servicio.reduccionPorcentual(3, 2), 1e-9);
        assertEquals(-50.0, servicio.reduccionPorcentual(100, 150), 1e-9);
    }

    @Test
    void reduccionConAntesNoPositivoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> servicio.reduccionPorcentual(0, 10));
    }

    @ParameterizedTest
    @CsvSource({"2001,2000,true", "21,20,true", "1,20,false", "3,2,true", "2,2,false", "2,1,false", "50,2000,false"})
    void detectaSospechaDeNMasUno(long consultas, int elementos, boolean esperado) {
        assertEquals(esperado, servicio.sospechaNMasUno(consultas, elementos));
    }

    @Test
    void mejorHoraDentroDeLaVentana() {
        Map<Integer, Double> prevision = Map.of(
                0, 180.0, 1, 170.0, 2, 160.0, 3, 150.0,
                13, 60.0, 14, 55.0, 15, 70.0, 22, 190.0);
        assertEquals(Optional.of(14), servicio.mejorHora(prevision, 0, 23));
        assertEquals(Optional.of(3), servicio.mejorHora(prevision, 0, 6));
    }

    @Test
    void mejorHoraEnEmpateEsLaMasTemprana() {
        Map<Integer, Double> prevision = Map.of(9, 80.0, 10, 80.0, 11, 95.0);
        assertEquals(Optional.of(9), servicio.mejorHora(prevision, 9, 11));
    }

    @Test
    void mejorHoraSinDatosEnLaVentana() {
        assertEquals(Optional.empty(), servicio.mejorHora(Map.of(13, 60.0), 0, 6));
    }

    @Test
    void decideSiEjecutarAhora() {
        assertTrue(servicio.ejecutarAhora(90, 120));
        assertTrue(servicio.ejecutarAhora(120, 120));
        assertFalse(servicio.ejecutarAhora(121, 120));
    }

    @ParameterizedTest
    @CsvSource({"0.05,A", "0.1,A", "0.15,B", "0.3,C", "0.8,D", "1.2,E", "1.61,F"})
    void etiquetaEnergeticaPorTramos(double gramos, String esperada) {
        assertEquals(esperada, servicio.etiqueta(gramos));
    }
}
