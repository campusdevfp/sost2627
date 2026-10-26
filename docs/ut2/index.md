# UT2 · Retos ambientales y la huella de la electricidad

> **RA2** · 4 h · 15 % · Clase `Ut2Energia.java` · `mvn test -Dtest=Ut2EnergiaTest`

**La idea de toda la unidad en una frase:** el mismo servidor, consumiendo lo mismo, emite más o menos CO₂ según **cómo se haya fabricado la electricidad ese día**. Vas a aprender a calcularlo y a usarlo para decidir cuándo ejecutar un proceso.

El camino es siempre el mismo:

```mermaid
flowchart LR
    A["1 · Entiendes<br/>el concepto"] --> B["2 · Lo calculas<br/>a mano"] --> C["3 · Lo programas<br/>en un método Java"] --> D["4 · Lo usas<br/>para decidir"]
```

---

## 1. Teoría paso a paso

### 1.1 Retos ambientales y sociales

Un **reto** es un problema grande que afecta a todos y que hay que ir resolviendo.

| Retos **ambientales** (afectan al planeta) | Retos **sociales** (afectan a las personas) |
|---|---|
| Cambio climático | Desigualdad |
| Pérdida de biodiversidad | Brecha digital |
| Contaminación | Trabajo precario |
| Escasez de agua | Desigualdad de género |
| Agotamiento de recursos | |

**Van juntos.** Una ola de calor (ambiental) golpea más a quien trabaja al aire libre (social). Por eso se habla de **transición justa**: reducir emisiones **sin dejar atrás** a los trabajadores y territorios que viven de las industrias que contaminan.

### 1.2 Lo digital también contamina

Una web parece no pesar nada, pero detrás hay máquinas que gastan electricidad, agua para refrigerarlas y aparatos que acaban en la basura.

| Impacto | Dato de referencia |
|---|---|
| Electricidad | Los centros de datos rondaban el **1,5 %** de la electricidad mundial en 2024 (IEA, *Energy and AI*, 2025) |
| Residuos electrónicos | **62 Mt** generadas en 2022; solo el **22,3 %** se recogió formalmente (Global E-waste Monitor 2024) |
| Agua | Refrigeración de los centros de datos, un problema en zonas con sequía |

### 1.3 El mix eléctrico: con qué se fabrica la electricidad

Cada día, la electricidad del país se produce con **varias tecnologías a la vez**: un poco de eólica, un poco de nuclear, algo de gas… Esa mezcla se llama **mix eléctrico** y **cambia cada día**: si sopla viento hay mucha eólica; si no, hay que tirar de gas o de carbón.

Un día cualquiera, en **MWh** (megavatios hora, la unidad de energía que se usa para la red):

| Tecnología | MWh generados |
|---|---:|
| Eólica | 300 |
| Ciclo combinado (gas) | 200 |
| Nuclear | 500 |
| **Total** | **1000** |

!!! info "Las unidades que vas a usar"
    | | | |
    |---|---|---|
    | **1 MWh** = 1000 kWh | El kWh es lo que pone tu factura de la luz | Un servidor pequeño gasta unos 250 kWh al mes |
    | **1 t** = 1000 kg = 1 000 000 g | Las emisiones del país van en toneladas | Las de un proceso, en kilos |

### 1.4 El factor de emisión: cuánto ensucia cada tecnología

El **factor de emisión** dice cuántas **toneladas de CO₂** se emiten por cada MWh fabricado con esa tecnología.

| Tecnología | Factor (t CO₂/MWh) | ¿Emite CO₂? | ¿Es renovable? |
|---|---:|:-:|:-:|
| Carbón | 0,95 | Sí, mucho | No |
| Motores diésel | 0,77 | Sí | No |
| Cogeneración | 0,38 | Sí | No |
| Ciclo combinado | 0,37 | Sí | No |
| Eólica, solar, hidráulica | 0 | No | **Sí** |
| Nuclear | 0 | No | **No** |

**Fíjate en la nuclear:** no emite CO₂, pero **no es renovable** (el uranio se acaba). «Emitir» y «ser renovable» son **dos preguntas distintas**; por eso en Java habrá dos métodos distintos.

### 1.5 Las toneladas de un día

Se multiplica lo generado por su factor y se suma todo:

```text
toneladas = MWh₁ × factor₁ + MWh₂ × factor₂ + …
```

Con el día del apartado 1.3:

| Tecnología | MWh | × factor | = toneladas |
|---|---:|---:|---:|
| Eólica | 300 | 0 | 0 |
| Ciclo combinado | 200 | 0,37 | 74 |
| Nuclear | 500 | 0 | 0 |
| **Total** | **1000** | | **74 t** |

### 1.6 La intensidad de carbono: el número clave

Las 74 t solas no dicen mucho: un día con el doble de consumo emitiría más aunque fuera más limpio. Lo que interesa es **cuánto CO₂ hay detrás de cada kWh**. Eso es la **intensidad de carbono**, y se mide en **gramos de CO₂ por kWh (g/kWh)**.

```text
intensidad = toneladas ÷ MWh totales × 1000
```

¿Por qué `× 1000`? Porque toneladas ÷ MWh da **t/MWh**, y queremos **g/kWh**:
**1 t/MWh = 1 000 000 g ÷ 1000 kWh = 1000 g/kWh**.

Con nuestro día: `74 ÷ 1000 × 1000 =` **74 g/kWh**.

!!! warning "Los MWh que no emiten también cuentan"
    La eólica y la nuclear no suman toneladas, pero **sí suman en el total de MWh**. Son las que bajan la media.

### 1.7 El porcentaje renovable

Qué parte de la electricidad viene de fuentes renovables:

```text
% renovable = MWh renovables ÷ MWh totales × 100
```

Nuestro día: solo la eólica es renovable (la nuclear no) → `300 ÷ 1000 × 100 =` **30 %**.

### 1.8 El semáforo de la red

Para decidir rápido, la intensidad se traduce a un color:

| Intensidad | Color | Qué significa |
|---|---|---|
| Menos de 100 g/kWh | **VERDE** | Buen momento para tareas pesadas |
| De 100 a menos de 200 | **ÁMBAR** | Normal |
| 200 o más | **ROJO** | Si puedes, espera |

