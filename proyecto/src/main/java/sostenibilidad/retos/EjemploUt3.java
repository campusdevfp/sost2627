package sostenibilidad.retos;

import sostenibilidad.Ut3Desarrollo;

/** Reto de ejemplo UT3 · Auditoria de una pagina y huella del desplazamiento. */
public class EjemploUt3 {

    static final String[] URLS = {"/", "/estilo.css", "/app.js", "/api/catalogo", "/cabecera.png"};
    static final String[] TIPOS = {"text/html; charset=UTF-8", "text/css", "application/javascript",
                                   "application/json", "image/png"};
    static final long[] BYTES = {1250, 520, 48000, 366000, 780000};
    static final boolean[] COMPRIMIDO = {false, false, false, false, false};
    static final String[] CACHE = {null, null, "max-age=600", null, "max-age=600"};

    public static void main(String[] args) {
        System.out.println("AUDITORIA WEB · pagina principal");
        System.out.println("=".repeat(66));
        System.out.printf("%-16s %10s %6s  %s%n", "Recurso", "bytes", "nota", "que arreglar");

        for (int i = 0; i < URLS.length; i++) {
            int nota = Ut3Desarrollo.puntuacionRecurso(TIPOS[i], BYTES[i], COMPRIMIDO[i], CACHE[i]);
            System.out.printf("%-16s %10d %6d  %s%n", URLS[i], BYTES[i], nota, diagnostico(i));
        }

        long total = Ut3Desarrollo.pesoTotal(BYTES);
        System.out.println("-".repeat(66));
        System.out.printf("Peso total: %d bytes (%.0f kB)  ->  %s%n",
                total, total / 1000.0, Ut3Desarrollo.etiquetaPeso(total));

        // Comprimiendo los tres recursos de texto se quedarian en aprox. un 8% de su tamano
        long comprimidos = 100 + 60 + 12000 + 26000 + 780000;
        System.out.printf("Comprimiendo texto: %d bytes  ->  reduccion del %.1f%%%n",
                comprimidos, Ut3Desarrollo.reduccion(total, comprimidos));

        System.out.println("-".repeat(66));
        System.out.println("DESPLAZAMIENTO AL TRABAJO (12 km, 5 dias/semana, 42 semanas)");
        for (String medio : new String[]{"coche", "moto", "autobus", "tren", "bici"}) {
            double kg = Ut3Desarrollo.kgDesplazamiento(medio, 12, 5, 42);
            System.out.printf("  %-8s %8.1f kg/ano  %s%n", medio, kg, "#".repeat((int) (kg / 40)));
        }
        System.out.printf("Cambiar coche por autobus ahorra %.1f kg al ano%n",
                Ut3Desarrollo.ahorroCambio("coche", "autobus", 12, 5, 42));
    }

    static String diagnostico(int i) {
        StringBuilder sb = new StringBuilder();
        if (Ut3Desarrollo.esComprimible(TIPOS[i]) && !COMPRIMIDO[i]) sb.append("comprimir ");
        if (Ut3Desarrollo.maxAge(CACHE[i]) == 0) sb.append("cachear ");
        if (BYTES[i] > 500_000) sb.append("pesa demasiado");
        return sb.isEmpty() ? "ok" : sb.toString().trim();
    }
}
