# UT6 · El plan de sostenibilidad de una empresa

> **RA6** · 4 h · 20 % · Clase `Ut6Plan.java` · `mvn test -Dtest=Ut6PlanTest`

La unidad que lo junta todo. Vas a construir el plan de una empresa y a descubrir, con tu propio código, que el asunto **más importante de su plan no tiene ninguna acción asignada**.

---

## 1. Teoría en cinco minutos

```mermaid
flowchart LR
    D[1 Diagnóstico] --> G[2 Grupos de interés]
    G --> M[3 Materialidad]
    M --> A[4 Acciones]
    A --> I[5 Indicadores]
    I --> S[6 Seguimiento]
    S --> C[7 Comunicación]
    C --> D
```

**Grupos de interés:** quien afecta a la empresa o se ve afectado por ella. No se gestionan igual. La **matriz de poder e interés**:

| | Interés bajo | Interés alto |
|---|---|---|
| **Influencia alta** | Mantener satisfecho *(inversores)* | Gestionar de cerca *(clientes)* |
| **Influencia baja** | Monitorizar *(proveedores)* | Mantener informado *(vecindario)* |

**Materialidad.** No todo importa a todas las empresas. Las normas europeas usan la **doble materialidad**, y basta con **una** de las dos perspectivas:

```mermaid
flowchart LR
    E[La empresa] -->|"materialidad de IMPACTO<br/>de dentro hacia fuera"| P[Personas y planeta]
    P -->|"materialidad FINANCIERA<br/>de fuera hacia dentro"| E
```

El agua de refrigeración de un centro de datos puede tener impacto alto y efecto financiero bajo, **y aun así ser material**. Si programas eso con `&&` en vez de `||`, dejas fuera justo los asuntos que las empresas tienden a callar.

**Acciones e indicadores.** Cada aspecto material necesita una acción con objetivo **SMART** y un indicador con línea base, meta y valor actual:

```text
progreso = (actual − base) / (meta − base)      acotado entre 0 y 1
```

La misma fórmula sirve si la meta sube **o baja**: los dos signos negativos se cancelan.

| Indicador | Base | Meta | Actual | Progreso |
|---|:-:|:-:|:-:|:-:|
| Renovables (%) | 40 | 100 | 72 | 0,53 |
| PUE del centro de datos | 1,8 | 1,4 | 1,6 | 0,50 |

**Comunicar.** Un informe honesto es equilibrado (cuenta lo que va mal), comparable, preciso con sus fuentes y, si puede, verificado por un tercero. Publicar los **aspectos materiales sin acción** es lo que lo distingue de un folleto.

---

## 2. Batería de ejercicios

> Seis ejercicios en orden, con **enunciado, datos y resultado esperado**.

---

### Ejercicio 1 · ¿Cómo trato a cada grupo de interés?

La **matriz de poder e interés** cruza cuánta **influencia** tiene un grupo sobre la empresa con cuánto **interés** tiene en lo que hace. Alto significa **3 o más** en una escala de 1 a 5.

| | Interés bajo | Interés alto |
|---|---|---|
| **Influencia alta** | MANTENER SATISFECHO | GESTIONAR DE CERCA |
| **Influencia baja** | MONITORIZAR | MANTENER INFORMADO |

**Qué tienes que hacer.** Clasifica estos cuatro grupos de una empresa de alojamiento web.

| Grupo | Influencia | Interés | Estrategia |
|---|:-:|:-:|---|
| Clientes | 5 | 4 | ? |
| Inversores | 5 | 2 | ? |
| Vecindario del centro de datos | 2 | 4 | ? |
| Proveedores de material de oficina | 2 | 2 | ? |

??? success "Solución"

    | Grupo | Estrategia | Por qué |
    |---|---|---|
    | Clientes | **GESTIONAR DE CERCA** | Deciden mucho y les importa mucho |
    | Inversores | **MANTENER SATISFECHO** | Pueden cambiarlo todo, pero el tema no les ocupa el día a día |
    | Vecindario | **MANTENER INFORMADO** | Les afecta mucho y pueden hacer poco |
    | Proveedores de oficina | **MONITORIZAR** | Ni influyen ni les afecta demasiado |

    **La matriz no es burocracia: es el plan de comunicación.** A los clientes se les convoca a una reunión; al vecindario se le manda el informe y se le abre un canal; a los proveedores de folios, nada especial.


---

