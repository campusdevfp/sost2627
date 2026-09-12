package es.daw.sostenibilidad.energia.servicio;

import es.daw.sostenibilidad.energia.modelo.Generacion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/** Tests del observatorio energético (RA2). Son la especificación: no se modifican. */
class IntensidadServiceTest {

    private final IntensidadService servicio = new IntensidadService();

    private static final LocalDate D1 = LocalDate.of(2026, 3, 2);
    private static final LocalDate D2 = LocalDate.of(2026, 3, 3);

    // Día 1: mucho viento. 200 MWh de ciclo combinado -> 74 t en 1000 MWh -> 74 g/kWh
    private static final List<Generacion> DIA1 = List.of(
            new Generacion(D1, "Eólica", 300),
            new Generacion(D1, "Ciclo combinado", 200),
            new Generacion(D1, "Nuclear", 500));

    // Día 2: poco viento. 95 t (carbón) + 111 t (ciclo) en 500 MWh -> 412 g/kWh
    private static final List<Generacion> DIA2 = List.of(
            new Generacion(D2, "Carbón", 100),
            new Generacion(D2, "Ciclo combinado", 300),
            new Generacion(D2, "Solar fotovoltaica", 100));

    private static List<Generacion> semana() {
        return java.util.stream.Stream.concat(DIA2.stream(), DIA1.stream()).toList();
    }

    @Test
    void normalizaNombresDeTecnologia() {
        assertEquals("eolica", servicio.normalizar("  Eólica "));
        assertEquals("cogeneracion", servicio.normalizar("COGENERACIÓN"));
    }

    @Test
    void factorDeEmisionConocidoYDesconocido() {
        assertEquals(0.95, servicio.factorEmision("Carbón"), 1e-9);
        assertEquals(0.37, servicio.factorEmision("ciclo combinado"), 1e-9);
        assertEquals(0.0, servicio.factorEmision("Nuclear"), 1e-9);
        assertEquals(0.0, servicio.factorEmision("Tecnología inventada"), 1e-9);
    }

    @Test
    void emisionesEnToneladas() {
        assertEquals(74.0, servicio.emisionesToneladas(DIA1), 1e-9);
        assertEquals(206.0, servicio.emisionesToneladas(DIA2), 1e-9);
    }

    @Test
    void intensidadDeUnDia() {
        assertEquals(74.0, servicio.intensidad(DIA1), 1e-9);
        assertEquals(412.0, servicio.intensidad(DIA2), 1e-9);
    }

    @Test
    void intensidadDeVariosDiasRedondeada() {
        // 280 t / 1500 MWh x 1000 = 186.666... -> 186.7
        assertEquals(186.7, servicio.intensidad(semana()), 1e-9);
    }

    @Test
    void intensidadSinDatosEsCero() {
        assertEquals(0.0, servicio.intensidad(List.of()), 1e-9);
        assertEquals(0.0, servicio.intensidad(List.of(new Generacion(D1, "Eólica", 0))), 1e-9);
    }

    @Test
    void porcentajeRenovable() {
        assertEquals(30.0, servicio.porcentajeRenovable(DIA1), 1e-9);
        assertEquals(26.7, servicio.porcentajeRenovable(semana()), 1e-9);
        assertEquals(0.0, servicio.porcentajeRenovable(List.of()), 1e-9);
    }

    @Test
    void intensidadDiariaOrdenadaPorFecha() {
        Map<LocalDate, Double> porDia = servicio.intensidadDiaria(semana());
        assertEquals(List.of(D1, D2), List.copyOf(porDia.keySet()));
        assertEquals(74.0, porDia.get(D1), 1e-9);
        assertEquals(412.0, porDia.get(D2), 1e-9);
    }

    @Test
    void diaMasLimpio() {
        assertEquals(Optional.of(D1), servicio.diaMasLimpio(semana()));
        assertEquals(Optional.empty(), servicio.diaMasLimpio(List.of()));
    }

    @ParameterizedTest
    @CsvSource({"0,VERDE", "99.9,VERDE", "100,AMBAR", "199.9,AMBAR", "200,ROJO", "412,ROJO"})
    void semaforoPorTramos(double intensidad, String esperado) {
        assertEquals(esperado, servicio.semaforo(intensidad));
    }

    @Test
    void semaforoConIntensidadNegativaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> servicio.semaforo(-5));
    }

    @Test
    void kilosDeCo2DeUnConsumo() {
        // 250 kWh a 120 g/kWh = 30 kg
        assertEquals(30.0, servicio.kgCo2DeConsumo(250, 120), 1e-9);
        assertEquals(0.41, servicio.kgCo2DeConsumo(3.3, 123.4), 1e-9);
    }

    @Test
    void leeUnCsvSaltandoCabeceraYLineasEnBlanco() {
        List<String> lineas = List.of(
                "fecha;tecnologia;mwh",
                "2026-03-02;Eólica;300",
                "",
                "2026-03-02;Ciclo combinado;200.5");
        List<Generacion> datos = servicio.leerCsv(lineas);
        assertEquals(2, datos.size());
        assertEquals(new Generacion(D1, "Eólica", 300), datos.get(0));
        assertEquals(200.5, datos.get(1).mwh(), 1e-9);
    }
}
