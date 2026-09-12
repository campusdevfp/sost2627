# Introducción al módulo profesional

> Esta página es la referencia completa del módulo: qué es, qué normativa lo regula, qué hay que aprender, cómo se trabaja en clase y cómo se califica. Si buscas la versión rápida y práctica, ve a [El curso de un vistazo](el-curso.md).

## 1. Identificación

| | |
|---|---|
| **Módulo profesional** | Sostenibilidad aplicada al sistema productivo |
| **Código** | 1708 |
| **Duración** | 30 horas (1 hora semanal) |
| **Equivalencia** | 3 créditos ECTS |
| **Carácter** | Módulo profesional **común** y **obligatorio** en todos los ciclos formativos de grado medio y superior |
| **Ciclo en el que se imparte aquí** | Desarrollo de Aplicaciones Web (DAW), grado superior |
| **Convalidación** | Se considera el mismo módulo en cualquier ciclo: una vez superado, la calificación se traslada a otros ciclos que se cursen |

!!! warning "Comprueba el curso y el currículo autonómico"
    El curso en el que se imparte el 1708 **lo fija cada comunidad autónoma**: hay currículos que lo sitúan en primero y otros en segundo. Antes de usar esta programación, confirma en la normativa de la Comunidad de Madrid el curso asignado, la distribución horaria y cualquier concreción curricular propia.

## 2. Marco normativo

| Norma | Qué aporta |
|---|---|
| **Ley Orgánica 3/2022**, de 31 de marzo, de ordenación e integración de la Formación Profesional | Ordena el sistema de FP y define los grados formativos |
| **Real Decreto 659/2023**, de 18 de julio, por el que se desarrolla la ordenación del Sistema de Formación Profesional | Regula el módulo 1708. Su **anexo VIII** fija el currículo básico: resultados de aprendizaje, criterios de evaluación, contenidos y orientaciones pedagógicas |
| **Real Decreto 278/2023**, de 11 de abril | Establece el calendario de implantación del nuevo sistema |
| Normativa del título de **Desarrollo de Aplicaciones Web** y **currículo de la Comunidad de Madrid** | Concreta el ciclo, el curso y la organización horaria |
| Instrucciones anuales de organización y funcionamiento de la Comunidad de Madrid | Calendario, sesiones de evaluación y convocatorias del curso vigente |

La finalidad que fija el RD 659/2023 para este módulo es el desarrollo de **conocimiento y competencias básicas en economía verde, sostenibilidad e impacto ambiental de la actividad**, así como las condiciones en que las exigencias de la transición ecológica modifican los procesos productivos del sector correspondiente. En nuestro caso, ese sector es el **desarrollo de aplicaciones web**.

## 3. Enfoque: por qué se programa en Java

El módulo es transversal, pero se aplica a cada sector. Un ciclo de mecanizado lo aterriza en máquinas y materiales; nosotros lo aterrizamos en **software**, que es nuestro proceso productivo.

La decisión metodológica central es que **Spring Boot es el vehículo, no el objeto de evaluación**. El alumnado de DAW ya programa con Java 21 y Spring Boot en *Desarrollo Web en Entorno Servidor* (DWES), así que:

- no se pierde tiempo en enseñar la herramienta, se aprovecha la que ya domina;
- cada concepto de sostenibilidad acaba en **un número que se calcula y se interpreta**, no en una definición que se memoriza;
- la corrección es objetiva y rápida: los tests dicen si la lógica es correcta;
- el alumnado se lleva algo demostrable a las prácticas y a una entrevista.

Lo que se evalúa es **la decisión sostenible bien fundamentada**. El código es la prueba de que se ha entendido el cálculo que hay detrás.

!!! warning "El módulo no es una segunda asignatura de programación"
    Un proyecto que pasa todos los tests pero cuya interpretación está vacía no supera el resultado de aprendizaje. Por eso la rúbrica reserva un punto específico a interpretar el resultado y exige hacerlo con datos y fuentes.

## 4. Resultados de aprendizaje y contenidos

Los seis resultados de aprendizaje (RA) son los del anexo VIII del RD 659/2023. Cada uno se trabaja en una unidad.