### 1.9 Los kilos de CO₂ de un consumo

Si sabes cuántos kWh gasta algo y la intensidad de ese día:

```text
kg de CO₂ = kWh × g/kWh ÷ 1000
```

Un servidor que gasta **250 kWh** con la red a **74 g/kWh**: `250 × 74 = 18 500 g =` **18,5 kg**.
El mismo servidor con la red a **412 g/kWh**: `250 × 412 = 103 000 g =` **103 kg**.

**Misma máquina, mismo código y más de cinco veces el CO₂.** Solo cambia el día.

### 1.10 Qué hacer para reducir el impacto

Siempre en este orden: primero lo que más reduce, al final lo que menos.

```mermaid
flowchart LR
    A[1 Evitar] --> B[2 Reducir] --> C[3 Sustituir] --> D[4 Compensar]
```

| Paso | Qué significa | Ejemplo en informática |
|---|---|---|
| **Evitar** | Que ese consumo no exista | Eliminar un informe nocturno que nadie lee |
| **Reducir** | Consumir menos para lo mismo | Optimizar una consulta lenta; apagar servidores ociosos |
| **Sustituir** | Usar una fuente más limpia | Contratar electricidad renovable; lanzar los procesos cuando la red está más limpia |
| **Compensar** | Pagar para que otro reduzca | Comprar créditos de carbono |

Anunciarse como «neutro en carbono» **solo** comprando créditos, sin reducir nada, es **greenwashing**.

### Resumen: de cada concepto sale un método

| Concepto | Fórmula | Método Java |
|---|---|---|
| Factor de emisión | se busca en la tabla | `factorEmision` |
| ¿Es renovable? | se busca en la lista | `esRenovable` |
| Toneladas del día | Σ MWh × factor | `toneladas` |
| Intensidad | toneladas ÷ MWh × 1000 | `intensidad` |
| % renovable | MWh renovables ÷ MWh × 100 | `porcentajeRenovable` |
| Semáforo | < 100 · < 200 · resto | `semaforoRed` |
| Kilos de un consumo | kWh × g/kWh ÷ 1000 | `kgCo2` |
| Mejor día | el de menor intensidad | `diaMasLimpio` |

---

## 2. Batería de ejercicios · de la idea al método Java

> Ocho ejercicios, **uno por método** de `Ut2Energia.java`. En cada uno: qué hace, un ejemplo a mano, cómo pensarlo y la solución explicada línea a línea. Intenta escribirlo tú antes de abrir la solución y compruébalo con su test.
>
> **Cuando acabes los ocho, la clase está terminada** y tienes hecha la Parte 1 del reto.

### Antes de empezar: los arrays paralelos

Abre `src/main/java/sostenibilidad/Ut2Energia.java`. Arriba ya tienes la tabla de factores, guardada en **dos arrays que van emparejados por posición**:

| Posición `i` | `TECNOLOGIAS[i]` | `FACTORES[i]` |
|:-:|---|---:|
| 0 | `"Carbón"` | 0.95 |
| 1 | `"Ciclo combinado"` | 0.37 |
| 2 | `"Cogeneración"` | 0.38 |
| 3 | `"Motores diésel"` | 0.77 |

El factor del carbón está en la **misma posición** que la palabra «Carbón». Lo mismo pasará con los datos de un día: `tecnologias[i]` y `mwh[i]` van juntos.

```java
String[] tecnologias = {"Eólica", "Ciclo combinado", "Nuclear"};
double[] mwh         = {   300,              200,       500   };
//                        [0]               [1]        [2]
```

!!! tip "Redondear en Java"
    Varios métodos piden redondear. Siempre es el mismo truco:

    | Para redondear a… | Se escribe | Ejemplo |
    |---|---|---|
    | 1 decimal | `Math.round(x * 10) / 10.0` | 74.04 → 74.0 |
    | 2 decimales | `Math.round(x * 100) / 100.0` | 0.40722 → 0.41 |

    El `.0` del final es imprescindible: sin él, Java haría una división entera y perderías los decimales.

---

### Ejercicio 1 · `factorEmision`: buscar en la tabla

**Qué hace.** Recibe el nombre de una tecnología y devuelve su factor. Si no está en la tabla (porque no emite), devuelve 0.

| Llamada | Devuelve | Por qué |
|---|---:|---|
| `factorEmision("Carbón")` | 0.95 | Está en la posición 0 |
| `factorEmision("Ciclo combinado")` | 0.37 | Está en la posición 1 |
| `factorEmision("Nuclear")` | 0.0 | No está: no emite |

**Cómo pensarlo.**

1. Recorre las posiciones de `TECNOLOGIAS`.
2. Si en la posición `i` está el nombre que buscas, devuelve `FACTORES[i]`.
3. Si terminas el bucle sin encontrarlo, devuelve `0.0`.

??? success "Solución"

    ```java
    public static double factorEmision(String tecnologia) {
        for (int i = 0; i < TECNOLOGIAS.length; i++) {
            if (TECNOLOGIAS[i].equals(tecnologia)) {   // (1)
                return FACTORES[i];                     // (2)
            }
        }
        return 0.0;                                     // (3)
    }
    ```

    1. Los textos se comparan con `equals`, **nunca** con `==`.
    2. Misma posición en el otro array: ahí está su factor.
    3. Esta línea solo se alcanza si el bucle no ha encontrado nada. Si la pusieras dentro del bucle, devolvería 0 en cuanto la primera posición no coincidiera.

**Compruébalo:** `mvn test -Dtest=Ut2EnergiaTest#factorEmision`

---

### Ejercicio 2 · `esRenovable`: ¿está en la lista?

**Qué hace.** Devuelve `true` si la tecnología está en `RENOVABLES`.

| Llamada | Devuelve |
|---|:-:|
| `esRenovable("Eólica")` | `true` |
| `esRenovable("Hidráulica")` | `true` |
| `esRenovable("Nuclear")` | `false` |
| `esRenovable("Carbón")` | `false` |

**Cómo pensarlo.** Igual que el ejercicio 1, pero solo hay que contestar sí o no.

