package sostenibilidad.retos;

import sostenibilidad.Ut2Energia;

/** Reto de ejemplo UT2 · ¿Qué día lanzar el proceso de copias? */

public class EjemploUt2 {
    public static void main(String[] args) {
        String[] tecnologias = {"Eólica", "Solar fotovoltaica", "Nuclear", "Ciclo combinado", "Carbón"};
        String[] dias = {"lunes", "martes", "miércoles"};
        double[][] mwh = {
            {400, 100, 300, 200,   0},     // lunes
            {200, 100, 300, 300, 100},     // martes
            {100, 200, 300, 400,   0}};    // miércoles
        double kwhProceso = 50;

        // Paso 1: intensidad, % renovable y semáforo de cada día
        double[] intensidades = new double[dias.length];
        for (int d = 0; d < dias.length; d++) {
            intensidades[d] = Ut2Energia.intensidad(tecnologias, mwh[d]);
            double renovable = Ut2Energia.porcentajeRenovable(tecnologias, mwh[d]);
            System.out.printf("%-10s %6.1f g/kWh  %5.1f %% renovable  %s%n",
                    dias[d], intensidades[d], renovable, Ut2Energia.semaforoRed(intensidades[d]));
        }

        // Paso 2: el día más limpio y el más sucio
        int mejor = Ut2Energia.diaMasLimpio(intensidades);
        int peor = 0;
        for (int i = 1; i < intensidades.length; i++) {
            if (intensidades[i] > intensidades[peor]) peor = i;
        }

        // Paso 3: kilos que emite el proceso cada uno de esos dos días
        double kgMejor = Ut2Energia.kgCo2(kwhProceso, intensidades[mejor]);
        double kgPeor = Ut2Energia.kgCo2(kwhProceso, intensidades[peor]);
        System.out.printf("Mejor día: %s -> %.2f kg%n", dias[mejor], kgMejor);
        System.out.printf("Peor día:  %s -> %.2f kg%n", dias[peor], kgPeor);

        // Paso 4: el ahorro, por semana y en un año
        double ahorro = kgPeor - kgMejor;
        System.out.printf("Ahorro: %.2f kg por semana, %.1f kg al año%n", ahorro, ahorro * 52);
    }
}
