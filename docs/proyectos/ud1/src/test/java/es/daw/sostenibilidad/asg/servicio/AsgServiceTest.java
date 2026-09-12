package es.daw.sostenibilidad.asg.servicio;

import es.daw.sostenibilidad.asg.modelo.Dimension;
import es.daw.sostenibilidad.asg.modelo.Indicador;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/** Tests del Radar ASG (RA1). Son la especificación: no se modifican. */
class AsgServiceTest {

    private final AsgService servicio = new AsgService();

    private static Indicador ind(Dimension d, double valor, double meta, boolean mayorEsMejor, Integer... ods) {
        return new Indicador("X", "indicador de prueba", d, valor, meta, mayorEsMejor, Set.of(ods));
    }

    @Test
    void clasificaTemaAmbiental() {
        assertEquals(Optional.of(Dimension.AMBIENTAL), servicio.clasificar("Emisiones de CO2 del centro de datos"));
    }

    @Test
    void clasificaTemaSocialIgnorandoMayusculasYTildes() {
        assertEquals(Optional.of(Dimension.SOCIAL), servicio.clasificar("  HORAS DE FORMACIÓN POR PERSONA  "));
    }

    @Test
    void clasificaTemaDeGobernanza() {
        assertEquals(Optional.of(Dimension.GOBERNANZA), servicio.clasificar("Canal ético de denuncias"));
    }

    @Test
    void biodiversidadEsAmbientalAunqueContengaDivers() {
        assertEquals(Optional.of(Dimension.AMBIENTAL), servicio.clasificar("Protección de la biodiversidad"));
    }

    @Test
    void temaDesconocidoNuloOEnBlancoNoSeClasifica() {
        assertTrue(servicio.clasificar("Color corporativo del logotipo").isEmpty());
        assertTrue(servicio.clasificar("   ").isEmpty());
        assertTrue(servicio.clasificar(null).isEmpty());
    }

    @ParameterizedTest
    @CsvSource({"0,false", "1,true", "17,true", "18,false", "-3,false"})
    void validaNumerosDeOds(int numero, boolean esperado) {
        assertEquals(esperado, servicio.odsValido(numero));
    }

    @Test
    void cumplimientoParcialCuandoMayorEsMejor() {
        assertEquals(0.75, servicio.gradoCumplimiento(ind(Dimension.AMBIENTAL, 60, 80, true)), 1e-9);
    }

    @Test
    void cumplimientoNoPasaDeUnoAunqueSeSupereLaMeta() {
        assertEquals(1.0, servicio.gradoCumplimiento(ind(Dimension.AMBIENTAL, 95, 80, true)), 1e-9);
    }

    @Test
    void cumplimientoCuandoMenorEsMejorYSeSuperaLaMeta() {
        // 125 t emitidas con una meta de 100 t -> 100 / 125
        assertEquals(0.8, servicio.gradoCumplimiento(ind(Dimension.AMBIENTAL, 125, 100, false)), 1e-9);
    }

    @Test
    void cumplimientoCuandoMenorEsMejorYSeAlcanzaLaMeta() {
        assertEquals(1.0, servicio.gradoCumplimiento(ind(Dimension.SOCIAL, 4, 5, false)), 1e-9);
    }

    @Test
    void casosLimiteConMetaCero() {
        assertEquals(1.0, servicio.gradoCumplimiento(ind(Dimension.SOCIAL, 3, 0, true)), 1e-9);
        // objetivo "cero accidentes" y hubo 10 -> cumplimiento 0
        assertEquals(0.0, servicio.gradoCumplimiento(ind(Dimension.SOCIAL, 10, 0, false)), 1e-9);
    }

    @Test
    void puntuacionPorDimensionEsLaMediaEnPorcentajeRedondeada() {
        List<Indicador> lista = List.of(
                ind(Dimension.AMBIENTAL, 60, 80, true),     // 0.75
                ind(Dimension.AMBIENTAL, 1, 3, true),       // 0.3333...
                ind(Dimension.SOCIAL, 125, 100, false));    // 0.8
        Map<Dimension, Double> p = servicio.puntuacionPorDimension(lista);
        assertEquals(54.2, p.get(Dimension.AMBIENTAL), 1e-9);
        assertEquals(80.0, p.get(Dimension.SOCIAL), 1e-9);
    }

    @Test
    void puntuacionPorDimensionIncluyeLasTresDimensiones() {
        Map<Dimension, Double> p = servicio.puntuacionPorDimension(List.of(ind(Dimension.SOCIAL, 1, 1, true)));
        assertEquals(3, p.size());
        assertEquals(0.0, p.get(Dimension.AMBIENTAL), 1e-9);
        assertEquals(0.0, p.get(Dimension.GOBERNANZA), 1e-9);
    }

    @Test
    void puntuacionAsgEsLaMediaPonderada() {
        Map<Dimension, Double> p = Map.of(
                Dimension.AMBIENTAL, 50.0, Dimension.SOCIAL, 80.0, Dimension.GOBERNANZA, 90.0);
        // 50*0.4 + 80*0.3 + 90*0.3 = 71.0
        assertEquals(71.0, servicio.puntuacionAsg(p), 1e-9);
        assertEquals(24.0, servicio.puntuacionAsg(Map.of(Dimension.SOCIAL, 80.0)), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({"100,AAA", "85,AAA", "84.9,AA", "70,AA", "55,A", "40,BBB", "25,BB", "24.9,B", "0,B"})
    void ratingPorTramos(double puntuacion, String esperado) {
        assertEquals(esperado, servicio.rating(puntuacion));
    }

    @Test
    void ratingFueraDeRangoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> servicio.rating(-1));
        assertThrows(IllegalArgumentException.class, () -> servicio.rating(100.5));
    }

    @Test
    void odsCubiertosOrdenadosSinRepetirYSinInvalidos() {
        List<Indicador> lista = List.of(
                ind(Dimension.AMBIENTAL, 1, 1, true, 13, 7),
                ind(Dimension.SOCIAL, 1, 1, true, 5, 13, 99),
                ind(Dimension.GOBERNANZA, 1, 1, true, 16, 0));
        assertEquals(List.of(5, 7, 13, 16), List.copyOf(servicio.odsCubiertos(lista)));
    }
}