### Ejercicio 2 · Doble materialidad: el `||` que lo cambia todo

Un asunto es **material** si merece que la empresa lo gestione e informe de él. Las normas europeas miran **dos** perspectivas y basta con **una**:

- **De impacto:** cuánto afecta la empresa a las personas y al planeta.
- **Financiera:** cuánto afecta ese asunto al negocio.

**Qué tienes que hacer.** Con un umbral de **3,5**, di si cada aspecto es material y de qué tipo.

| Aspecto | Impacto | Financiero | ¿Material? | Tipo |
|---|:-:|:-:|---|---|
| a) Consumo energético del centro de datos | 5 | 5 | ? | ? |
| b) Uso de agua en refrigeración | 4 | 2 | ? | ? |
| c) Riesgo regulatorio de protección de datos | 2 | 5 | ? | ? |
| d) Patrocinio deportivo local | 1 | 1 | ? | ? |

??? success "Solución"

    | Aspecto | ¿Material? | Tipo |
    |---|:-:|---|
    | a | Sí | **AMBAS** |
    | b | **Sí** | **IMPACTO** |
    | c | Sí | **FINANCIERA** |
    | d | No | NO MATERIAL |

    ```java
    boolean esMaterial(double impacto, double financiero, double umbral) {
        return impacto >= umbral || financiero >= umbral;    // (1)
    }
    ```

    1. **Con `&&` en vez de `||`**, el caso b desaparecería del informe.

    Y el caso b es justo el que importa: la empresa **daña** un recurso de su comarca sin sufrir ninguna consecuencia económica por ello. Esos son los asuntos que las empresas tienden a callar, y un operador lógico mal elegido los borra del mapa. Es, literalmente, la diferencia entre rendir cuentas y no hacerlo.


---

### Ejercicio 3 · Priorizar: no todo lo material es igual de urgente

Entre los aspectos materiales hay que decidir por dónde empezar. La prioridad combina lo que dice la empresa con lo que dicen sus grupos de interés:

```text
prioridad = (la mayor de las dos materialidades + importancia para los grupos) ÷ 2
```

**Qué tienes que hacer.** Calcula la prioridad de estos dos y di cuál va primero.

| Aspecto | Impacto | Financiero | Importancia para los grupos |
|---|:-:|:-:|:-:|
| Consumo energético | 5 | 5 | 4,5 |
| Privacidad de datos | 4 | 5 | 5 |

??? success "Solución"

    ```text
    Consumo energético:  (max(5, 5) + 4,5) / 2 = 9,5 / 2 = 4,75 → 4,8
    Privacidad de datos: (max(4, 5) + 5)   / 2 = 10  / 2 = 5,00 → 5,0
    ```

    **Gana la privacidad de datos**, aunque su impacto sea menor (4 frente a 5).

    ¿Por qué? Porque **los grupos de interés la puntúan más alto**. La prioridad no es solo lo que la empresa cree que importa: es también lo que le están exigiendo clientes, reguladores y plantilla. Una empresa que solo se mira a sí misma prioriza mal.


---

### Ejercicio 4 · El progreso, suba o baje la meta

Cada acción se mide con un indicador que tiene **línea base**, **meta** y **valor actual**:

```text
progreso = (actual − base) ÷ (meta − base)      acotado entre 0 y 1
```

**Qué tienes que hacer.** Calcula el progreso de estas cuatro acciones, con dos decimales.

| # | Indicador | Base | Meta | Actual |
|:-:|---|---:|---:|---:|
| a | Electricidad renovable (%) | 40 | 100 | 72 |
| b | PUE del centro de datos | 1,8 | 1,4 | 1,6 |
| c | Equipos con segunda vida (%) | 10 | 60 | 75 |
| d | Equipos con segunda vida (%) | 10 | 60 | 5 |

