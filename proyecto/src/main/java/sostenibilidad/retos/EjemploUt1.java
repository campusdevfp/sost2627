package sostenibilidad.retos;

import sostenibilidad.Ut1Asg;

/** Reto de ejemplo UT1 · Radar ASG de una empresa. Ejecuta el main y lee el informe. */
public class EjemploUt1 {

    static final String[] NOMBRES = {
            "Electricidad renovable en el CPD (%)", "Emisiones alcance 1 y 2 (t CO2e)",
            "Residuos electronicos reutilizados (%)", "Horas de formacion por persona",
            "Brecha salarial de genero (%)", "Plantilla formada en codigo etico (%)"};
    static final String[] DIMENSIONES = {"AMBIENTAL", "AMBIENTAL", "AMBIENTAL", "SOCIAL", "SOCIAL", "GOBERNANZA"};
    static final double[] VALORES = {72, 410, 38, 28, 9, 91};
    static final double[] METAS = {100, 350, 60, 40, 5, 100};
    static final boolean[] MAYOR_ES_MEJOR = {true, false, true, true, false, true};
    static final int[] ODS = {7, 13, 13, 12, 4, 5, 16};

    public static void main(String[] args) {
        System.out.println("RADAR ASG · NubeVerde Hosting S.L.");
        System.out.println("=".repeat(62));

        for (int i = 0; i < NOMBRES.length; i++) {
            double c = Ut1Asg.cumplimiento(VALORES[i], METAS[i], MAYOR_ES_MEJOR[i]);
            System.out.printf("%-42s %s %s%n", recortar(NOMBRES[i]), barra(c), Ut1Asg.semaforo(c));
        }

        double a = puntuacion(new int[]{0, 1, 2});
        double s = puntuacion(new int[]{3, 4});
        double g = puntuacion(new int[]{5});
        double total = Ut1Asg.puntuacionAsg(a, s, g);

        System.out.println("-".repeat(62));
        System.out.printf("Ambiental %5.1f | Social %5.1f | Gobernanza %5.1f%n", a, s, g);
        System.out.printf("PUNTUACION ASG: %.1f  ->  RATING %s%n", total, Ut1Asg.rating(total));
        System.out.print("ODS cubiertos:");
        for (int n : Ut1Asg.odsCubiertos(ODS)) System.out.print(" " + n);
        System.out.println();
        System.out.println("Dimension mas debil: " + (s < a && s < g ? "SOCIAL" : a < g ? "AMBIENTAL" : "GOBERNANZA"));
    }

    static double puntuacion(int[] indices) {
        double[] c = new double[indices.length];
        for (int i = 0; i < indices.length; i++) {
            c[i] = Ut1Asg.cumplimiento(VALORES[indices[i]], METAS[indices[i]], MAYOR_ES_MEJOR[indices[i]]);
        }
        return Ut1Asg.puntuacionDimension(c);
    }

    static String barra(double cumplimiento) {
        int lleno = (int) Math.round(cumplimiento * 10);
        return "[" + "#".repeat(lleno) + ".".repeat(10 - lleno) + "]";
    }

    static String recortar(String s) {
        return s.length() > 40 ? s.substring(0, 39) + "." : s;
    }
}
