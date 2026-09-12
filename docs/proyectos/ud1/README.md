# Proyecto Radar ASG · UD1 (RA1)

Clasifica temas de sostenibilidad en sus dimensiones **ambiental, social y de gobernanza (ASG)**, mide cuánto cumple una empresa sus indicadores y calcula una puntuación y un rating ASG, igual que hacen (con muchos más datos) las agencias que analizan empresas para los fondos de inversión socialmente responsable.

Completa los `TODO` de `AsgService.java` hasta que **todos los tests pasen**, y rellena `INTERPRETACION.md`.

## Estructura

```
proyecto-ud1/
├── pom.xml, mvnw, mvnw.cmd
├── INTERPRETACION.md                 ← tu análisis (criterio 4 de la rúbrica)
├── src/main/java/es/daw/sostenibilidad/asg/
│   ├── modelo/Dimension.java, Indicador.java
│   ├── servicio/AsgService.java      ← tu código (métodos con TODO)
│   └── web/RadarController.java      ← API REST (ya hecha)
├── src/main/resources/datos/indicadores.csv   ← indicadores de la empresa
└── src/test/java/…/AsgServiceTest.java   ← los tests (no se tocan)
```

## Puesta en marcha

```bash
./mvnw test              # Windows: mvnw.cmd test  · al principio falla: aún no has escrito nada
./mvnw spring-boot:run   # arranca la aplicación en http://localhost:8080
```

La primera vez el wrapper descarga Maven y las dependencias: tarda un poco. Después va rápido.

Con la aplicación arrancada:

- `GET /api/indicadores` → los indicadores del CSV
- `GET /api/radar` → puntuación por dimensión, puntuación global, rating y ODS cubiertos
- `GET /api/clasificar?tema=Horas de formación` → dimensión del tema

## Cómo trabajar

1. **Lee** `AsgService.java`: cada método tiene un Javadoc que dice exactamente qué debe hacer.
2. **Escribe** el código sustituyendo cada `TODO` y borra el `throw new UnsupportedOperationException("TODO")`.
3. **Ejecuta los tests** y ve arreglando lo que falle:

```bash
./mvnw test                                        # todos
./mvnw test -Dsurefire.skipAfterFailureCount=1     # para en el primer fallo
./mvnw test -Dtest=AsgServiceTest#nombreDelTest   # solo uno
```

4. Repite hasta que salga `BUILD SUCCESS`. Después arranca la aplicación y prueba la API.

!!! warning "Los tests son la especificación"
    No los modifiques para que pasen: describen lo que tu código debe hacer, y el examen usará una batería equivalente.

## Criterio específico del RA1 (rúbrica, punto 2)

El código debe usar un **`EnumMap`** para agregar las puntuaciones por dimensión ASG.

## Interpretación (rúbrica, punto 4)

Un número sin interpretar no sirve para tomar decisiones. En `INTERPRETACION.md` explicas qué significa tu resultado: tres apartados obligatorios (**Resultado**, **Impacto** y **Acción propuesta**) con al menos **40 palabras** cada uno.
