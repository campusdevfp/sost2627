# Entorno de trabajo

Antes de la primera unidad prepara tu **entorno de Java** para los proyectos. Es el mismo que usas en DWES; si ya lo tienes, comprueba solo las versiones.

## 1. JDK y editor

### 1.1 Instalar el JDK

Instala un **JDK 21 o superior** (Eclipse Temurin, Oracle JDK o el paquete de tu distribución) y comprueba la versión:

```bash
java -version      # openjdk version "21.x" o superior
```

!!! warning "JRE no es JDK"
    Para compilar necesitas el **JDK** (trae `javac`). Si `java -version` funciona pero Maven se queja de que no encuentra el compilador, tienes solo el JRE o `JAVA_HOME` apunta a otro sitio.

### 1.2 Editor

- **IntelliJ IDEA Community**: abre la carpeta del proyecto y detecta el `pom.xml` solo.
- **VS Code** con *Extension Pack for Java* y *Spring Boot Extension Pack*.

Los dos permiten lanzar un test concreto con el icono verde junto al método, que es la forma más cómoda de ir de uno en uno.

## 2. Maven Wrapper: compilar sin instalar Maven

Cada proyecto trae `mvnw` (Linux/macOS) y `mvnw.cmd` (Windows). La primera vez descargan la versión de Maven indicada en `.mvn/wrapper/maven-wrapper.properties` y todas las dependencias; después funcionan sin volver a descargar.

```bash
cd docs/proyectos/ud1
./mvnw test                     # Windows: mvnw.cmd test
./mvnw spring-boot:run          # arranca la aplicación en http://localhost:8080
```

!!! tip "Si `./mvnw` da «Permiso denegado»"
    En Linux o macOS dale permiso de ejecución una vez: `chmod +x mvnw`.

### 2.1 El ciclo de trabajo de cada proyecto

```bash
./mvnw test                                        # todos los tests
./mvnw test -Dsurefire.skipAfterFailureCount=1     # para en el primer fallo
./mvnw test -Dtest=AsgServiceTest                  # una clase de test
./mvnw test -Dtest=AsgServiceTest#ratingPorTramos  # un solo test
```

Lees un método con su Javadoc, lo escribes, lanzas los tests y repites hasta ver `BUILD SUCCESS`. Después arrancas la aplicación y pruebas la API.

### 2.2 Leer un fallo de test

```text
[ERROR] AsgServiceTest.cumplimientoParcialCuandoMayorEsMejor:58
        expected: <0.75> but was: <1.0>
```

El mensaje dice **qué test** falla, **en qué línea** y **qué esperaba frente a lo que obtuvo**. Abre el test, mira los datos de entrada y compara con tu código: casi siempre es suficiente.

## 3. Probar la API

Con la aplicación arrancada puedes usar el navegador para los `GET` y `curl` para todo lo demás:

```bash
curl http://localhost:8080/api/radar
curl -X PATCH "http://localhost:8080/api/equipos/1/estado?nuevo=EN_REPARACION"
curl -X POST http://localhost:8080/api/auditoria \
     -H "Content-Type: application/json" -d @recursos.json
```

Si prefieres una interfaz gráfica, cualquier cliente HTTP (el integrado en IntelliJ, Bruno, Postman…) sirve igual.

## 4. Herramientas de medición que usaremos

| Herramienta | Para qué | Unidad |
|---|---|---|
| Pestaña **Red** del navegador (F12) | Peso, compresión y caché de cada recurso | UD3, UD5 |
| `curl -I` y `curl --compressed` | Ver cabeceras y comparar bytes transferidos | UD3 |
| **Estadísticas de Hibernate** | Contar las consultas SQL de cada petición | UD5 |
| **Spring Boot Actuator** | Tiempos de respuesta en `/actuator/metrics` | UD5 |
| API **REData** de Red Eléctrica | Estructura de generación e intensidad de la red | UD2, UD5 |

## 5. Base de datos

Los proyectos que usan JPA (UD4 y UD5) trabajan con **H2 en memoria**: se crea al arrancar y desaparece al parar. No hay que instalar nada. Si en DWES usáis MySQL o PostgreSQL, puedes cambiar la URL en `application.properties`; los tests no dependen de la base de datos.

!!! note "Conexión a Internet"
    Solo la necesitas la primera vez (para descargar dependencias) y en los apartados que consultan la API de Red Eléctrica. Todos los proyectos traen **datos de ejemplo** para trabajar sin conexión.