??? success "Solución"

    | # | Cuenta | Progreso |
    |:-:|---|---:|
    | a | `(72 − 40) / (100 − 40) = 32/60 = 0,533` | **0,53** |
    | b | `(1,6 − 1,8) / (1,4 − 1,8) = (−0,2)/(−0,4)` | **0,50** |
    | c | `(75 − 10) / 50 = 1,3` → acotado | **1,00** |
    | d | `(5 − 10) / 50 = −0,1` → acotado | **0,00** |

    **El caso b es la clave de todo el ejercicio.** La meta **baja** (un PUE menor es mejor), y sin embargo **la fórmula es exactamente la misma**: los dos signos negativos se cancelan.

    Mucha gente escribe un `if` para distinguir los dos casos y se equivoca en una de las dos ramas. No hace falta:

    ```java
    double progreso(double lineaBase, double meta, double valorActual) {
        double recorrido = meta - lineaBase;
        if (recorrido == 0) throw new IllegalArgumentException("Meta igual a la línea base");
        double p = (valorActual - lineaBase) / recorrido;
        return Math.round(Math.max(0, Math.min(1, p)) * 100) / 100.0;
    }
    ```


---

### Ejercicio 5 · Avanzada no significa en plazo

**Qué tienes que hacer.** Una acción de renovables va por un progreso de **0,53**. El plazo es de **36 meses** y han pasado **18**.

1. ¿Qué **estado** tiene? (≥ 1,0 COMPLETADA · ≥ 0,5 AVANZADA · > 0 INICIADA · 0 SIN EMPEZAR)
2. ¿Va **en plazo**?
3. Otra acción está en 0,30 con esos mismos 18 de 36 meses. ¿Estado y plazo?

??? success "Solución"

    Tiempo consumido: `18 / 36 = 0,50`.

    | Acción | Progreso | Estado | ¿En plazo? |
    |---|---:|---|---|
    | Renovables | 0,53 | **AVANZADA** | **Sí**, por 0,03 |
    | La otra | 0,30 | **INICIADA** | **No**: 0,30 < 0,50 |

    **Estado y plazo son dos preguntas distintas.** Una acción puede estar «AVANZADA» y llegar tarde igualmente si el reloj corre más deprisa que ella. En un informe hay que dar las dos cosas, porque «avanzada» suena bien y puede estar tapando un retraso.


---

### Ejercicio 6 · Los huecos del plan

**Qué tienes que hacer.** Estos son los aspectos materiales de una empresa y estas las acciones que tiene en marcha:

```text
Aspectos materiales:  "Consumo energético", "Privacidad de datos", "Uso de agua"
Acciones del plan:    "CONSUMO ENERGÉTICO"
```

1. ¿Qué aspectos se quedan sin acción?
2. ¿Qué pasaría si compararas los nombres con `equals` en lugar de `equalsIgnoreCase`?

??? success "Solución"

    1. **Privacidad de datos** y **Uso de agua**.

    2. Con `equals`, `"CONSUMO ENERGÉTICO"` no casaría con `"Consumo energético"` y el informe diría que **los tres** aspectos están sin acción. Un falso positivo que arruina la credibilidad del análisis entero: estarías acusando a la empresa de no hacer algo que sí hace.

    ```java
    for (String c : aspectosConAccion) {
        if (c.equalsIgnoreCase(a)) tiene = true;
    }
    ```

    Los nombres llegan de sitios distintos (una hoja de cálculo, un informe en PDF, un formulario) y cada uno con sus mayúsculas. **Comparar texto que viene de varias fuentes siempre exige normalizarlo.** Es la misma lección de la UT2 con las tecnologías eléctricas.


---

## 3. Reto ejemplo (resuelto)

> Aquí se juntan los seis ejercicios en un programa que analiza el plan completo de una empresa. El reto que entregas es igual, con otra empresa.

### El encargo

La dirección de **NubeVerde Hosting S.L.** te pide revisar su plan de sostenibilidad: **qué asuntos son materiales, en qué orden atenderlos, cómo van las acciones y qué falta**. El umbral de materialidad es **3,5**.

| Aspecto | Impacto | Financiero | Importancia para los grupos |
|---|:-:|:-:|:-:|
| Consumo energético del centro de datos | 5 | 5 | 4,5 |
| Privacidad y seguridad de datos | 4 | 5 | 5 |
| Uso de agua en refrigeración | 4 | 2 | 3,5 |
| Residuos electrónicos | 4 | 3 | 3 |
| Patrocinio deportivo local | 1 | 1 | 2 |

| Acción en marcha | Responde al aspecto | Base | Meta | Actual | Meses |
|---|---|---:|---:|---:|---|
| Contratar electricidad renovable (%) | Consumo energético | 40 | 100 | 72 | 18 de 36 |
| Reacondicionar equipos retirados (%) | Residuos electrónicos | 10 | 60 | 38 | — |

### Cómo se resuelve, paso a paso

