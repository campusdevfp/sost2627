# Proyecto ReUsa · Gestión circular de equipos · UD4 (RA4)

Gestiona el **ciclo de vida** de los equipos informáticos del centro con criterios de **economía circular**: estados y transiciones permitidas, huella anualizada, ahorro de CO₂ por alargar la vida útil, jerarquía de las R y tasa de circularidad. Usa JPA con una base de datos H2 en memoria.

Completa los `TODO` de `CircularidadService.java` hasta que **todos los tests pasen**, y rellena `INTERPRETACION.md`.

## Estructura

```
proyecto-ud4/
├── pom.xml, mvnw, mvnw.cmd
├── INTERPRETACION.md                 ← tu análisis (criterio 4 de la rúbrica)
├── src/main/java/es/daw/sostenibilidad/circular/
│   ├── modelo/Equipo.java (entidad JPA), EstadoEquipo.java, OpcionR.java
│   ├── servicio/CircularidadService.java   ← tu código (métodos con TODO)
│   └── web/EquipoRepository.java, EquipoController.java   ← ya hechos
├── src/main/resources/data.sql   ← inventario inicial
└── src/test/java/…/CircularidadServiceTest.java   ← los tests (no se tocan)
```

## Puesta en marcha

```bash
./mvnw test              # Windows: mvnw.cmd test  · al principio falla: aún no has escrito nada
./mvnw spring-boot:run   # arranca la aplicación en http://localhost:8080
```

La primera vez el wrapper descarga Maven y las dependencias: tarda un poco. Después va rápido.

Con la aplicación arrancada:

- `GET /api/equipos` → inventario
- `PATCH /api/equipos/1/estado?nuevo=EN_REPARACION` → cambia el estado (409 si no está permitido)
- `GET /api/circularidad` → tasa de circularidad
- `GET /api/candidatos?edadMinima=4` → equipos para reacondicionar

## Cómo trabajar

1. **Lee** `CircularidadService.java`: cada método tiene un Javadoc que dice exactamente qué debe hacer.
2. **Escribe** el código sustituyendo cada `TODO` y borra el `throw new UnsupportedOperationException("TODO")`.
3. **Ejecuta los tests** y ve arreglando lo que falle:

```bash
./mvnw test                                        # todos
./mvnw test -Dsurefire.skipAfterFailureCount=1     # para en el primer fallo
./mvnw test -Dtest=CircularidadServiceTest#nombreDelTest   # solo uno
```

4. Repite hasta que salga `BUILD SUCCESS`. Después arranca la aplicación y prueba la API.

!!! warning "Los tests son la especificación"
    No los modifiques para que pasen: describen lo que tu código debe hacer, y el examen usará una batería equivalente.

## Criterio específico del RA4 (rúbrica, punto 2)

El código debe lanzar **`IllegalStateException`** cuando una transición del ciclo de vida no está permitida.

## Interpretación (rúbrica, punto 4)

Un número sin interpretar no sirve para tomar decisiones. En `INTERPRETACION.md` explicas qué significa tu resultado: tres apartados obligatorios (**Resultado**, **Impacto** y **Acción propuesta**) con al menos **40 palabras** cada uno.
