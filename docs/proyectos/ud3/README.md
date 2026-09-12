# Proyecto Auditor web sostenible · UD3 (RA3)

Aplica criterios de sostenibilidad a tu trabajo como desarrollador: **audita los recursos** que sirve una web (compresión, caché, peso y formato de imagen) y calcula la **huella de los desplazamientos** al trabajo y lo que se ahorra cambiando de transporte. Además, configuras tu propia aplicación para que sirva las respuestas comprimidas.

Completa los `TODO` de `AuditoriaService.java` hasta que **todos los tests pasen**, y rellena `INTERPRETACION.md`.

## Estructura

```
proyecto-ud3/
├── pom.xml, mvnw, mvnw.cmd
├── INTERPRETACION.md                 ← tu análisis (criterio 4 de la rúbrica)
├── src/main/java/es/daw/sostenibilidad/auditoria/
│   ├── modelo/Recurso.java, Hallazgo.java, Transporte.java
│   ├── servicio/AuditoriaService.java   ← tu código (métodos con TODO)
│   └── web/AuditoriaController.java     ← ya hecho
├── src/main/resources/application.properties   ← TODO RA3: activar la compresión
├── src/main/resources/static/          ← página y CSS de prueba
└── src/test/java/…/AuditoriaServiceTest.java   ← los tests (no se tocan)
```

## Puesta en marcha

```bash
./mvnw test              # Windows: mvnw.cmd test  · al principio falla: aún no has escrito nada
./mvnw spring-boot:run   # arranca la aplicación en http://localhost:8080
```

La primera vez el wrapper descarga Maven y las dependencias: tarda un poco. Después va rápido.

Con la aplicación arrancada:

- `POST /api/auditoria` con una lista JSON de recursos → hallazgos, recomendaciones y puntuación
- `GET /api/catalogo` → JSON grande para comparar el peso con y sin compresión

## Cómo trabajar

1. **Lee** `AuditoriaService.java`: cada método tiene un Javadoc que dice exactamente qué debe hacer.
2. **Escribe** el código sustituyendo cada `TODO` y borra el `throw new UnsupportedOperationException("TODO")`.
3. **Ejecuta los tests** y ve arreglando lo que falle:

```bash
./mvnw test                                        # todos
./mvnw test -Dsurefire.skipAfterFailureCount=1     # para en el primer fallo
./mvnw test -Dtest=AuditoriaServiceTest#nombreDelTest   # solo uno
```

4. Repite hasta que salga `BUILD SUCCESS`. Después arranca la aplicación y prueba la API.

!!! warning "Los tests son la especificación"
    No los modifiques para que pasen: describen lo que tu código debe hacer, y el examen usará una batería equivalente.

## Criterio específico del RA3 (rúbrica, punto 2)

El fichero **`application.properties`** debe activar la compresión HTTP con `server.compression.enabled=true`.

## Interpretación (rúbrica, punto 4)

Un número sin interpretar no sirve para tomar decisiones. En `INTERPRETACION.md` explicas qué significa tu resultado: tres apartados obligatorios (**Resultado**, **Impacto** y **Acción propuesta**) con al menos **40 palabras** cada uno.
