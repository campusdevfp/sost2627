package sostenibilidad.retos;

import sostenibilidad.Ut5Huella;

/** Reto de ejemplo UT5 · Antes y despues de optimizar un endpoint. */
public class EjemploUt5 {

    static final double INTENSIDAD = 150;      // g CO2/kWh de la red
    static final long PETICIONES_DIA = 10_000;

    public static void main(String[] args) {
        System.out.println("MEDIR, OPTIMIZAR, VOLVER A MEDIR");
        System.out.println("=".repeat(68));

        medir("v1 (todo de golpe)", 274_000, 2001, 2000);
        medir("v2 (paginado y sin N+1)", 1_800, 2, 20);

        double antes = Ut5Huella.kgAnuales(274_000, PETICIONES_DIA, INTENSIDAD);
        double despues = Ut5Huella.kgAnuales(1_800, PETICIONES_DIA, INTENSIDAD);
        System.out.println("-".repeat(68));
        System.out.printf("Reduccion por peticion: %.1f%%%n", Ut5Huella.reduccionPorcentual(antes, despues));

        // Comparacion justa: por producto mostrado
        double porProdV1 = Ut5Huella.gramosCo2(274_000, INTENSIDAD) / 2000;
        double porProdV2 = Ut5Huella.gramosCo2(1_800, INTENSIDAD) / 20;
        System.out.printf("Por producto mostrado: %.6f g -> %.6f g  (reduccion real del %.1f%%)%n",
                porProdV1, porProdV2, Ut5Huella.reduccionPorcentual(porProdV1, porProdV2));

        System.out.println("-".repeat(68));
        System.out.println("SCI (carbono por peticion, incluyendo el hardware)");
        System.out.printf("  5 kWh/dia, 4250 g embebidos, 10 000 peticiones -> %.3f g/peticion%n",
                Ut5Huella.sci(5, INTENSIDAD, 4250, 10_000));
        System.out.printf("  el mismo servicio con 20 000 peticiones        -> %.3f g/peticion%n",
                Ut5Huella.sci(5, INTENSIDAD, 4250, 20_000));

        System.out.println("-".repeat(68));
        System.out.println("CARBON-AWARE · prevision de intensidad del dia");
        double[] prevision = new double[24];
        for (int h = 0; h < 24; h++) {
            prevision[h] = 170 - 60 * Math.sin(Math.PI * Math.max(0, h - 6) / 12.0);
            if (h >= 20 || h <= 5) prevision[h] = 175;
        }
        int mejorNoche = Ut5Huella.mejorHora(prevision, 0, 6);
        int mejorDia = Ut5Huella.mejorHora(prevision, 0, 23);
        System.out.printf("  Mejor hora de madrugada (0-6): %02d:00 -> %.1f g/kWh%n", mejorNoche, prevision[mejorNoche]);
        System.out.printf("  Mejor hora de todo el dia:     %02d:00 -> %.1f g/kWh%n", mejorDia, prevision[mejorDia]);
        double copia = 12;   // kWh de una copia de seguridad
        System.out.printf("  Mover la copia (%.0f kWh) de las %02d:00 a las %02d:00 ahorra %.0f g de CO2%n",
                copia, mejorNoche, mejorDia, copia * (prevision[mejorNoche] - prevision[mejorDia]));
    }

    static void medir(String nombre, long bytes, long consultas, int elementos) {
        double g = Ut5Huella.gramosCo2(bytes, INTENSIDAD);
        System.out.printf("%-26s %8d B %6d consultas %8.4f g  etiqueta %s  %s%n",
                nombre, bytes, consultas, g, Ut5Huella.etiqueta(g),
                Ut5Huella.hayNMasUno(consultas, elementos) ? "N+1 !" : "ok");
        System.out.printf("%-26s proyeccion anual: %.1f kg de CO2%n", "",
                Ut5Huella.kgAnuales(bytes, PETICIONES_DIA, INTENSIDAD));
    }
}
