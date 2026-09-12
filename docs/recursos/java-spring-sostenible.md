# Java y Spring Boot sostenibles

En este módulo **Spring Boot es el vehículo, no el objetivo**: ya lo aprendes en DWES. Aquí lo usamos para que las decisiones de sostenibilidad se conviertan en código que se ejecuta, se prueba y se mide.

## Las piezas que usarás

| Pieza | Para qué la usamos | Unidad |
|---|---|---|
| `enum` y `EnumMap` | Dimensiones ASG, estados del ciclo de vida, jerarquía de las R | UD1, UD4 |
| `record` | Datos inmutables: indicadores, generación, recursos, acciones | todas |
| API de **Streams** y `Collectors` | Agregar series de datos: sumas, medias, agrupar por día | UD2 |
| `Optional` | Resultados que pueden no existir (día más limpio, mejor hora) | UD1, UD2, UD5 |
| `Comparator` | Ordenar por prioridad, antigüedad o intensidad | UD4, UD6 |
| `RestClient` | Consumir la API de Red Eléctrica | UD2 |
| `server.compression.*` y `Cache-Control` | Enviar menos bytes por la red | UD3 |
| **JPA** (`@Entity`, `JpaRepository`, `@EntityGraph`) | Inventario de equipos y evitar consultas N+1 | UD4, UD5 |
| **Actuator** y estadísticas de Hibernate | Medir tiempos y consultas | UD5 |
| `@Scheduled` y `@Cacheable` | Programar tareas en horas limpias y no repetir trabajo | UD5 |
| **Thymeleaf** | Generar el informe de sostenibilidad | UD6 |

## Cómo están hechos los proyectos

Todos siguen la misma separación:

```text
modelo/     ← records, enums y entidades: los datos
servicio/   ← la lógica de sostenibilidad (TU CÓDIGO): Java puro, sin web ni base de datos
web/        ← controladores, repositorios y clientes HTTP (ya hechos)
```

La lógica vive en el **servicio** y no depende de Spring para funcionar: los tests crean el servicio con `new` y lo prueban en milisegundos, sin arrancar la aplicación. Por eso los tests son rápidos y el mismo servicio sirve para la API REST, para una tarea programada o para un informe.

## Estilo de código del módulo

**Constantes con nombre y unidades.** Un número suelto en mitad del código no se entiende ni se puede revisar:

```java
// Mal: ¿0.81 qué?
double kwh = bytes / 1e9 * 0.81;

// Bien: la constante documenta el modelo y la unidad
public static final double KWH_POR_GB = 0.81;   // Sustainable Web Design v3, simplificado
double kwh = bytes / BYTES_POR_GB * KWH_POR_GB;
```

**Javadoc en lo que no es evidente.** Cuenta en la rúbrica, pero sobre todo sirve a quien lea tu código después:

```java
/**
 * Intensidad de carbono en g CO2/kWh.
 * 1 t/MWh equivale a 1000 g/kWh, de ahí el factor 1000.
 */
public double intensidad(List<Generacion> datos) { … }
```

**Redondea solo al final.** Los cálculos intermedios van sin redondear; se redondea el resultado que se muestra. Cuando un método del proyecto pide redondeo, el Javadoc lo dice.

## Cómo se prueba tu código

Cada proyecto trae una clase de test con **JUnit 5**. Los tests son la **especificación**: describen exactamente qué debe hacer tu código.

```java
@Test
void intensidadDeUnDia() {
    assertEquals(74.0, servicio.intensidad(DIA1), 1e-9);   // (1)!
}
```

1. El tercer argumento es la **tolerancia**: con `double` nunca se compara con `==`, sino admitiendo una diferencia mínima.

!!! tip "De uno en uno"
    Ejecuta los tests desde el IDE con el icono junto a cada método, o usa `./mvnw test -Dsurefire.skipAfterFailureCount=1`. Arreglar los fallos de uno en uno es mucho más llevadero.

!!! warning "No toques los tests"
    Si un test falla, arregla tu código, no el test. En el examen se usa una batería equivalente que no puedes modificar.

## Por qué el código eficiente también es sostenible

El software no emite CO₂ por sí mismo, pero **hace trabajar al hardware**, y el hardware consume electricidad y tiene una huella de fabricación. Cada byte enviado, cada consulta repetida y cada servidor sobredimensionado cuentan. Las decisiones que tomas en Spring Boot (paginar, cachear, comprimir, evitar N+1) son decisiones de sostenibilidad, y en la UD5 aprenderás a medir cuánto.