```mermaid
flowchart LR
    A["1 · ¿Qué aspectos<br/>son materiales?"] --> B["2 · ¿De qué tipo<br/>y con qué prioridad?"]
    B --> C["3 · ¿Cómo van<br/>las acciones?"]
    C --> D["4 · ¿Qué aspectos<br/>no tienen acción?"]
```

1. `esMaterial` descarta los que no alcanzan el umbral por ninguna de las dos perspectivas.
2. `tipoMaterialidad` y `prioridad` dicen por qué importa cada uno y cuánto.
3. `progreso`, `estado` y `vaEnPlazo` dan el estado de cada acción.
4. `aspectosSinAccion` compara la lista de materiales con los aspectos que ya tienen alguna acción.

### El programa

Crea el fichero `src/main/java/sostenibilidad/DemoUt6.java` y ejecútalo con el botón de *play* del IDE:

```java
package sostenibilidad;

public class DemoUt6 {
    public static void main(String[] args) {
        String[] aspectos = {"Consumo energético", "Privacidad de datos",
                             "Uso de agua", "Residuos electrónicos", "Patrocinio local"};
        double[] imp = {5, 4, 4, 4, 1};
        double[] fin = {5, 5, 2, 3, 1};
        double[] gru = {4.5, 5, 3.5, 3, 2};
        double umbral = 3.5;

        // Pasos 1 y 2: materiales, tipo y prioridad
        System.out.println("ASPECTO                 TIPO           PRIORIDAD");
        for (int i = 0; i < aspectos.length; i++) {
            if (!Ut6Plan.esMaterial(imp[i], fin[i], umbral)) continue;   // descarta los no materiales
            System.out.printf("%-22s  %-13s  %.1f%n", aspectos[i],
                    Ut6Plan.tipoMaterialidad(imp[i], fin[i], umbral),
                    Ut6Plan.prioridad(imp[i], fin[i], gru[i]));
        }

        // Paso 3: estado de las acciones
        double pReno = Ut6Plan.progreso(40, 100, 72);
        double pReac = Ut6Plan.progreso(10, 60, 38);
        System.out.printf("%nRenovables: %.0f %% (%s), en plazo: %b%n",
                pReno * 100, Ut6Plan.estado(pReno), Ut6Plan.vaEnPlazo(pReno, 18, 36));
        System.out.printf("Reacondicionamiento: %.0f %% (%s)%n",
                pReac * 100, Ut6Plan.estado(pReac));

        // Paso 4: aspectos materiales que nadie está atendiendo
        String[] materiales = {"Consumo energético", "Privacidad de datos",
                               "Uso de agua", "Residuos electrónicos"};
        String[] conAccion = {"CONSUMO ENERGÉTICO", "Residuos electrónicos"};
        System.out.println("\nSIN ACCIÓN:");
        for (String s : Ut6Plan.aspectosSinAccion(materiales, conAccion)) {
            System.out.println("  - " + s);
        }
    }
}
```

### La salida

```text
ASPECTO                 TIPO           PRIORIDAD
Consumo energético      AMBAS          4,8
Privacidad de datos     AMBAS          5,0
Uso de agua             IMPACTO        3,8
Residuos electrónicos   IMPACTO        3,5

Renovables: 53 % (AVANZADA), en plazo: true
Reacondicionamiento: 56 % (AVANZADA)

SIN ACCIÓN:
  - Privacidad de datos
  - Uso de agua
```

Ordenados por prioridad:

```text
Privacidad de datos     ████████████████████████████████████████  5,0  ← SIN ACCIÓN
Consumo energético      ██████████████████████████████████████░░  4,8
Uso de agua             ██████████████████████████████░░░░░░░░░░  3,8  ← SIN ACCIÓN
Residuos electrónicos   ████████████████████████████░░░░░░░░░░░░  3,5
Patrocinio local        ████████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░  1,5  (no material)
```

### Qué significa este resultado

**El aspecto más prioritario de todo el plan no tiene ninguna acción.** La privacidad de datos saca un 5,0, y las dos acciones en marcha atacan el segundo y el cuarto de la lista. Un informe que no diga esto en la primera página no vale nada.

**El agua es material solo por su impacto.** Le afecta poco al negocio (financiero 2) pero mucho al entorno (impacto 4). Si `esMaterial` usara `&&` en vez de `||`, habría desaparecido del análisis, y con ella la responsabilidad de la empresa sobre el agua de su comarca.