| RA | Enunciado | Unidad | Contenidos principales |
|:--:|---|:--:|---|
| **RA1** | Identifica los aspectos ambientales, sociales y de gobernanza (ASG) relativos a la sostenibilidad teniendo en cuenta el concepto de desarrollo sostenible y los marcos internacionales que contribuyen a su consecución | [UD1](ud1/index.md) | Desarrollo sostenible y sus dimensiones · Agenda 2030 y ODS · Acuerdo de París · Pacto Mundial · criterios ASG · indicadores y estándares de reporte · inversión socialmente responsable |
| **RA2** | Caracteriza los retos ambientales y sociales a los que se enfrenta la sociedad, describiendo los impactos sobre las personas y los sectores productivos y proponiendo acciones para minimizarlos | [UD2](ud2/index.md) | Retos ambientales y sociales · límites planetarios · transición justa · impacto del sector digital · mix eléctrico e intensidad de carbono · datos abiertos · acciones de mitigación y adaptación |
| **RA3** | Establece la aplicación de criterios de sostenibilidad en el desempeño profesional y personal, identificando los elementos necesarios | [UD3](ud3/index.md) | ODS aplicados al puesto de trabajo · riesgos y oportunidades · web sostenible: peso, compresión, caché e imágenes · accesibilidad y privacidad · movilidad y hábitos personales |
| **RA4** | Propone productos y servicios responsables teniendo en cuenta los principios de la economía circular | [UD4](ud4/index.md) | Modelo lineal y obsolescencia · economía verde y circular · jerarquía de las R · análisis de ciclo de vida y huella de producto · ecodiseño de hardware y software · modelos de negocio circulares |
| **RA5** | Realiza actividades sostenibles minimizando el impacto de las mismas en el medio ambiente | [UD5](ud5/index.md) | Proceso productivo del software · principios del software verde · medición · modelos de huella (Sustainable Web Design, SCI) · optimización · computación *carbon-aware* |
| **RA6** | Analiza un plan de sostenibilidad de una empresa del sector, identificando sus grupos de interés, los aspectos ASG materiales y justificando acciones para su gestión y medición | [UD6](ud6/index.md) | Fases del plan · grupos de interés y matriz de poder e interés · doble materialidad · priorización · objetivos SMART e indicadores · seguimiento y comunicación |

!!! info "Criterios de evaluación"
    Los criterios de evaluación de cada RA son los literales del anexo VIII. En este repositorio se concretan al final de cada unidad, en el apartado **«Cómo se evalúa esta unidad»**, y se traducen a una rúbrica pública.

## 5. Temporalización y pesos

30 horas repartidas en dos trimestres, a una hora semanal.

| Unidad | RA | Horas | Trimestre | **Peso** |
|:---:|:---:|:---:|:---:|:---:|
| 1 · Sostenibilidad, ODS y criterios ASG | RA1 | 4 | 1.º | **12 %** |
| 2 · Retos ambientales y sociales | RA2 | 4 | 1.º | **15 %** |
| 3 · Sostenibilidad en el trabajo del desarrollador | RA3 | 4 | 1.º | **15 %** |
| Examen práctico del 1.er trimestre | RA1–RA3 | 2 | 1.º | — |
| 4 · Economía circular y ecodiseño | RA4 | 4 | 2.º | **16 %** |
| 5 · Actividades sostenibles y huella del software | RA5 | 5 | 2.º | **22 %** |
| 6 · Plan de sostenibilidad empresarial | RA6 | 5 | 2.º | **20 %** |
| Examen práctico del 2.º trimestre | RA4–RA6 | 2 | 2.º | — |
| | | **30** | | **100 %** |

### Por qué estos pesos

Los pesos **no son iguales**: se han ajustado a la dificultad real de cada resultado de aprendizaje y a las horas que requiere.

