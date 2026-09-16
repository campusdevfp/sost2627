package sostenibilidad;

/**
 * UT4 · Economía circular y ecodiseño.
 *
 * Completa los ocho métodos. Ejecuta los tests con:  mvn test -Dtest=Ut4CircularTest
 */
public class Ut4Circular {

    /** Estados del ciclo de vida de un equipo. DONADO y RECICLADO son finales. */
    public static final String[] ESTADOS = {"EN_USO", "EN_REPARACION", "REACONDICIONADO", "DONADO", "RECICLADO"};

    /** Jerarquía de las R, de la mejor a la peor opción. */
    public static final String[] OPCIONES_R = {"REDUCIR", "REUTILIZAR", "REPARAR",
                                               "REACONDICIONAR", "RECICLAR", "VALORIZAR", "ELIMINAR"};

    /**
     * Ejercicio 1. Indica si se puede pasar de un estado a otro:
     *   EN_USO          -> EN_REPARACION, REACONDICIONADO, DONADO, RECICLADO
     *   EN_REPARACION   -> EN_USO, REACONDICIONADO, RECICLADO
     *   REACONDICIONADO -> EN_USO, DONADO
     *   DONADO, RECICLADO -> ninguno (son finales)
     * Pasar al mismo estado no está permitido.
     */
    public static boolean transicionPermitida(String desde, String hasta) {
        // TODO: un switch sobre 'desde' que compruebe si 'hasta' está entre los permitidos
        throw new UnsupportedOperationException("TODO ejercicio 1");
    }

    /**
     * Ejercicio 2. Comprueba una transición y, si no está permitida, lanza
     * IllegalStateException con un mensaje que contenga los dos estados.
     * Si está permitida, devuelve el estado nuevo.
     */
    public static String cambiarEstado(String desde, String hasta) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 2");
    }

    /**
     * Ejercicio 3. Huella anual de un equipo en kg de CO2, redondeada a 1 decimal:
     * fabricación repartida entre los años de vida, más la huella de un año de uso.
     * Lanza IllegalArgumentException si los años no son positivos.
     */
    public static double huellaAnual(double kgFabricacion, double kgUsoAnual, int aniosVida) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 3");
    }

    /**
     * Ejercicio 4. Kg de CO2 al año que se ahorran alargando la vida del equipo,
     * redondeados a 1 decimal: fab/aniosActuales - fab/(aniosActuales + aniosExtra).
     * Lanza IllegalArgumentException si aniosActuales no es positivo o aniosExtra es negativo.
     */
    public static double ahorroAlargarVida(double kgFabricacion, int aniosActuales, int aniosExtra) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 4");
    }

    /**
     * Ejercicio 5. Porcentaje del total del ciclo de vida que corresponde a la fabricación,
     * redondeado a 1 decimal. Total = fabricación + uso anual x años.
     */
    public static double porcentajeFabricacion(double kgFabricacion, double kgUsoAnual, int aniosVida) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 5");
    }

    /**
     * Ejercicio 6. Mejor opción de la jerarquía de las R entre las viables:
     * la que aparece antes en OPCIONES_R. Si el array está vacío, devuelve "ELIMINAR".
     * Lanza IllegalArgumentException si alguna opción no existe en OPCIONES_R.
     */
    public static String mejorOpcionR(String[] viables) {
        // TODO: recorre OPCIONES_R en orden y devuelve la primera que esté en viables
        throw new UnsupportedOperationException("TODO ejercicio 6");
    }

    /**
     * Ejercicio 7. Tasa de circularidad, redondeada a 1 decimal:
     * (REACONDICIONADO + DONADO) / (REACONDICIONADO + DONADO + RECICLADO) x 100.
     * Los equipos EN_USO y EN_REPARACION no cuentan. Si no hay retirados, 0.0.
     */
    public static double tasaCircularidad(String[] estados) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 7");
    }

    /**
     * Ejercicio 8. Años que tarda en amortizarse la fabricación de un equipo nuevo
     * con el ahorro anual de consumo que aporta (redondeando hacia arriba).
     * Si el ahorro anual no es positivo, devuelve -1 (nunca compensa).
     * Pista: (int) Math.ceil(...)
     */
    public static int aniosParaAmortizar(double kgFabricacionNuevo, double ahorroUsoAnual) {
        // TODO
        throw new UnsupportedOperationException("TODO ejercicio 8");
    }
}