??? success "Solución"

    ```java
    public static boolean esRenovable(String tecnologia) {
        for (String r : RENOVABLES) {        // (1)
            if (r.equals(tecnologia)) {
                return true;
            }
        }
        return false;
    }
    ```

    1. Aquí no hace falta la posición, así que basta un `for` mejorado: «para cada `r` de `RENOVABLES`».

    **¿Por qué no un solo método `esLimpia()`?** Porque con la nuclear no sabrías qué contestar: no emite (sería «limpia») pero no es renovable. Dos preguntas, dos métodos.

**Compruébalo:** `mvn test -Dtest=Ut2EnergiaTest#esRenovable`

---

### Ejercicio 3 · `toneladas`: el CO₂ de un día

**Qué hace.** Es el apartado 1.5 de la teoría en código: suma `mwh[i] × factor` de cada tecnología.

| Datos | Cuenta | Devuelve |
|---|---|---:|
| Eólica 300 · Ciclo combinado 200 · Nuclear 500 | 0 + 200 × 0,37 + 0 | 74.0 |
| Carbón 100 · Ciclo combinado 300 · Solar fotovoltaica 100 | 95 + 111 + 0 | 206.0 |

**Cómo pensarlo.**

1. Empieza con `suma = 0`.
2. Para cada posición `i`: busca el factor de `tecnologias[i]` con el método 1 y súmale `mwh[i] × factor`.
3. Devuelve la suma.

??? success "Solución"

    ```java
    public static double toneladas(String[] tecnologias, double[] mwh) {
        double suma = 0;
        for (int i = 0; i < tecnologias.length; i++) {
            suma += mwh[i] * factorEmision(tecnologias[i]);   // (1)
        }
        return suma;
    }
    ```

    1. Reutilizas el método 1: no repites la búsqueda en la tabla. Con la eólica, `factorEmision` devuelve 0 y se suma 0.

**Compruébalo:** `mvn test -Dtest=Ut2EnergiaTest#toneladas`

---

### Ejercicio 4 · `intensidad`: los gramos por kWh

**Qué hace.** Es el apartado 1.6: `toneladas ÷ MWh totales × 1000`, redondeado a 1 decimal. Si no hay MWh, devuelve 0.

| Datos | Cuenta | Devuelve |
|---|---|---:|
| Día 1 del ejercicio 3 | 74 ÷ 1000 × 1000 | 74.0 |
| Día 2 del ejercicio 3 | 206 ÷ 500 × 1000 | 412.0 |
| Sin datos | — | 0.0 |

**Cómo pensarlo.**

1. Suma todos los MWh.
2. Si el total es 0, devuelve 0.0 (no se puede dividir entre 0).
3. Calcula las toneladas con el método 3.
4. Aplica la fórmula y redondea a 1 decimal.

??? success "Solución"

    ```java
    public static double intensidad(String[] tecnologias, double[] mwh) {
        double totalMwh = 0;
        for (double m : mwh) {
            totalMwh += m;                                   // (1)
        }
        if (totalMwh == 0) {
            return 0.0;                                      // (2)
        }
        double t = toneladas(tecnologias, mwh);              // (3)
        return Math.round(t / totalMwh * 1000 * 10) / 10.0;  // (4)
    }
    ```

    1. **Todos** los MWh, también los que no emiten.
    2. Sin esta línea dividirías entre 0 y Java devolvería `NaN` («no es un número»), que estropea cualquier cuenta que se haga después.
    3. Otra vez reutilizas un método anterior.
    4. La fórmula (`t / totalMwh * 1000`) y el redondeo a 1 decimal (`* 10 … / 10.0`) en una línea.

**Fíjate en el día 2:** genera **la mitad** de electricidad que el día 1 y tiene **más de cinco veces** su intensidad. No importa cuánta electricidad hay, sino **con qué se ha fabricado**.

**Compruébalo:** `mvn test -Dtest=Ut2EnergiaTest#intensidad`

---

### Ejercicio 5 · `porcentajeRenovable`

**Qué hace.** Es el apartado 1.7: qué parte de los MWh es renovable, redondeado a 1 decimal.

| Datos | Renovables | Devuelve |
|---|---|---:|
| Eólica 300 · Ciclo combinado 200 · Nuclear 500 | solo la eólica: 300 de 1000 | 30.0 |
| Carbón 100 · Ciclo combinado 300 · Solar fotovoltaica 100 | solo la solar: 100 de 500 | 20.0 |

**Cómo pensarlo.** Un bucle con **dos sumas a la vez**: el total de MWh y los MWh renovables (usando el método 2).

??? success "Solución"

    ```java
    public static double porcentajeRenovable(String[] tecnologias, double[] mwh) {
        double total = 0;
        double renovable = 0;
        for (int i = 0; i < tecnologias.length; i++) {
            total += mwh[i];                          // (1)
            if (esRenovable(tecnologias[i])) {
                renovable += mwh[i];                  // (2)
            }
        }
        if (total == 0) {
            return 0.0;
        }
        return Math.round(renovable / total * 100 * 10) / 10.0;
    }
    ```

    1. Todos suman al total.
    2. Solo los renovables suman aquí. La nuclear no entra.

**Compruébalo:** `mvn test -Dtest=Ut2EnergiaTest#porcentajeRenovable`

---

### Ejercicio 6 · `semaforoRed`

**Qué hace.** Es el apartado 1.8: traduce la intensidad a un color. Si es negativa, lanza `IllegalArgumentException`.

| Intensidad | Devuelve |
|---:|---|
| 74 | `"VERDE"` |
| 100 | `"AMBAR"` |
| 199.9 | `"AMBAR"` |
| 200 | `"ROJO"` |
| −5 | excepción |

**Cómo pensarlo.** Primero el error; después los tramos **de menor a mayor**.

??? success "Solución"

    ```java
    public static String semaforoRed(double gramosPorKwh) {
        if (gramosPorKwh < 0) {
            throw new IllegalArgumentException("Intensidad negativa: " + gramosPorKwh);
        }
        if (gramosPorKwh < 100) return "VERDE";     // (1)
        if (gramosPorKwh < 200) return "AMBAR";     // (2)
        return "ROJO";
    }
    ```

    1. Si es menor que 100, ya has terminado.
    2. Si llegas aquí, ya sabes que es 100 o más; solo falta ver si es menor que 200.

    Con exactamente 200 devuelve `"ROJO"`: ámbar es «**menos** de 200».