| RA | Peso | Justificación |
|:--:|:---:|---|
| **RA1** | 12 % | Es el más conceptual y el de entrada. Los cálculos son sencillos (medias, porcentajes, comparaciones) y el andamiaje del enunciado es alto. Se le da el peso menor para que un arranque dubitativo no hipoteque el módulo. |
| **RA2** | 15 % | Sube la exigencia técnica: agregación con Streams, agrupación por fecha y conversión de unidades, donde se concentran los errores. Conceptualmente sigue siendo abarcable. |
| **RA3** | 15 % | Dificultad media. La lógica es de casos y cabeceras, no compleja, pero exige salir del IDE: medir con las herramientas del navegador y configurar el servidor. |
| **RA4** | 16 % | Aparece el modelado de un ciclo de vida con estados, transiciones y excepciones, más JPA. El razonamiento sobre huella de fabricación frente a huella de uso es exigente. |
| **RA5** | 22 % | **El más difícil y el de más horas.** Integra medición real, dos modelos de estimación, diagnóstico y corrección de un problema de rendimiento (N+1), paginación, DTO y tareas programadas. Además exige comparar un antes y un después de forma honesta. |
| **RA6** | 20 % | Alta carga conceptual (doble materialidad, grupos de interés, indicadores con línea base y meta) y es el RA integrador: recoge resultados de las unidades anteriores y termina en un informe. |

Un módulo de 30 horas no da para más profundidad, y la progresión está pensada para que la exigencia crezca a la vez que la soltura del alumnado.

## 6. Metodología

### 6.1 Principios

- **Aprendizaje basado en proyectos.** Cada RA culmina en un proyecto Spring Boot que resuelve un problema real de sostenibilidad.
- **Aprender haciendo.** La teoría es breve y va seguida de práctica inmediata: retos rápidos entre secciones, actividades con solución desplegable y un banco de preguntas para autoevaluarse.
- **Evaluación transparente.** La rúbrica y el mecanismo de corrección son públicos desde el primer día y son los mismos en las actividades y en el examen.
- **Rigor con los datos.** Toda cifra lleva su fuente y toda estimación se identifica como tal. Es el antídoto contra el greenwashing y una competencia profesional en sí misma.
- **Transferencia.** Las mejoras se aplican sobre el proyecto real de DWES del propio alumnado siempre que es posible.

### 6.2 Estructura de cada unidad

| Fase | Qué ocurre | Tiempo aproximado |
|:---:|---|---|
| **1** | Exposición breve del concepto, con ejemplos de código ejecutables | 25 % |
| **2** | Retos rápidos y actividades con solución desplegable | 25 % |
| **3** | Proyecto de la unidad: el alumnado programa el servicio hasta pasar los tests | 35 % |
| **4** | Laboratorio sobre la aplicación arrancada e interpretación de resultados | 15 % |

### 6.3 Organización del aula

Trabajo **individual** en el proyecto evaluable, para que la calificación refleje el aprendizaje de cada persona. Los laboratorios y los retos admiten trabajo por parejas. Con grupos numerosos, la corrección automática por tests permite dar realimentación detallada sin depender del tiempo de corrección manual.

### 6.4 Uso de la inteligencia artificial

Se permite usar asistentes de IA para comprender conceptos y depurar código, igual que se usa un manual. Con dos límites: **las cifras que proporcione una IA no son una fuente** y deben verificarse en fuentes oficiales, y **la interpretación debe estar escrita por el alumnado**. En la evaluación puede pedirse explicar oralmente cualquier parte del código entregado.

## 7. Evaluación

### 7.1 Qué se evalúa y con qué

| Instrumento | Qué mide | Cuándo |
|---|---|---|
| **Tarea práctica de RA** (proyecto de la unidad) | El RA completo: cálculo correcto, técnica del RA, documentación e interpretación | Al terminar cada unidad |
| **Examen práctico de trimestre** | Los mismos RA con una batería de tests equivalente, en condiciones controladas | Una sesión de 2 h por trimestre |
| **Banco de preguntas** | Comprensión conceptual: preguntas tipo test y cortas | Durante las unidades y en el examen |
| **Laboratorio entregable** | Aplicación a un caso real elegido por el alumnado | Dentro de cada unidad |

### 7.2 Rúbrica de cada RA

Es la misma en los seis RA y se aplica igual en las tareas y en los exámenes. Sobre **10 puntos**:

| # | Criterio | Cómo se mide | Puntos |
|:---:|---|---|:---:|
| **1** | **Funcionamiento** | `(tests superados ÷ total) × 7`. Proporcional | 0 – 7 |
| **2** | **Criterio técnico del RA** | Todo o nada. Se comprueba leyendo el código | 0 **o** 1 |
| **3** | **Documentación** | Todo o nada. Al menos 2 Javadoc o comentarios propios con contenido real | 0 **o** 1 |
| **4** | **Interpretación** | Todo o nada. `INTERPRETACION.md` con Resultado, Impacto y Acción propuesta, ≥ 40 palabras cada uno, usando los números del propio programa | 0 **o** 1 |

**Criterio 2 por RA:** RA1, `EnumMap` · RA2, API de Streams · RA3, `server.compression.enabled=true` · RA4, `IllegalStateException` · RA5, `@Scheduled` · RA6, `Comparator`.

### 7.3 Calificación del módulo

1. Cada RA obtiene una nota de 0 a 10 con la rúbrica anterior.
2. **Un RA se supera con 5**.
3. **Para superar el módulo hay que tener los seis RA con nota igual o superior a 5. No hay compensación** entre resultados de aprendizaje: un RA suspenso no se salva con la nota alta de otro.
4. La calificación final es la **media ponderada** de los seis RA con los pesos de la sección 5, redondeada al entero más próximo (a partir de 0,5 se sube).
5. Mientras quede algún RA sin superar, la calificación del módulo **no puede ser superior a 4**.

!!! reto "Ejemplo de cálculo"
    RA1 = 8, RA2 = 6, RA3 = 7, RA4 = 9, RA5 = 5, RA6 = 7.
    `8×0,12 + 6×0,15 + 7×0,15 + 9×0,16 + 5×0,22 + 7×0,20 = 0,96 + 0,90 + 1,05 + 1,44 + 1,10 + 1,40 =` **6,85 → 7**.
    Si el RA5 hubiera sido un 4, el módulo quedaría suspenso con un 4 pese a que la media ponderada daría 6,63.

### 7.4 Evaluaciones parciales, ordinaria y extraordinaria

**Parciales.** Al final de cada trimestre se celebra una sesión de **examen práctico de 2 horas** con una tarea por cada RA del trimestre, calificada con la misma rúbrica. La nota de cada RA es la de esta prueba, salvo que la tarea de la unidad haya sido mejor y se haya realizado en las condiciones previstas; en ese caso se toma la más favorable para el alumnado.

**Convocatoria ordinaria.** Recoge la calificación final del módulo. Quien llegue con algún RA no superado se examina **solo de los RA pendientes**, con una prueba equivalente. Los RA ya superados se conservan.

**Convocatoria extraordinaria.** Misma lógica: prueba práctica de los **RA pendientes** exclusivamente, con idéntica estructura y rúbrica. No hay examen global de todo el módulo para quien solo debe un RA.

**Recuperación durante el curso.** Un RA suspenso puede recuperarse entregando de nuevo la tarea corregida antes de la sesión de evaluación siguiente. La nota máxima de una recuperación de este tipo es la que corresponda por rúbrica, sin penalización, porque lo que importa es que el resultado de aprendizaje acabe alcanzado.

**Pérdida del derecho a la evaluación continua.** Se aplica según la normativa de la Comunidad de Madrid y las normas de organización y funcionamiento del centro *(concretar aquí el porcentaje de faltas y el procedimiento vigentes)*. En ese caso, la evaluación se realiza mediante una prueba práctica que abarca los seis RA.

### 7.5 Cómo se corrige exactamente

El alumnado entrega el fichero del servicio, el fichero del criterio 2 cuando es distinto y su `INTERPRETACION.md`. El profesorado los coloca en un proyecto con la batería de tests y ejecuta la corrección con un script, de modo que **la nota es la misma la corrija quien la corrija**. Cada persona recibe un informe con la salida real de los tests y el desglose de los cuatro criterios.

## 8. Materiales y equipamiento

### 8.1 Del aula