**El umbral decide de qué responde la empresa.** Si se sube de 3,5 a **4,5**, el agua y los residuos salen del informe sin que la empresa haya hecho nada: solo quedan los dos asuntos que también le afectan económicamente. Por eso el umbral se justifica, no se elige.

---

## 4. Reto a realizar (entregable)

### Parte 1 · Completa la clase `Ut6Plan.java`

```bash
mvn test -Dtest=Ut6PlanTest
```

Cinco ya los has hecho en la batería. Faltan estos:

| # | Método | Qué debe hacer | Pista |
|:-:|---|---|---|
| 1 | `estrategiaGrupo` | Las cuatro casillas de la matriz | Valida el rango 1-5 primero. Después dos booleanos (`influyente`, `interesado`) y tres `if`: el cuarto caso es el `return` final |
| 3 | `tipoMaterialidad` | AMBAS, IMPACTO, FINANCIERA o NO MATERIAL | Los mismos dos booleanos del método anterior de la batería |
| 6 | `estado` | COMPLETADA, AVANZADA, INICIADA, SIN EMPEZAR | Cuatro tramos **de mayor a menor** |
| 7 | `vaEnPlazo` | Si el progreso alcanza al tiempo consumido | `Math.min(1.0, (double) meses / total)`. Ese `(double)` es obligatorio: sin él, `18/36` en enteros da **0** |
| 8 | `aspectosSinAccion` | Los aspectos que nadie está atendiendo | Dos bucles anidados y `equalsIgnoreCase`. Recoge en `ArrayList<String>` y termina con `toArray(new String[0])` |

### Parte 2 · Analiza el plan de esta empresa

Con los tests en verde, aplica tu programa al plan de **Códex Cloud S.L.**, con umbral de materialidad **4,0**:

| Aspecto | Impacto | Financiero | Importancia grupos |
|---|:-:|:-:|:-:|
| Consumo energético del centro de datos | 5 | 4 | 5 |
| Seguridad de los datos de clientes | 3 | 5 | 5 |
| Residuos electrónicos | 4 | 2 | 3 |
| Formación y retención del talento | 3 | 4 | 4 |
| Patrocinio de un club deportivo | 1 | 1 | 2 |

Grupos de interés:

| Grupo | Influencia | Interés |
|---|:-:|:-:|
| Clientes corporativos | 5 | 5 |
| Plantilla técnica | 3 | 4 |
| Inversores | 4 | 2 |
| Ayuntamiento | 2 | 3 |

Acciones en marcha:

| Acción | Responde al aspecto | Indicador | Base | Meta | Actual | Meses / plazo |
|---|---|---|---:|---:|---:|---|
| Contratar electricidad renovable | Consumo energético del centro de datos | % renovable | 30 | 100 | 65 | 24 / 48 |
| Certificación de seguridad | Seguridad de los datos de clientes | % controles implantados | 20 | 100 | 65 | 30 / 36 |

**Entrega un documento de una página con:**

1. Una **tabla de aspectos materiales** con su tipo de materialidad y su prioridad, **ordenados de mayor a menor**.
2. La **estrategia** de cada grupo de interés.
3. El **progreso, estado y si va en plazo** de las dos acciones.
4. **Tres frases** respondiendo a:
      - ¿Qué aspectos materiales se quedan **sin acción**?
      - Una de las dos acciones va **en plazo** y la otra **no**, aunque las dos estén «AVANZADA». ¿Cuál y por qué?
      - Escribe una **acción SMART** para el aspecto material sin acción de mayor prioridad: qué se hace, con qué indicador se mide, línea base, meta y plazo.

!!! tip "Sobre el umbral"
    Fíjate en qué pasa con «Formación y retención del talento» (impacto 3, financiero 4): entra por los pelos. Si el umbral fuera 4,5, saldría del informe **sin que la empresa hubiera hecho nada**. Por eso el umbral se justifica, no se elige.

---

## 5. Simulacro de examen

> **Esta es la evaluación de la unidad.** Tiene el mismo formato que la parte de RA6 del examen del trimestre, y **las preguntas de test del examen salen de este banco**. Si dominas esta sección, tienes el RA6 preparado.

```mermaid
flowchart LR
    S["Simulacro RA6"] --> P["Parte práctica<br/>3 métodos · 8 puntos"]
    S --> T["Banco de preguntas<br/>30 preguntas · el examen elige 4"]
    P --> N["Tu nota del RA6<br/>sobre 10"]
    T --> N
```

