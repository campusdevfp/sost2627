package sostenibilidad.retos;

import sostenibilidad.Ut6Plan;

/** Reto de ejemplo UT6 · Analisis del plan de sostenibilidad de una empresa. */
public class EjemploUt6 {

    static final double UMBRAL = 3.5;

    static final String[] GRUPOS = {"Clientes", "Plantilla", "Inversores", "Vecindario del CPD", "Proveedores"};
    static final int[] INFLUENCIA = {5, 3, 5, 2, 2};
    static final int[] INTERES = {4, 5, 2, 4, 2};

    static final String[] ASPECTOS = {"Consumo energetico del CPD", "Privacidad de datos",
            "Uso de agua en refrigeracion", "Residuos electronicos", "Patrocinio deportivo local"};
    static final double[] IMPACTO = {5, 4, 4, 4, 1};
    static final double[] FINANCIERO = {5, 5, 2, 3, 1};
    static final double[] GRUPOS_IMP = {4.5, 5, 3.5, 3, 2};

    static final String[] ACCION_ASPECTO = {"Consumo energetico del CPD", "Residuos electronicos"};
    static final String[] ACCION_NOMBRE = {"Contratar electricidad renovable", "Programa de reacondicionamiento"};
    static final double[] BASE = {40, 10};
    static final double[] META = {100, 60};
    static final double[] ACTUAL = {72, 38};
    static final int[] MESES_HECHOS = {18, 24};
    static final int[] MESES_TOTAL = {36, 36};

    public static void main(String[] args) {
        System.out.println("PLAN DE SOSTENIBILIDAD · NubeVerde Hosting S.L.");
        System.out.println("=".repeat(74));
        System.out.println("1. GRUPOS DE INTERES");
        for (int i = 0; i < GRUPOS.length; i++) {
            System.out.printf("   %-22s influencia %d interes %d -> %s%n",
                    GRUPOS[i], INFLUENCIA[i], INTERES[i], Ut6Plan.estrategiaGrupo(INFLUENCIA[i], INTERES[i]));
        }

        System.out.println("\n2. MATERIALIDAD (umbral " + UMBRAL + ")");
        String[] materiales = new String[ASPECTOS.length];
        double[] prioridades = new double[ASPECTOS.length];
        int n = 0;
        for (int i = 0; i < ASPECTOS.length; i++) {
            String tipo = Ut6Plan.tipoMaterialidad(IMPACTO[i], FINANCIERO[i], UMBRAL);
            double p = Ut6Plan.prioridad(IMPACTO[i], FINANCIERO[i], GRUPOS_IMP[i]);
            System.out.printf("   %-30s %-12s prioridad %.1f%n", ASPECTOS[i], tipo, p);
            if (Ut6Plan.esMaterial(IMPACTO[i], FINANCIERO[i], UMBRAL)) {
                materiales[n] = ASPECTOS[i];
                prioridades[n] = p;
                n++;
            }
        }
        materiales = java.util.Arrays.copyOf(materiales, n);
        prioridades = java.util.Arrays.copyOf(prioridades, n);
        ordenarPorPrioridad(materiales, prioridades);

        System.out.println("\n3. ASPECTOS MATERIALES PRIORIZADOS");
        for (int i = 0; i < materiales.length; i++) {
            System.out.printf("   %d. %-30s %.1f %s%n", i + 1, materiales[i], prioridades[i],
                    "*".repeat((int) Math.round(prioridades[i])));
        }

        System.out.println("\n4. SEGUIMIENTO DE LAS ACCIONES");
        for (int i = 0; i < ACCION_NOMBRE.length; i++) {
            double p = Ut6Plan.progreso(BASE[i], META[i], ACTUAL[i]);
            System.out.printf("   %-34s %3.0f%% %-12s %s%n", ACCION_NOMBRE[i], p * 100, Ut6Plan.estado(p),
                    Ut6Plan.vaEnPlazo(p, MESES_HECHOS[i], MESES_TOTAL[i]) ? "en plazo" : "RETRASADA");
        }

        System.out.println("\n5. HUECOS DEL PLAN");
        String[] sin = Ut6Plan.aspectosSinAccion(materiales, ACCION_ASPECTO);
        for (String s : sin) System.out.println("   SIN ACCION -> " + s);
        System.out.printf("   Cobertura: %.0f%% de los aspectos materiales tienen accion%n",
                (materiales.length - sin.length) * 100.0 / materiales.length);
    }

    /** Ordena los dos arrays a la vez, de mayor a menor prioridad (seleccion directa). */
    static void ordenarPorPrioridad(String[] nombres, double[] prioridades) {
        for (int i = 0; i < prioridades.length - 1; i++) {
            int mayor = i;
            for (int j = i + 1; j < prioridades.length; j++) {
                if (prioridades[j] > prioridades[mayor]) mayor = j;
            }
            double p = prioridades[i]; prioridades[i] = prioridades[mayor]; prioridades[mayor] = p;
            String s = nombres[i]; nombres[i] = nombres[mayor]; nombres[mayor] = s;
        }
    }
}
