# Proyecto Plan de sostenibilidad · UD6 (RA6)

Construye el **plan de sostenibilidad** de una empresa: estrategia con cada grupo de interés, **doble materialidad**, priorización de aspectos, progreso de las acciones con sus indicadores y detección de aspectos materiales sin respuesta. Genera un **informe HTML** con Thymeleaf listo para guardar como PDF.

Completa los `TODO` de `PlanService.java` hasta que **todos los tests pasen**, y rellena `INTERPRETACION.md`.

## Estructura

```
proyecto-ud6/
├── pom.xml, mvnw, mvnw.cmd
├── INTERPRETACION.md                 ← tu análisis (criterio 4 de la rúbrica)
├── src/main/java/es/daw/sostenibilidad/plan/
│   ├── modelo/GrupoInteres.java, AspectoMaterial.java, Accion.java, Dimension.java
│   ├── servicio/PlanService.java        ← tu código (métodos con TODO)
│   └── web/PlanRepositorio.java, InformeController.java   ← ya hechos
├── src/main/resources/templates/informe.html   ← informe Thymeleaf
└── src/test/java/…/PlanServiceTest.java   ← los tests (no se tocan)
```

## Puesta en marcha

```bash
./mvnw test              # Windows: mvnw.cmd test  · al principio falla: aún no has escrito nada
./mvnw spring-boot:run   # arranca la aplicación en http://localhost:8080
```

La primera vez el wrapper descarga Maven y las dependencias: tarda un poco. Después va rápido.

Con la aplicación arrancada:

- `GET /informe?umbral=3.5` → informe de sostenibilidad en HTML

## Cómo trabajar

1. **Lee** `PlanService.java`: cada método tiene un Javadoc que dice exactamente qué debe hacer.
2. **Escribe** el código sustituyendo cada `TODO` y borra el `throw new UnsupportedOperationException("TODO")`.
3. **Ejecuta los tests** y ve arreglando lo que falle:

```bash
./mvnw test                                        # todos
./mvnw test -Dsurefire.skipAfterFailureCount=1     # para en el primer fallo
./mvnw test -Dtest=PlanServiceTest#nombreDelTest   # solo uno
```

4. Repite hasta que salga `BUILD SUCCESS`. Después arranca la aplicación y prueba la API.

!!! warning "Los tests son la especificación"
    No los modifiques para que pasen: describen lo que tu código debe hacer, y el examen usará una batería equivalente.

## Criterio específico del RA6 (rúbrica, punto 2)

El código debe ordenar los aspectos prioritarios con un **`Comparator`**.

## Interpretación (rúbrica, punto 4)

Un número sin interpretar no sirve para tomar decisiones. En `INTERPRETACION.md` explicas qué significa tu resultado: tres apartados obligatorios (**Resultado**, **Impacto** y **Acción propuesta**) con al menos **40 palabras** cada uno.