**Compruébalo:** `mvn test -Dtest="Ut2EnergiaTest#semaforo*"`

---

### Ejercicio 7 · `kgCo2`: lo que emite un consumo

**Qué hace.** Es el apartado 1.9: `kWh × g/kWh ÷ 1000`, redondeado a 2 decimales.

| Llamada | Cuenta | Devuelve |
|---|---|---:|
| `kgCo2(250, 120)` | 30 000 g ÷ 1000 | 30.0 |
| `kgCo2(50, 74)` | 3700 g ÷ 1000 | 3.7 |
| `kgCo2(3.3, 123.4)` | 0,40722 → redondeo | 0.41 |

??? success "Solución"

    ```java
    public static double kgCo2(double kwh, double gramosPorKwh) {
        return Math.round(kwh * gramosPorKwh / 1000 * 100) / 100.0;   // (1)
    }
    ```

    1. `kwh * gramosPorKwh` da gramos; `/ 1000` los pasa a kilos; `* 100 … / 100.0` redondea a 2 decimales.

**Compruébalo:** `mvn test -Dtest=Ut2EnergiaTest#kgCo2`

---

### Ejercicio 8 · `diaMasLimpio`: la posición del mínimo

**Qué hace.** Recibe las intensidades de varios días y devuelve la **posición** del más bajo (no su valor). Si hay empate, el primero. Si el array está vacío, −1.

| Llamada | Devuelve | Por qué |
|---|---:|---|
| `diaMasLimpio({90, 60, 120})` | 1 | 60 está en la posición 1 |
| `diaMasLimpio({50, 50, 90})` | 0 | Empate: gana el primero |
| `diaMasLimpio({})` | −1 | No hay días |

**Cómo pensarlo.**

1. Si está vacío, devuelve −1.
2. Supón que el mejor es el día 0.
3. Recorre los demás: si uno es **estrictamente menor** que el mejor que llevas, ese pasa a ser el mejor.
4. Devuelve la posición.

??? success "Solución"

    ```java
    public static int diaMasLimpio(double[] intensidades) {
        if (intensidades.length == 0) {
            return -1;
        }
        int mejor = 0;                                    // (1)
        for (int i = 1; i < intensidades.length; i++) {
            if (intensidades[i] < intensidades[mejor]) {  // (2)
                mejor = i;
            }
        }
        return mejor;                                     // (3)
    }
    ```

    1. Guardas la **posición**, no el valor.
    2. Con `<` (y no `<=`), en un empate no cambias: se queda el primero.
    3. Con la posición puedes sacar después el nombre del día y su intensidad.

**Compruébalo:** `mvn test -Dtest=Ut2EnergiaTest#diaMasLimpio`

!!! success "Comprueba la clase entera"
    `mvn test -Dtest=Ut2EnergiaTest` → todos los tests en verde. **Ya tienes la Parte 1 del reto.**

---

## 3. Reto ejemplo (resuelto paso a paso)

> Aquí juntas los ocho métodos para tomar una decisión real. Primero lo resolvemos **a mano**, para que veas de dónde sale cada número, y después **con el programa**. El reto que entregas es **exactamente igual con otros datos**.

### El encargo

El departamento de sistemas lanza **una vez por semana** un proceso de **copias de seguridad** que consume **50 kWh**. Puede hacerse el lunes, el martes o el miércoles. Te pasan cómo se fabricó la electricidad esos tres días (en MWh):

| Tecnología | Lunes | Martes | Miércoles |
|---|---:|---:|---:|
| Eólica | 400 | 200 | 100 |
| Solar fotovoltaica | 100 | 100 | 200 |
| Nuclear | 300 | 300 | 300 |
| Ciclo combinado | 200 | 300 | 400 |
| Carbón | 0 | 100 | 0 |
| **Total** | **1000** | **1000** | **1000** |

**La pregunta:** ¿qué día conviene lanzarlo y cuánto CO₂ se ahorra al año?

### Paso 1 · Las toneladas de cada día

Solo emiten el ciclo combinado (0,37) y el carbón (0,95). El resto suma 0.

| Día | Ciclo combinado | Carbón | **Toneladas** |
|---|---|---|---:|
| Lunes | 200 × 0,37 = 74 | 0 | **74** |
| Martes | 300 × 0,37 = 111 | 100 × 0,95 = 95 | **206** |
| Miércoles | 400 × 0,37 = 148 | 0 | **148** |

### Paso 2 · Intensidad, % renovable y semáforo

Como los tres días tienen 1000 MWh, la intensidad es `toneladas ÷ 1000 × 1000`, es decir, **el mismo número que las toneladas**. Renovables son la eólica y la solar.

| Día | Intensidad | Renovable | Semáforo |
|---|---:|---:|---|
| Lunes | 74,0 g/kWh | (400 + 100) ÷ 1000 = **50 %** | VERDE |
| Martes | 206,0 g/kWh | (200 + 100) ÷ 1000 = **30 %** | ROJO |
| Miércoles | 148,0 g/kWh | (100 + 200) ÷ 1000 = **30 %** | ÁMBAR |

### Paso 3 · El mejor y el peor día

- **Mejor:** el lunes (74,0). Es lo que devuelve `diaMasLimpio`: la posición **0**.
- **Peor:** el martes (206,0).

### Paso 4 · Los kilos del proceso cada día

`kg = 50 kWh × intensidad ÷ 1000`

| Día | Cuenta | kg de CO₂ |
|---|---|---:|
| Lunes | 50 × 74 ÷ 1000 | **3,70** |
| Martes | 50 × 206 ÷ 1000 | **10,30** |

### Paso 5 · El ahorro

- Por semana: `10,30 − 3,70 =` **6,60 kg**
- Al año: `6,60 × 52 semanas =` **343,2 kg**

### El mismo cálculo, con el programa

Crea el fichero `src/main/java/sostenibilidad/DemoUt2.java`, pega esto y ejecútalo con el botón de *play* del IDE. Cada bloque es uno de los pasos anteriores:

```java
package sostenibilidad;

public class DemoUt2 {
    public static void main(String[] args) {
        // DATOS: lo único que cambiarás en tu reto
        String[] tecnologias = {"Eólica", "Solar fotovoltaica", "Nuclear", "Ciclo combinado", "Carbón"};
        String[] dias = {"lunes", "martes", "miércoles"};
        double[][] mwh = {
            {400, 100, 300, 200,   0},     // lunes
            {200, 100, 300, 300, 100},     // martes
            {100, 200, 300, 400,   0}};    // miércoles
        double kwhProceso = 50;

        // PASOS 1 y 2: intensidad, % renovable y semáforo de cada día
        double[] intensidades = new double[dias.length];
        for (int d = 0; d < dias.length; d++) {
            intensidades[d] = Ut2Energia.intensidad(tecnologias, mwh[d]);
            double renovable = Ut2Energia.porcentajeRenovable(tecnologias, mwh[d]);
            System.out.printf("%-10s %6.1f g/kWh  %5.1f %% renovable  %s%n",
                    dias[d], intensidades[d], renovable, Ut2Energia.semaforoRed(intensidades[d]));
        }

        // PASO 3: el día más limpio y el más sucio
        int mejor = Ut2Energia.diaMasLimpio(intensidades);
        int peor = 0;
        for (int i = 1; i < intensidades.length; i++) {
            if (intensidades[i] > intensidades[peor]) peor = i;
        }

        // PASO 4: kilos que emite el proceso esos dos días
        double kgMejor = Ut2Energia.kgCo2(kwhProceso, intensidades[mejor]);
        double kgPeor = Ut2Energia.kgCo2(kwhProceso, intensidades[peor]);
        System.out.printf("Mejor día: %s -> %.2f kg%n", dias[mejor], kgMejor);
        System.out.printf("Peor día:  %s -> %.2f kg%n", dias[peor], kgPeor);

        // PASO 5: el ahorro, por semana y en un año
        double ahorro = kgPeor - kgMejor;
        System.out.printf("Ahorro: %.2f kg por semana, %.1f kg al año%n", ahorro, ahorro * 52);
    }
}
```

**Lo que hay que entender del código:**

| Línea | Qué es |
|---|---|
| `double[][] mwh` | Una **tabla**: cada fila es un día y cada columna una tecnología, en el mismo orden que `tecnologias`. `mwh[1]` es la fila del martes |
| `Ut2Energia.intensidad(tecnologias, mwh[d])` | Le pasas las tecnologías y los MWh **de un día** y te devuelve su intensidad |
| `intensidades[d] = …` | Guardas la intensidad de cada día para usarla después |
| El `for` del peor día | El mismo bucle que `diaMasLimpio`, pero con `>`: busca el mayor |
| `printf("%.1f", …)` | Escribe el número con 1 decimal |

### La salida

```text
lunes        74,0 g/kWh   50,0 % renovable  VERDE
martes      206,0 g/kWh   30,0 % renovable  ROJO
miércoles   148,0 g/kWh   30,0 % renovable  AMBAR
Mejor día: lunes -> 3,70 kg
Peor día:  martes -> 10,30 kg
Ahorro: 6,60 kg por semana, 343,2 kg al año
```

Son **los mismos números que a mano**. Si alguna vez no coinciden, el fallo está en uno de los métodos.

### Qué significa este resultado

- **La recomendación:** lanzar las copias **el lunes**. Ahorra **343 kg de CO₂ al año** sin comprar nada: solo cambiando el día en el programador de tareas.
- **Martes y miércoles tienen el mismo 30 % renovable**, pero el martes emite bastante más. La culpa es de los **100 MWh de carbón**: ellos solos son 95 de las 206 t del día. El porcentaje renovable no lo dice todo; importa **con qué se cubre el resto**.
- **La nuclear es igual los tres días**, así que no explica ninguna diferencia.

Esto es una acción del tipo **sustituir** (apartado 1.10): se gasta lo mismo, pero con electricidad más limpia.

---

## 4. Reto a realizar (entregable)

> Si has hecho la batería, ya tienes media tarea. Son tres pasos.

### Paso 1 · La clase en verde

```bash
mvn test -Dtest=Ut2EnergiaTest
```

Todos los tests tienen que pasar. Si alguno falla, vuelve a su ejercicio de la batería.

### Paso 2 · Ejecuta el programa con estos datos

Copia `DemoUt2` del reto ejemplo y cambia **solo el bloque de DATOS**. Ahora el proceso consume **80 kWh**, se lanza una vez por semana y puede ir el jueves, el viernes o el sábado:

| Tecnología | Jueves | Viernes | Sábado |
|---|---:|---:|---:|
| Eólica | 100 | 300 | 500 |
| Hidráulica | 100 | 100 | 100 |
| Nuclear | 300 | 300 | 300 |
| Ciclo combinado | 400 | 300 | 100 |
| Carbón | 100 | 0 | 0 |

Hay que cambiar cuatro cosas: `tecnologias` (ahora hay hidráulica en lugar de solar), `dias`, las tres filas de `mwh` y `kwhProceso`.

!!! tip "Para saber que vas bien"
    El jueves debe salirte **243,0 g/kWh**. Hazlo también a mano, como en el reto ejemplo: 400 × 0,37 + 100 × 0,95.

### Paso 3 · Entrega media página con

1. La **salida del programa**.
2. **Dos frases** respondiendo a:
      - ¿Qué día recomiendas lanzar el proceso y cuánto CO₂ se ahorra al año frente al peor día?
      - Viernes y sábado tienen la misma nuclear y ninguno tiene carbón. ¿Qué explica que el sábado sea mucho más limpio?

---

## 5. Simulacro de examen

> **Esta es la evaluación de la unidad.** Tiene el mismo formato que la parte de RA2 del examen del trimestre, y **las preguntas de test del examen salen de este banco**.

```mermaid
flowchart LR
    S["Simulacro RA2"] --> P["Parte práctica<br/>3 métodos · 8 puntos"]
    S --> T["Banco de preguntas<br/>60 preguntas · el examen elige 4"]
    P --> N["Tu nota del RA2<br/>sobre 10"]
    T --> N
```