### Parte práctica · 8 puntos

Hazla **en 40 minutos**, sin mirar la batería ni tu reto. Abre `src/main/java/simulacro/SimulacroRa6.java`, completa los 3 métodos y lanza:

```bash
mvn test -Dtest=SimulacroRa6Test
```

```text
nota práctica = (tests superados ÷ 14) × 8
```

!!! warning "Las reglas no son las de clase"
    Cambian pesos, umbrales o redondeos, y el Javadoc lo avisa en mayúsculas. Es el fallo número uno del examen: código correcto con las constantes de clase.

### Banco de preguntas · 2 puntos

En el examen salen **4 preguntas de este banco**, a 0,5 puntos cada una. Para practicar, tápate las respuestas y hazlas todas.

#### A · Teoría y cálculo

**1.** Un grupo de interés es…

a) solo los accionistas · b) quien afecta a la empresa o se ve afectado por ella · c) la dirección · d) los clientes grandes

??? success "Respuesta"

    **b**

**2.** Influencia 5, interés 2 →

a) gestionar de cerca · b) mantener satisfecho · c) mantener informado · d) monitorizar

??? success "Respuesta"

    **b**

**3.** Influencia 2, interés 4 →

a) gestionar de cerca · b) mantener satisfecho · c) mantener informado · d) monitorizar

??? success "Respuesta"

    **c**

**4.** La doble materialidad considera material un asunto si…

a) lo es por impacto Y por finanzas · b) lo es por impacto O por finanzas · c) lo pide un cliente · d) sale en los ODS

??? success "Respuesta"

    **b**

**5.** Impacto 4, financiero 2, umbral 3,5. Es…

a) no material · b) material por impacto · c) material financiero · d) material por ambas

??? success "Respuesta"

    **b**

**6.** Programar `esMaterial` con `&&` haría que…

a) diera igual · b) desaparecieran los asuntos con impacto alto y efecto financiero bajo · c) no compilara · d) todo fuera material

??? success "Respuesta"

    **b**

**7.** Impacto 4, financiero 5, grupos 5. La prioridad es…

a) 4,5 · b) 4,7 · c) 5,0 · d) 4,0

??? success "Respuesta"

    **c**

**8.** Base 40, meta 100, actual 72. El progreso es…

a) 0,72 · b) 0,53 · c) 0,60 · d) 0,40

??? success "Respuesta"

    **b**

**9.** Base 1,8, meta 1,4, actual 1,6 (PUE). El progreso es…

a) 0,0 · b) 0,5 · c) 0,89 · d) no se puede, la meta baja

??? success "Respuesta"

    **b**

**10.** ¿Hace falta un `if` distinto para las metas que bajan?

a) sí · b) no, los dos signos negativos se cancelan · c) solo si el valor es negativo · d) sí, con valor absoluto

??? success "Respuesta"

    **b**

**11.** Si meta y línea base son iguales, `progreso` debe…

a) devolver 1,0 · b) lanzar IllegalArgumentException · c) devolver 0,0 · d) devolver NaN

??? success "Respuesta"

    **b**

**12.** Un progreso de 0,53 da estado…

a) COMPLETADA · b) AVANZADA · c) INICIADA · d) SIN EMPEZAR

??? success "Respuesta"

    **b**

**13.** Ese 0,53 con 18 de 36 meses consumidos…

a) va retrasada · b) va en plazo, muy justa · c) está completada · d) no se puede valorar

??? success "Respuesta"

    **b**

**14.** En el reto ejemplo, el aspecto de mayor prioridad era…

a) consumo energético · b) privacidad de datos · c) uso de agua · d) residuos electrónicos

??? success "Respuesta"

    **b**, con 5,0.

**15.** Y lo llamativo de ese aspecto era que…

a) ya estaba resuelto · b) no tenía ninguna acción asignada · c) no era material · d) lo llevaba otro departamento

??? success "Respuesta"

    **b**

**16.** En el reto ejemplo, subir el umbral de 3,5 a 4,5 haría que…

a) no cambiara nada · b) el agua y los residuos salieran del informe sin hacer nada · c) mejorara la cobertura · d) fuera obligatorio

??? success "Respuesta"

    **b**

**17.** En `aspectosSinAccion` se usa `equalsIgnoreCase` porque…

