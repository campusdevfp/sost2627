# Proyecto Laboratorio de huella del software · UD5 (RA5)

Estima la **huella de carbono del software** (modelo Sustainable Web Design y especificación SCI), detecta el **problema N+1**, calcula reducciones y decide **cuándo ejecutar una tarea** según lo limpia que esté la red (*carbon-aware*). La aplicación trae un catálogo deliberadamente ineficiente para medir el antes y el después.

Completa los `TODO` de `HuellaService.java` hasta que **todos los tests pasen**, y rellena `INTERPRETACION.md`.

## Estructura

```
proyecto-ud5/
├── pom.xml, mvnw, mvnw.cmd
├── INTERPRETACION.md                 ← tu análisis (criterio 4 de la rúbrica)
├── src/main/java/es/daw/sostenibilidad/huella/
│   ├── modelo/Producto.java, FichaAmbiental.java
│   ├── servicio/HuellaService.java      ← tu código (métodos con TODO)
│   ├── tarea/TareaCarbonAware.java      ← TODO RA5: programarla con @Scheduled
│   └── web/CatalogoController.java      ← TODO LAB: versión v2 optimizada
│       MedicionFilter.java             ← mide bytes y CO₂ de cada respuesta (ya hecho)
└── src/test/java/…/HuellaServiceTest.java   ← los tests (no se tocan)
```

## Puesta en marcha

```bash
./mvnw test              # Windows: mvnw.cmd test  · al principio falla: aún no has escrito nada
./mvnw spring-boot:run   # arranca la aplicación en http://localhost:8080
```

La primera vez el wrapper descarga Maven y las dependencias: tarda un poco. Después va rápido.

Con la aplicación arrancada:

- `GET /api/v1/productos` → versión ineficiente (el "antes")
- `GET /api/v2/productos` → la versión que optimizas en el laboratorio
- `GET /actuator/metrics/http.server.requests` → tiempos medidos por Actuator

## Cómo trabajar

1. **Lee** `HuellaService.java`: cada método tiene un Javadoc que dice exactamente qué debe hacer.
2. **Escribe** el código sustituyendo cada `TODO` y borra el `throw new UnsupportedOperationException("TODO")`.
3. **Ejecuta los tests** y ve arreglando lo que falle:

```bash
./mvnw test                                        # todos
./mvnw test -Dsurefire.skipAfterFailureCount=1     # para en el primer fallo
./mvnw test -Dtest=HuellaServiceTest#nombreDelTest   # solo uno
```

4. Repite hasta que salga `BUILD SUCCESS`. Después arranca la aplicación y prueba la API.

!!! warning "Los tests son la especificación"
    No los modifiques para que pasen: describen lo que tu código debe hacer, y el examen usará una batería equivalente.

## Criterio específico del RA5 (rúbrica, punto 2)

La tarea `TareaCarbonAware` debe estar programada con **`@Scheduled`** y decidir con `ejecutarAhora(...)`.

## Interpretación (rúbrica, punto 4)

Un número sin interpretar no sirve para tomar decisiones. En `INTERPRETACION.md` explicas qué significa tu resultado: tres apartados obligatorios (**Resultado**, **Impacto** y **Acción propuesta**) con al menos **40 palabras** cada uno.