### Parte práctica · 8 puntos

Hazla **en 40 minutos**, sin mirar la batería. Abre `src/main/java/simulacro/SimulacroRa2.java`, completa los 3 métodos y lanza:

```bash
mvn test -Dtest=SimulacroRa2Test
```

```text
nota práctica = (tests superados ÷ 7) × 8
```

Son tres métodos que ya conoces (`factorEmision`, `intensidad` y `kgCo2`), con **tres cambios** respecto a clase:

| Qué cambia | En clase | En el simulacro |
|---|---|---|
| La tabla de factores | Carbón 0,95 · Ciclo combinado 0,37 · … | Carbón 0,90 · Ciclo combinado 0,40 · Turbina de gas 0,60 |
| Redondeo de `kgCo2` | 2 decimales | **1 decimal** |
| Datos negativos en `kgCo2` | No se comprueban | Lanzan `IllegalArgumentException` |

!!! warning "Lee el Javadoc antes de escribir"
    El fallo número uno del examen es escribir código correcto con las reglas de clase.

### Banco de preguntas · 2 puntos

En el examen salen **4 preguntas de este banco**, a 0,5 puntos cada una. El banco tiene **60 preguntas**: tápate las respuestas y hazlas todas. Si las dominas, tienes el test del examen preparado.

Son preguntas **sencillas**: si has entendido la teoría y has hecho la batería, salen solas. Los cálculos llevan números redondos y se hacen de cabeza.

#### A · Retos ambientales y sociales

**1.** ¿Cuál de estos es un reto **social**?

a) pérdida de biodiversidad · b) escasez de agua · c) brecha digital · d) contaminación

??? success "Respuesta"

    **c**. Los otros tres son retos ambientales.

**2.** ¿Cuál de estos es un reto **ambiental**?

a) agotamiento de recursos · b) trabajo precario · c) desigualdad de género · d) brecha digital

??? success "Respuesta"

    **a**. Los otros tres son retos sociales.

**3.** Una ola de calor perjudica más a quien trabaja al aire libre. Esto muestra que…

a) el cambio climático solo es un problema ambiental · b) los retos ambientales y sociales van juntos · c) hay que dejar de trabajar en verano · d) el problema es solo económico

??? success "Respuesta"

    **b**. De ahí la idea de transición justa.

**4.** La transición justa consiste en…

a) repartir las emisiones por igual entre países · b) cobrar una tasa por contaminar · c) cerrar todas las industrias contaminantes · d) descarbonizar sin dejar atrás a trabajadores ni territorios

??? success "Respuesta"

    **d**. Reducir emisiones teniendo en cuenta a quién afecta el cambio.

**5.** Los centros de datos consumían en 2024 cerca de qué parte de la electricidad mundial…

a) 0,01 % · b) 1,5 % · c) 15 % · d) 50 %

??? success "Respuesta"

    **b**. Dato de la IEA (2025).

**6.** Del residuo electrónico generado en 2022 se recogió formalmente…

a) casi todo · b) la mitad · c) algo más de una quinta parte · d) nada

??? success "Respuesta"

    **c**. El 22,3 % de 62 Mt.

**7.** ¿Por qué se dice que el sector digital **no es inmaterial**?

a) porque consume electricidad y agua y genera residuos electrónicos · b) porque usa mucho papel · c) porque no emite CO₂ · d) porque los datos pesan

??? success "Respuesta"

    **a**. Detrás de cada servicio web hay máquinas, refrigeración y aparatos que acaban como residuo.

**8.** La brecha digital es un reto…

a) ambiental · b) económico, no social · c) social · d) que no afecta a la informática

??? success "Respuesta"

    **c**. Afecta a las personas: quién tiene acceso a la tecnología y quién no.

**9.** La escasez de agua es un reto…

a) ambiental · b) social · c) de gobernanza · d) que no afecta a la informática

??? success "Respuesta"

    **a**. Y toca a lo digital: los centros de datos usan agua.

**10.** ¿Para qué usan agua los centros de datos?

a) para fabricar electricidad · b) para limpiar los discos · c) no usan agua · d) para refrigerar las máquinas

??? success "Respuesta"

    **d**. Es un problema en zonas con sequía.

**11.** ¿Cuántos residuos electrónicos se generaron en el mundo en 2022?

a) 62 t · b) 62 Mt · c) 6 Mt · d) 620 Mt

??? success "Respuesta"

    **b**. Millones de toneladas; solo el 22,3 % se recogió formalmente.

**12.** La transición justa se preocupa sobre todo por…

a) el precio de la luz · b) los centros de datos · c) los trabajadores y territorios que viven de las industrias contaminantes · d) las agencias de rating

??? success "Respuesta"

    **c**. Descarbonizar sin dejarlos atrás.

#### B · La intensidad de carbono

**13.** La intensidad de carbono de la red eléctrica indica…

a) cuánta electricidad se genera en un día · b) el precio del kWh · c) cuánto CO₂ hay detrás de cada kWh consumido · d) el porcentaje renovable

??? success "Respuesta"

    **c**. Es una media: CO₂ emitido entre electricidad generada.

**14.** La intensidad de carbono se mide en…

a) kWh · b) €/MWh · c) t/año · d) g CO₂/kWh

??? success "Respuesta"

    **d**. Gramos de CO₂ por cada kWh.

**15.** ¿Cuál es la fórmula de la intensidad?

a) toneladas totales ÷ MWh totales × 1000 · b) MWh totales ÷ toneladas totales × 1000 · c) toneladas totales × MWh totales · d) MWh renovables ÷ MWh totales

??? success "Respuesta"

    **a**. La d) es el porcentaje renovable (sin el × 100).

**16.** En esa fórmula, ¿por qué se multiplica por 1000?

a) para redondear · b) porque 1 t/MWh equivale a 1000 g/kWh · c) para pasarlo a porcentaje · d) para quitar los decimales

??? success "Respuesta"

    **b**. 1 000 000 g ÷ 1000 kWh = 1000 g/kWh.

**17.** ¿Qué tecnología tiene el factor de emisión **más alto** de la tabla?

a) ciclo combinado · b) carbón · c) cogeneración · d) nuclear

