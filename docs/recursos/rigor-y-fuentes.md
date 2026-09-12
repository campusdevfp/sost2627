# Rigor, datos y greenwashing

!!! warning "Léelo antes de publicar cualquier cifra"
    En sostenibilidad, un dato mal usado hace tanto daño como un mal código: lleva a decisiones equivocadas y engaña a quien confía en ti. Esta página define cómo se trabaja con datos en este módulo. No es un formalismo: es lo que distingue un análisis profesional de un folleto publicitario.

## Qué es el greenwashing

**Greenwashing** (o *ecopostureo*) es presentar un producto, un servicio o una empresa como más respetuosos con el medio ambiente de lo que realmente son. Formas típicas:

| Forma | Ejemplo en el sector digital |
|---|---|
| **Afirmación genérica** | «Hosting ecológico» sin decir qué lo hace ecológico |
| **Selección interesada** | Presumir de oficinas con placas solares y callar el consumo del centro de datos |
| **Compensar en lugar de reducir** | «Somos neutros en carbono» solo porque se compran créditos de compensación |
| **Datos sin fuente ni método** | «Nuestra app ahorra un 40 % de energía» sin decir frente a qué ni cómo se midió |
| **Etiquetas propias** | Un sello «verde» inventado por la propia empresa |

!!! danger "No es solo una cuestión ética"
    La **Directiva (UE) 2024/825**, sobre el empoderamiento de los consumidores para la transición ecológica, prohíbe en la UE las afirmaciones ambientales genéricas que no se puedan demostrar y los sellos de sostenibilidad que no se basen en sistemas de certificación. Comprueba en cada momento cómo está su aplicación en España.

## Reglas de este módulo

1. **Cada cifra con su fuente.** Si un número no es tuyo (calculado por tu programa), indica de dónde sale: organismo, documento y año.
2. **Fuentes oficiales primero.** Organismos públicos, estadísticas oficiales y normas técnicas antes que blogs o notas de prensa.
3. **Las aproximaciones se dicen.** Los factores de emisión de nuestros proyectos son **didácticos y aproximados**; está indicado en el código y debe estar indicado en tu interpretación.
4. **Compara siempre con algo.** «300 kg de CO₂» no dice nada; «300 kg, lo mismo que la electricidad de X meses de un hogar» o «un 40 % menos que la versión anterior» sí.
5. **Explica el método.** Qué mediste, cómo y con qué supuestos. Otra persona debería poder repetirlo.
6. **Datos ficticios o abiertos.** Las empresas de los proyectos son inventadas. Si analizas una real, usa solo información que ella misma haya publicado.

## Dónde buscar datos fiables

| Qué necesitas | Dónde |
|---|---|
| Generación eléctrica e intensidad de la red en España | **Red Eléctrica (REData)**: ree.es |
| Factores de emisión para calcular huellas | **MITECO**, Registro de huella de carbono |
| Estadísticas de España y de la UE | **INE** y **Eurostat** |
| Objetivos y metas de los ODS | **Naciones Unidas** y el portal de la Agenda 2030 del Gobierno de España |
| Informes de sostenibilidad de empresas | Webs corporativas (estado de información no financiera o informe de sostenibilidad) |
| Huella del software y la web | **Green Software Foundation**, **Sustainable Web Design**, **W3C** (Directrices de Sostenibilidad Web) |
| Residuos electrónicos | **Global E-waste Monitor** (UNITAR / UIT) |
| Energía y centros de datos | **Agencia Internacional de la Energía (IEA)** |

## Cómo citar en tu `INTERPRETACION.md`

> Según Red Eléctrica (REData, estructura de generación, 2–8 de marzo de 2026), la renovable supuso un XX,X % de la generación. Con los factores didácticos del proyecto, la intensidad media fue de YYY,Y g CO₂/kWh.

*Es un ejemplo de formato: sustituye XX,X e YYY,Y por las cifras que obtengas tú.* Basta con **organismo, conjunto de datos o documento, y fecha**. Si usaste un enlace, añádelo.

## Sobre el uso de la IA

Puedes usar asistentes de IA para entender conceptos o depurar código, igual que usarías un libro. Pero **las cifras que te den no son una fuente**: pueden estar desactualizadas o ser inventadas. Todo dato que acabe en tu trabajo debe estar comprobado en una fuente oficial, y tu interpretación debe estar escrita por ti.
