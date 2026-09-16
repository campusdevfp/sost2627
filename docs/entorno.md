# Entorno de trabajo

## Lo que necesitas

- **JDK 21** o superior (Eclipse Temurin u otra distribución).
- Un IDE: **IntelliJ IDEA Community** o **VS Code** con *Extension Pack for Java*. Los dos traen Maven incorporado, así que no hace falta instalarlo aparte.

```bash
java -version    # debe decir 21 o superior
```

!!! warning "JRE no es JDK"
    Para compilar necesitas el JDK, que trae `javac`. Si Maven se queja de que no encuentra el compilador, tienes solo el JRE o `JAVA_HOME` apunta a otro sitio.

## Poner en marcha el proyecto

1. Descarga o clona el repositorio.
2. Abre la carpeta `proyecto` con tu IDE. Detectará el `pom.xml` solo.
3. La primera vez, Maven descarga JUnit: necesita internet un momento. Después ya no.

```bash
cd proyecto
mvn test
```

Verás **124 tests y 124 fallos**. Es lo correcto: todos los métodos están sin hacer.

## El ciclo de trabajo

```bash
mvn test                                    # todos
mvn test -Dtest=Ut1AsgTest                  # solo una unidad
mvn test -Dtest=Ut1AsgTest#clasifica        # un solo test
```

En el IDE es aún más cómodo: junto a cada método de test hay un botón de ejecutar. Úsalo para ir de uno en uno.

**Leer un fallo:**

```text
[ERROR] Ut1AsgTest.cumplimientoMayorEsMejor:31
        expected: <0.75> but was: <1.0>
```

Te dice el test, la línea y qué esperaba frente a lo que obtuvo. Abre el test, mira con qué datos te llama y compara con tu código.

## Estructura

```text
proyecto/
├── pom.xml                                   Java 21 + JUnit 5
└── src/
    ├── main/java/sostenibilidad/
    │   ├── Ut1Asg.java        ← tus 8 métodos de la UT1
    │   ├── Ut2Energia.java
    │   ├── Ut3Desarrollo.java
    │   ├── Ut4Circular.java
    │   ├── Ut5Huella.java
    │   └── Ut6Plan.java
    └── test/java/sostenibilidad/
        └── Ut1AsgTest.java …                 los tests: NO se tocan
```

!!! warning "Los tests son el enunciado"
    No los modifiques para que pasen. Describen exactamente lo que debe hacer tu código, y el examen usa una batería equivalente.

## Dudas frecuentes

**`mvn` no se reconoce.** Usa el terminal del IDE, que ya lo trae configurado, o instala Maven y añádelo al `PATH`.

**Los tests siguen fallando después de escribir el método.** Comprueba que has borrado la línea `throw new UnsupportedOperationException(...)`.

**Un test espera un decimal exacto y me sale por poco.** Revisa el redondeo. Casi todos los métodos piden `Math.round(x * 10) / 10.0` (un decimal) o `* 100 / 100.0` (dos).

**Me sale `NaN` o `Infinity`.** Estás dividiendo entre cero. Casi siempre falta comprobar el caso límite antes de la división.