??? success "Respuesta"

    **b**. 0,95 t/MWh, casi el triple que el ciclo combinado.

**18.** Sobre la energía nuclear, ¿qué es correcto?

a) emite mucho CO₂ y es renovable · b) no emite CO₂ al generar y es renovable · c) emite CO₂ y no es renovable · d) no emite CO₂ al generar y no es renovable

??? success "Respuesta"

    **d**. Son dos preguntas distintas: no emite, pero el uranio es un recurso finito.

**19.** Un día se generan **menos** MWh que otro, pero con más carbón. Su intensidad…

a) puede ser mayor: importa con qué se genera, no cuánto · b) es seguro menor, porque hay menos electricidad · c) es igual · d) no se puede calcular

??? success "Respuesta"

    **a**. Es el día 2 de la batería: la mitad de MWh y cinco veces más intensidad.

**20.** El mix eléctrico es…

a) la mezcla de tecnologías con que se fabrica la electricidad de un día · b) la factura de la luz · c) un tipo de central · d) el porcentaje renovable

??? success "Respuesta"

    **a**. Un poco de eólica, un poco de nuclear, algo de gas…

**21.** ¿Por qué el mix eléctrico cambia de un día a otro?

a) porque lo decide cada empresa · b) no cambia nunca · c) porque cambia el precio · d) porque depende del viento, del sol, del agua disponible…

??? success "Respuesta"

    **d**. Si no sopla viento, hay que tirar de gas o de carbón.

**22.** 1 MWh son…

a) 100 kWh · b) 1000 kWh · c) 10 kWh · d) 1 000 000 kWh

??? success "Respuesta"

    **b**. Mega = mil veces kilo.

**23.** 1 tonelada son…

a) 1000 g · b) 100 000 g · c) 1 000 000 g · d) 10 000 g

??? success "Respuesta"

    **c**. 1 t = 1000 kg y 1 kg = 1000 g.

**24.** El factor de emisión de una tecnología indica…

a) las toneladas de CO₂ por cada MWh que fabrica · b) cuánta electricidad fabrica · c) si es renovable · d) su precio

??? success "Respuesta"

    **a**. Cuánto ensucia cada MWh.

**25.** ¿Cuál es el factor de emisión de la eólica?

a) 0,37 · b) 0,95 · c) 1 · d) 0

??? success "Respuesta"

    **d**. No emite CO₂ al generar.

**26.** ¿Cuál de estas tecnologías es **renovable**?

a) nuclear · b) hidráulica · c) carbón · d) ciclo combinado

??? success "Respuesta"

    **b**. La nuclear no emite, pero no es renovable.

**27.** ¿Qué tecnología **no emite** CO₂ al generar y **no es renovable**?

a) eólica · b) carbón · c) nuclear · d) solar

??? success "Respuesta"

    **c**. El uranio es un recurso que se acaba.

**28.** Al calcular la intensidad, los MWh de la eólica…

a) no suman toneladas, pero sí cuentan en el total de MWh · b) no se tienen en cuenta · c) suman toneladas · d) se restan

??? success "Respuesta"

    **a**. Son los que bajan la media.

#### C · Cálculos sencillos

**29.** 0,37 t/MWh equivalen a…

a) 3,7 g/kWh · b) 37 g/kWh · c) 370 g/kWh · d) 3700 g/kWh

??? success "Respuesta"

    **c**. Se multiplica por 1000.

**30.** Un día se generan 1000 MWh: 100 de carbón (0,95 t/MWh) y el resto eólica y nuclear. La intensidad es…

a) 95 g/kWh · b) 950 g/kWh · c) 9,5 g/kWh · d) 0 g/kWh

??? success "Respuesta"

    **a**. 100 × 0,95 = 95 t; 95 / 1000 × 1000 = 95. La eólica y la nuclear no emiten, pero sí cuentan en el total de MWh.

**31.** Con el semáforo de la red (VERDE < 100 · ÁMBAR < 200 · ROJO desde 200), una red a 150 g/kWh está en…

a) VERDE · b) ROJO · c) ÁMBAR · d) depende del día

??? success "Respuesta"

    **c**. Está entre 100 y 200.

**32.** De 1000 MWh, 400 son eólicos, 300 nucleares y 300 de ciclo combinado. El porcentaje renovable es…

a) 70 % · b) 40 % · c) 30 % · d) 100 %

??? success "Respuesta"

    **b**. Solo cuenta la eólica: la nuclear no es renovable.

**33.** Un proceso consume 100 kWh con la red a 200 g/kWh. Emite…

a) 2 kg · b) 200 kg · c) 20 000 kg · d) 20 kg

??? success "Respuesta"

    **d**. 100 × 200 = 20 000 g = 20 kg.

**34.** Un proceso emite 5 kg si se lanza el lunes y 15 kg si se lanza el martes. Lanzándolo el lunes se ahorran…

a) 10 kg · b) 5 kg · c) 15 kg · d) 20 kg

??? success "Respuesta"

    **a**. 15 − 5.

**35.** Cambiar un proceso de día ahorra 2 kg cada semana. En un año (52 semanas) son…

a) 26 kg · b) 52 kg · c) 104 kg · d) 1040 kg

??? success "Respuesta"

    **c**. 2 × 52. La escala convierte lo pequeño en relevante.

**36.** 200 MWh de ciclo combinado (0,37 t/MWh) emiten…

a) 37 t · b) 200 t · c) 740 t · d) 74 t

??? success "Respuesta"

    **d**. 200 × 0,37.

**37.** 100 MWh de carbón (0,95 t/MWh) emiten…

a) 9,5 t · b) 95 t · c) 950 t · d) 0,95 t

??? success "Respuesta"

    **b**. 100 × 0,95.

**38.** Un día emite 74 t y genera 1000 MWh. Su intensidad es…

a) 7,4 g/kWh · b) 740 g/kWh · c) 74 g/kWh · d) 0,074 g/kWh

??? success "Respuesta"

    **c**. 74 ÷ 1000 × 1000.

**39.** Un día emite 206 t y genera 500 MWh. Su intensidad es…

a) 412 g/kWh · b) 206 g/kWh · c) 103 g/kWh · d) 41,2 g/kWh

