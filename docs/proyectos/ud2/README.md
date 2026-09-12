# Proyecto Observatorio energético · UD2 (RA2)

Calcula la **intensidad de carbono** de la electricidad (gramos de CO₂ por kWh) a partir de la estructura de generación del sistema eléctrico español, el porcentaje renovable y el día más limpio. Funciona sin conexión con un CSV de ejemplo y, con conexión, con datos reales de la API REData de Red Eléctrica.

Completa los `TODO` de `IntensidadService.java` hasta que **todos los tests pasen**, y rellena `INTERPRETACION.md`.

## Estructura

```
proyecto-ud2/
├── pom.xml, mvnw, mvnw.cmd
├── INTERPRETACION.md                 ← tu análisis (criterio 4 de la rúbrica)
├── src/main/java/es/daw/sostenibilidad/energia/
│   ├── modelo/Generacion.java
│   ├── servicio/IntensidadService.java   ← tu código (métodos con TODO)
│   └── web/ReeCliente.java, ObservatorioController.java   ← ya hechos
├── src/main/resources/datos/generacion-ejemplo.csv
└── src/test/java/…/IntensidadServiceTest.java   ← los tests (no se tocan)
```

## Puesta en marcha

```bash
./mvnw test              # Windows: mvnw.cmd test  · al principio falla: aún no has escrito nada
./mvnw spring-boot:run   # arranca la aplicación en http://localhost:8080
```

La primera vez el wrapper descarga Maven y las dependencias: tarda un poco. Después va rápido.

Con la aplicación arrancada:

- `GET /api/intensidad` → resumen con los datos de ejemplo
- `GET /api/intensidad/ree?desde=2026-03-02&hasta=2026-03-08` → resumen con datos reales de REE
- `GET /api/consumo?kwh=250&intensidad=120` → kg de CO₂ de un consumo

## Cómo trabajar

1. **Lee** `IntensidadService.java`: cada método tiene un Javadoc que dice exactamente qué debe hacer.
2. **Escribe** el código sustituyendo cada `TODO` y borra el `throw new UnsupportedOperationException("TODO")`.
3. **Ejecuta los tests** y ve arreglando lo que falle:

```bash
./mvnw test                                        # todos
./mvnw test -Dsurefire.skipAfterFailureCount=1     # para en el primer fallo
./mvnw test -Dtest=IntensidadServiceTest#nombreDelTest   # solo uno
```

4. Repite hasta que salga `BUILD SUCCESS`. Después arranca la aplicación y prueba la API.

!!! warning "Los tests son la especificación"
    No los modifiques para que pasen: describen lo que tu código debe hacer, y el examen usará una batería equivalente.

## Criterio específico del RA2 (rúbrica, punto 2)

El código debe usar la **API de Streams** (`.stream()`) para agregar la serie de datos energéticos.

## Interpretación (rúbrica, punto 4)

Un número sin interpretar no sirve para tomar decisiones. En `INTERPRETACION.md` explicas qué significa tu resultado: tres apartados obligatorios (**Resultado**, **Impacto** y **Acción propuesta**) con al menos **40 palabras** cada uno.