a) es más rápido · b) los nombres llegan con mayúsculas dispares según la fuente · c) lo exige Java · d) para ordenar

??? success "Respuesta"

    **b**

**18.** Publicar los aspectos materiales sin acción…

a) perjudica a la empresa · b) es la señal de que el informe es honesto · c) está prohibido · d) es opcional y poco útil

??? success "Respuesta"

    **b**

**19.** Con "alto" = 4 o más, un grupo con influencia 3 e interés 5 debe…

a) gestionarse de cerca · b) mantenerse satisfecho · c) mantenerse informado · d) monitorizarse

??? success "Respuesta"

    c. La influencia 3 ya no es alta en el simulacro.

**20.** Con la fórmula del simulacro, la prioridad de (5, 5, 4,5) es…

a) 4,8 · b) 4,75 · c) 5,0 · d) 14,5

??? success "Respuesta"

    a. (5 + 5 + 4,5) / 3 = 4,83 → 4,8.

**21.** Un progreso de 0,80 tiene estado…

a) COMPLETADA · b) CASI · c) EN MARCHA · d) AVANZADA

??? success "Respuesta"

    b. Desde 0,75. «AVANZADA» es un estado de clase que aquí no existe.

**22.** La doble materialidad considera material un aspecto si…

a) lo es por impacto y por finanzas · b) lo es por impacto o por finanzas · c) lo decide el consejo · d) aparece en un ODS

??? success "Respuesta"

    b.

#### B · ¿Qué devuelve este código?

Sin ejecutarlo. Razona con las reglas de la unidad, no con las del simulacro.

**23.** ¿Qué devuelve `Ut6Plan.estrategiaGrupo(3, 2)`?

a) `"MONITORIZAR"` · b) `"MANTENER SATISFECHO"` · c) `"MANTENER INFORMADO"` · d) `"GESTIONAR DE CERCA"`

??? success "Respuesta"

    **b**. La influencia 3 ya es alta; el interés 2 es bajo.

**24.** ¿Qué devuelve `Ut6Plan.esMaterial(3.4, 3.4, 3.5)`?

a) `true` · b) `false` · c) excepción · d) `3.4`

??? success "Respuesta"

    **b**. Ninguna de las dos llega al umbral.

**25.** ¿Qué devuelve `Ut6Plan.tipoMaterialidad(2, 4, 3.5)`?

a) `"IMPACTO"` · b) `"AMBAS"` · c) `"FINANCIERA"` · d) `"NO MATERIAL"`

??? success "Respuesta"

    **c**. Solo la financiera alcanza el umbral.

**26.** ¿Qué devuelve `Ut6Plan.prioridad(2, 3, 5)`?

a) `3.3` · b) `4.0` · c) `5.0` · d) `2.5`

??? success "Respuesta"

    **b**. (máx(2, 3) + 5) / 2. La a) es la media de las tres, que es otra fórmula.

**27.** ¿Qué devuelve `Ut6Plan.progreso(100, 50, 75)`?

a) `-0.5` · b) `0.75` · c) `0.5` · d) `1.0`

??? success "Respuesta"

    **c**. (75 − 100) / (50 − 100): la meta baja y los dos negativos se cancelan.

**28.** ¿Qué devuelve `Ut6Plan.estado(0.49)`?

a) `"AVANZADA"` · b) `"INICIADA"` · c) `"SIN EMPEZAR"` · d) `"COMPLETADA"`

??? success "Respuesta"

    **b**. AVANZADA empieza en 0,5.

**29.** ¿Qué devuelve `Ut6Plan.vaEnPlazo(0.4, 12, 24)`?

a) `true` · b) `false` · c) `0.5` · d) excepción

??? success "Respuesta"

    **b**. Ha pasado la mitad del plazo (0,5) y el progreso es 0,4.

**30.** ¿Qué devuelve `Ut6Plan.aspectosSinAccion(new String[]{"A","B","C"}, new String[]{"b"}).length`?

a) `3` · b) `1` · c) `2` · d) `0`

??? success "Respuesta"

    **c**. «b» casa con «B» sin distinguir mayúsculas. Quedan A y C.

### Tu nota del simulacro

```text
nota RA6 = (tests superados ÷ 14) × 8  +  aciertos en 4 preguntas × 0,5
```

Si sale por debajo de 5, vuelve a la batería de la unidad antes del examen del trimestre.