??? success "Respuesta"

    **a**. 206 ÷ 500 × 1000.

**40.** Con el semáforo VERDE < 100 · ÁMBAR < 200 · ROJO desde 200, una red a 74 g/kWh está en…

a) ÁMBAR · b) ROJO · c) depende del día · d) VERDE

??? success "Respuesta"

    **d**. Está por debajo de 100.

**41.** Con el mismo semáforo, una red a exactamente 200 g/kWh está en…

a) ÁMBAR · b) ROJO · c) VERDE · d) depende del día

??? success "Respuesta"

    **b**. Ámbar es «menos de 200».

**42.** De 1000 MWh, 300 son eólicos y 200 solares. El porcentaje renovable es…

a) 30 % · b) 20 % · c) 50 % · d) 500 %

??? success "Respuesta"

    **c**. (300 + 200) ÷ 1000 × 100.

**43.** Un proceso de 50 kWh con la red a 74 g/kWh emite…

a) 3,7 kg · b) 37 kg · c) 0,37 kg · d) 3700 kg

??? success "Respuesta"

    **a**. 50 × 74 = 3700 g = 3,7 kg.

**44.** Un proceso de 10 kWh con la red a 300 g/kWh emite…

a) 30 kg · b) 0,3 kg · c) 3000 kg · d) 3 kg

??? success "Respuesta"

    **d**. 10 × 300 = 3000 g = 3 kg.

**45.** En el reto ejemplo se ahorraban 6,6 kg por semana. En un año (52 semanas) son unos…

a) 34 kg · b) 343 kg · c) 66 kg · d) 3430 kg

??? success "Respuesta"

    **b**. 6,6 × 52 = 343,2.

#### D · Actuar con criterio

**46.** ¿Cuál es el orden correcto para reducir impactos?

a) compensar, sustituir, reducir, evitar · b) reducir, evitar, compensar, sustituir · c) sustituir, compensar, evitar, reducir · d) evitar, reducir, sustituir, compensar

??? success "Respuesta"

    **d**. Compensar siempre es lo último.

**47.** Eliminar un informe nocturno que nadie lee es un ejemplo de…

a) evitar · b) reducir · c) sustituir · d) compensar

??? success "Respuesta"

    **a**. Ese consumo deja de existir.

**48.** Contratar electricidad 100 % renovable para los servidores es un ejemplo de…

a) evitar · b) compensar · c) sustituir · d) reducir

??? success "Respuesta"

    **c**. Se sigue consumiendo lo mismo, pero de una fuente limpia.

**49.** Una empresa compra créditos de carbono sin reducir nada y se anuncia «neutra en carbono». Es…

a) lo correcto · b) greenwashing · c) transición justa · d) obligatorio por ley

??? success "Respuesta"

    **b**. Compensar sin reducir antes.

**50.** Lanzar un proceso por lotes el día con la red más limpia…

a) reduce sus emisiones aunque consuma los mismos kWh · b) reduce los kWh que consume · c) no cambia nada · d) aumenta sus emisiones

??? success "Respuesta"

    **a**. Es la idea del reto: misma máquina, mismo código, distinto momento.

**51.** `factorEmision("Nuclear")` devuelve 0.0 porque…

a) el código tiene un error · b) la nuclear es renovable · c) la nuclear no está en la tabla de tecnologías que emiten · d) lanza una excepción

??? success "Respuesta"

    **c**. Solo están en la tabla las que emiten CO₂. Si el bucle no la encuentra, devuelve 0.

**52.** ¿Por qué `factorEmision` y `esRenovable` son dos métodos separados?

a) por rendimiento · b) porque emitir y ser renovable son propiedades distintas · c) porque Java lo exige · d) para tener más tests

??? success "Respuesta"

    **b**. La nuclear no emite y tampoco es renovable: un único método no podría responder bien.

**53.** Si el total de MWh es 0, `intensidad` devuelve 0.0 en lugar de dividir. ¿Por qué?

a) para redondear · b) porque 0 es VERDE · c) da igual lo que devuelva · d) porque dividir entre 0 da NaN y estropea las cuentas siguientes

??? success "Respuesta"

    **d**. NaN es contagioso: todo lo que se calcule con él también da NaN.

**54.** En el reto ejemplo, martes y miércoles tienen el mismo porcentaje renovable (30 %), pero el martes emite más. ¿Por qué?

a) porque el martes se generan más MWh · b) por la nuclear · c) es un error de cálculo · d) porque el martes hay carbón

??? success "Respuesta"

    **d**. Los dos generan 1000 MWh y la misma nuclear; lo que cambia es con qué se cubre el resto.

**55.** `diaMasLimpio(new double[]{90, 60, 120})` devuelve…

a) 60 · b) 0 · c) 1 · d) 2

??? success "Respuesta"

    **c**. La **posición** del menor, no su valor.

**56.** `diaMasLimpio` con un array vacío devuelve…

a) −1 · b) 0 · c) una excepción · d) null

??? success "Respuesta"

    **a**. No hay ningún día que elegir.

**57.** Optimizar una consulta lenta para que gaste menos es un ejemplo de…

a) evitar · b) sustituir · c) compensar · d) reducir

??? success "Respuesta"

    **d**. Se sigue haciendo, pero consumiendo menos.

**58.** Comprar créditos de carbono es un ejemplo de…

a) reducir · b) compensar · c) evitar · d) sustituir

??? success "Respuesta"

    **b**. Es el último paso de la jerarquía.

**59.** En Java, para redondear `x` a 1 decimal se escribe…

a) Math.round(x) / 10 · b) Math.round(x / 10) · c) Math.round(x * 10) / 10.0 · d) (int) x * 10

??? success "Respuesta"

    **c**. El `.0` evita la división entera.

**60.** En Java, para comparar dos textos se usa…

a) equals · b) == · c) = · d) compare

??? success "Respuesta"

    **a**. `==` compara si son el mismo objeto, no si dicen lo mismo.

### Tu nota del simulacro

```text
nota RA2 = (tests superados ÷ 7) × 8  +  aciertos en 4 preguntas × 0,5
```

Si sale por debajo de 5, vuelve a la batería de la unidad antes del examen del trimestre.
