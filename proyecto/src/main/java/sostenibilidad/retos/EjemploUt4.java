package sostenibilidad.retos;

import sostenibilidad.Ut4Circular;

/** Reto de ejemplo UT4 · Inventario circular del aula. */
public class EjemploUt4 {

    static final int ANIO = 2026;
    static final String[] ETIQUETA = {"PORT-001", "PORT-002", "SOBR-010", "SOBR-011", "MONI-003", "MONI-004"};
    static final int[] COMPRA = {2019, 2021, 2017, 2016, 2015, 2014};
    static final String[] ESTADO = {"EN_USO", "EN_REPARACION", "EN_USO", "REACONDICIONADO", "DONADO", "RECICLADO"};
    static final double[] FABRICACION = {300, 300, 350, 350, 400, 400};
    static final double[] USO_ANUAL = {12, 12, 45, 45, 20, 20};

    public static void main(String[] args) {
        System.out.println("INVENTARIO CIRCULAR · aula de informatica " + ANIO);
        System.out.println("=".repeat(72));
        System.out.printf("%-10s %5s %-16s %10s %10s %8s%n",
                "Equipo", "anos", "estado", "huella/ano", "%fabric.", "ahorro+2");

        for (int i = 0; i < ETIQUETA.length; i++) {
            int anos = Math.max(1, ANIO - COMPRA[i]);
            double huella = Ut4Circular.huellaAnual(FABRICACION[i], USO_ANUAL[i], anos);
            double pct = Ut4Circular.porcentajeFabricacion(FABRICACION[i], USO_ANUAL[i], anos);
            double ahorro = Ut4Circular.ahorroAlargarVida(FABRICACION[i], anos, 2);
            System.out.printf("%-10s %5d %-16s %10.1f %9.1f%% %8.1f%n",
                    ETIQUETA[i], anos, ESTADO[i], huella, pct, ahorro);
        }

        System.out.println("-".repeat(72));
        System.out.printf("Tasa de circularidad: %.1f%%%n", Ut4Circular.tasaCircularidad(ESTADO));

        // Que pasa si reciclamos un equipo que todavia funciona
        String[] copia = ESTADO.clone();
        copia[2] = "RECICLADO";
        System.out.printf("Si reciclamos SOBR-010 (que aun funciona): %.1f%%  <- empeora%n",
                Ut4Circular.tasaCircularidad(copia));

        System.out.println("-".repeat(72));
        System.out.println("MEJOR OPCION CIRCULAR SEGUN LO QUE SEA VIABLE");
        String[][] casos = {
                {"REPARAR", "RECICLAR"}, {"REACONDICIONAR", "RECICLAR"}, {"RECICLAR"}, {}};
        for (String[] caso : casos) {
            System.out.printf("  viables %-32s -> %s%n",
                    java.util.Arrays.toString(caso), Ut4Circular.mejorOpcionR(caso));
        }

        System.out.println("-".repeat(72));
        System.out.println("REPARAR O SUSTITUIR");
        System.out.printf("  Portatil nuevo (280 kg fabric., ahorra 4 kg/ano): %d anos en amortizarse%n",
                Ut4Circular.aniosParaAmortizar(280, 4));
        System.out.printf("  Servidor nuevo (900 kg fabric., ahorra 300 kg/ano): %d anos en amortizarse%n",
                Ut4Circular.aniosParaAmortizar(900, 300));

        System.out.println("-".repeat(72));
        try {
            Ut4Circular.cambiarEstado("RECICLADO", "EN_USO");
        } catch (IllegalStateException e) {
            System.out.println("Transicion bloqueada -> " + e.getMessage());
        }
    }
}