- Aula de informática con un **puesto por alumno o alumna** y conexión a internet.
- Proyector o pantalla para las demostraciones.
- Permisos para instalar o ejecutar un JDK y un IDE.

### 8.2 Software (todo gratuito)

| Herramienta | Uso | Notas |
|---|---|---|
| **JDK 21** o superior | Compilar y ejecutar | Eclipse Temurin u otra distribución |
| **IntelliJ IDEA Community** o **VS Code** con *Extension Pack for Java* | Desarrollo | El mismo que en DWES |
| **Maven Wrapper** | Construcción | Incluido en cada proyecto: no hay que instalar Maven |
| **Git** | Control de versiones | Para clonar el repositorio y entregar |
| **Navegador con herramientas de desarrollo** | Medir peso, compresión y caché | Pestaña Red |
| **`curl`** | Probar la API y comparar cabeceras | Alternativa: cualquier cliente HTTP |
| **H2** | Base de datos en memoria | Llega como dependencia, no se instala |
| **MkDocs + Material** | Publicar y consultar estos materiales | Solo para el profesorado |

### 8.3 Recursos y fuentes de datos

- Este repositorio: unidades, actividades, banco de preguntas y seis proyectos con tests.
- **API REData de Red Eléctrica** para datos reales del sistema eléctrico español.
- **MITECO** (factores de emisión y registro de huella), **INE** y **Eurostat**.
- Informes de sostenibilidad y estados de información no financiera de empresas del sector.
- **Green Software Foundation**, **Sustainable Web Design** y **W3C** (directrices de sostenibilidad web y WCAG).
- Fichas de huella de carbono de producto (PCF) publicadas por fabricantes de hardware.

!!! warning "Conexión a internet"
    Solo es imprescindible la primera vez, para descargar dependencias, y en los apartados que consultan APIs externas. **Todos los proyectos incluyen datos de ejemplo** para poder trabajar sin conexión, y la documentación puede distribuirse en modo offline.

## 9. Atención a la diversidad y accesibilidad

- Los proyectos están **graduados**: los tests se pasan de uno en uno, lo que permite avanzar a distintos ritmos con realimentación inmediata.
- Cada unidad incluye **retos de ampliación** para quien termine antes, y solución desplegable en todas las actividades para quien necesite andamiaje.
- Los materiales se publican en **HTML accesible** (Material for MkDocs), se leen bien en móvil, funcionan sin conexión y admiten impresión o guardado en PDF.
- Las adaptaciones de acceso (tiempo adicional, ampliación tipográfica, uso de productos de apoyo) se aplican según el informe del departamento de orientación. Los resultados de aprendizaje y los criterios de evaluación **no se modifican**.
- La accesibilidad no es solo una medida organizativa: es **contenido evaluable** del RA3, de modo que el propio alumnado la trabaja desde el punto de vista profesional.

## 10. Relación con otros módulos y transversalidad

| Módulo | Conexión |
|---|---|
| **Desarrollo Web en Entorno Servidor (DWES)** | Aporta Java y Spring Boot. El 1708 los usa y devuelve criterios de eficiencia y accesibilidad aplicables al proyecto de DWES |
| **Despliegue de Aplicaciones Web** | Compresión, caché, dimensionado de servidores y consumo en la nube |
| **Bases de Datos / Acceso a Datos** | El problema N+1, la paginación y el coste de las consultas |
| **Diseño de Interfaces Web** | Peso de las páginas, formatos de imagen y accesibilidad |
| **Itinerario Personal para la Empleabilidad** | Criterios ASG y sostenibilidad como factor de empleabilidad en el sector |
| **Proyecto y FCT** | Un panel de huella o un inventario circular son puntos de partida excelentes |

## 11. Seguimiento de la programación

Al final de cada trimestre conviene revisar y dejar constancia de: porcentaje de alumnado que supera cada RA, RA con más dificultad, ajuste real de la temporalización, incidencias técnicas y propuestas de mejora. Los pesos de la sección 5 son una hipótesis razonada sobre la dificultad: si los datos del curso muestran que un RA resulta más o menos exigente de lo previsto, deben ajustarse en la revisión anual.
