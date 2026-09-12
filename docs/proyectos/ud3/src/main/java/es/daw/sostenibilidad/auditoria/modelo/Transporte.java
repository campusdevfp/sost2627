package es.daw.sostenibilidad.auditoria.modelo;

/**
 * Medios de transporte con su factor de emisión en kg de CO2 por km recorrido por persona.
 * Valores APROXIMADOS con fines didácticos; para cálculos oficiales usa los factores
 * de emisión que publica el Ministerio para la Transición Ecológica (MITECO).
 */
public enum Transporte {
    COCHE(0.16),
    MOTO(0.10),
    AUTOBUS(0.08),
    METRO(0.03),
    TREN(0.03),
    BICI(0.0),
    A_PIE(0.0);

    private final double kgCo2PorKm;

    Transporte(double kgCo2PorKm) {
        this.kgCo2PorKm = kgCo2PorKm;
    }

    public double kgCo2PorKm() {
        return kgCo2PorKm;
    }
}
