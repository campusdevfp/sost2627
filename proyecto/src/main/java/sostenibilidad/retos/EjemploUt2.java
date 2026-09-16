package sostenibilidad.retos;

import sostenibilidad.Ut2Energia;

/** Reto de ejemplo UT2 · Una semana del sistema electrico. */
public class EjemploUt2 {

    static final String[] DIAS = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
    static final String[] TEC = {"Eolica", "Solar fotovoltaica", "Hidraulica", "Nuclear", "Ciclo combinado", "Carbon"};
    // MWh generados cada dia por cada tecnologia (mismo orden que TEC)
    static final double[][] MWH = {
            {230, 110, 60, 150, 90, 4},   // lunes, mucho viento
            {200, 105, 58, 150, 110, 4},
            {120, 118, 55, 150, 170, 5},
            { 75, 100, 50, 150, 210, 8},  // jueves, calma
            {150, 112, 57, 150, 130, 5},
            {245, 120, 62, 150, 80, 3},   // sabado, el mas limpio
            {215, 108, 59, 150, 95, 4}};

    static final double KWH_SERVIDOR_DIA = 12;

    public static void main(String[] args) {
        double[] intensidades = new double[DIAS.length];
        System.out.println("SEMANA ELECTRICA · intensidad de carbono y huella de un servidor");
        System.out.println("=".repeat(70));
        System.out.printf("%-10s %8s %10s %8s %10s%n", "Dia", "g/kWh", "renovable", "semaforo", "kg CO2");

        for (int d = 0; d < DIAS.length; d++) {
            intensidades[d] = Ut2Energia.intensidad(TEC, MWH[d]);
            double renov = Ut2Energia.porcentajeRenovable(TEC, MWH[d]);
            double kg = Ut2Energia.kgCo2(KWH_SERVIDOR_DIA, intensidades[d]);
            System.out.printf("%-10s %8.1f %9.1f%% %8s %10.2f  %s%n",
                    DIAS[d], intensidades[d], renov, Ut2Energia.semaforoRed(intensidades[d]), kg, barra(intensidades[d]));
        }

        int mejor = Ut2Energia.diaMasLimpio(intensidades);
        int peor = 0;
        for (int i = 1; i < intensidades.length; i++) if (intensidades[i] > intensidades[peor]) peor = i;

        System.out.println("-".repeat(70));
        System.out.printf("Mas limpio: %s (%.1f g/kWh) | Mas sucio: %s (%.1f g/kWh)%n",
                DIAS[mejor], intensidades[mejor], DIAS[peor], intensidades[peor]);
        System.out.printf("Diferencia: %.1f veces%n", intensidades[peor] / intensidades[mejor]);

        double ahorro = Ut2Energia.kgCo2(40, intensidades[peor]) - Ut2Energia.kgCo2(40, intensidades[mejor]);
        System.out.printf("Mover una tarea de 40 kWh de %s a %s ahorra %.2f kg de CO2%n",
                DIAS[peor], DIAS[mejor], ahorro);
    }

    static String barra(double intensidad) {
        return "#".repeat((int) Math.round(intensidad / 10));
    }
}
