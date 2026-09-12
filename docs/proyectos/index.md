# Los seis proyectos

Cada unidad termina en un **proyecto Spring Boot** que tú completas y que se corrige solo con tests. La mecánica es siempre la misma: el modelo, los controladores y los datos ya están hechos; **tú escribes el servicio**, que es donde vive la lógica de sostenibilidad.

| Unidad | Proyecto | Qué construyes | Tests | Criterio del RA |
|:---:|---|---|:---:|---|
| **1** | [Radar ASG](ud1/README.md) | Clasificación ASG, cumplimiento de indicadores, puntuación y rating | 29 | `EnumMap` |
| **2** | [Observatorio energético](ud2/README.md) | Intensidad de carbono de la red con datos de Red Eléctrica | 18 | API de Streams |
| **3** | [Auditor web sostenible](ud3/README.md) | Auditoría de recursos web y huella de los desplazamientos | 21 | `server.compression.enabled=true` |
| **4** | [ReUsa](ud4/README.md) | Ciclo de vida circular de los equipos informáticos | 24 | `IllegalStateException` |
| **5** | [Laboratorio de huella](ud5/README.md) | Medir, optimizar y programar según la huella del software | 26 | `@Scheduled` |
| **6** | [Plan de sostenibilidad](ud6/README.md) | Materialidad, acciones, indicadores e informe | 19 | `Comparator` |

## Cómo se trabaja cualquiera de ellos

```bash
cd docs/proyectos/ud1
./mvnw test              # Windows: mvnw.cmd test
./mvnw spring-boot:run   # http://localhost:8080
```

1. **Lee** la clase del servicio: cada método tiene un Javadoc que dice exactamente qué debe hacer.
2. **Escribe** el código sustituyendo cada `TODO` y borra el `throw new UnsupportedOperationException("TODO")`.
3. **Ejecuta los tests** y arregla los fallos de uno en uno hasta ver `BUILD SUCCESS`.
4. **Arranca** la aplicación y prueba la API con el navegador o `curl`.
5. **Rellena** `INTERPRETACION.md`: resultado, impacto y acción propuesta.

```bash
./mvnw test -Dsurefire.skipAfterFailureCount=1        # para en el primer fallo
./mvnw test -Dtest=AsgServiceTest#ratingPorTramos     # un solo test
```

## Estructura común

```text
proyecto-udN/
├── pom.xml, mvnw, mvnw.cmd          Maven Wrapper: no hace falta instalar Maven
├── README.md                        qué construyes y cómo empezar
├── INTERPRETACION.md                tu análisis (criterio 4 de la rúbrica)
└── src/
    ├── main/java/…/modelo/          records, enums y entidades (ya hechos)
    ├── main/java/…/servicio/        TU CÓDIGO: la lógica de sostenibilidad
    ├── main/java/…/web/             controladores y repositorios (ya hechos)
    ├── main/resources/              datos de ejemplo y configuración
    └── test/java/…/                 los tests: la especificación
```

!!! warning "Los tests son la especificación"
    No los modifiques para que pasen. Describen exactamente lo que tu código debe hacer, y el examen de cada trimestre usa una batería equivalente que no vas a poder tocar.

!!! tip "Si algo no arranca"
    Repasa [Entorno de trabajo](../recursos/entorno.md): versión del JDK, permisos de `mvnw` y primera descarga de dependencias.
