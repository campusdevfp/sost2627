# Retos de programación

Problemas sueltos para practicar por tu cuenta, más abiertos que las actividades de las unidades. **No cuentan para la nota**: están para que programes cosas útiles y para que te lleves ideas al proyecto final de ciclo o a las prácticas en empresa.

Cada reto indica la unidad en la que tienes lo necesario para abordarlo. Si te lanzas antes, tendrás que buscarte la vida, que también vale.

## Nivel 1 · Para empezar

### R1 · Calculadora de huella de una página
*A partir de la UD3.*
Un programa de consola que reciba el peso de una página en kB y el número de visitas mensuales, y devuelva los kg de CO₂ al año y su equivalente en algo comprensible (kilómetros en coche, por ejemplo). Usa el modelo de 0,81 kWh por GB de la UD5 y **cita la fuente** de la equivalencia que elijas.

### R2 · ¿Cuánto pesa mi web de verdad?
*A partir de la UD3.*
Pásale a tu programa el listado de recursos que copias de la pestaña Red y que te devuelva: peso total, los tres recursos más pesados, el reparto por tipo y cuánto ahorrarías comprimiendo todo lo comprimible.

### R3 · Contador de ODS de un texto
*A partir de la UD1.*
Lee un fichero de texto (por ejemplo, la memoria de sostenibilidad de una empresa en `.txt`) y cuenta cuántas veces se menciona cada ODS. Después pregúntate: ¿mencionar mucho un ODS significa trabajarlo?

## Nivel 2 · Con datos reales

### R4 · Semáforo de la red eléctrica
*A partir de la UD2.*
Un programa que consulte la API de Red Eléctrica y diga en una línea si **ahora mismo** es buen momento para lanzar un proceso pesado. Añade un modo `--semana` que muestre una tabla de los últimos siete días.

### R5 · Planificador de tareas carbon-aware
*A partir de la UD5.*
Dada la previsión horaria de intensidad y una lista de tareas con su consumo y su hora límite, decide a qué hora ejecutar cada una para minimizar las emisiones totales. Empieza con una estrategia sencilla (la hora más limpia disponible antes del límite) y compárala con ejecutarlas todas a medianoche.

### R6 · ¿Mi hosting usa energía renovable?
*A partir de la UD3.*
Investiga qué información publican los proveedores de alojamiento sobre su electricidad (garantías de origen, PUE, informes) y escribe una ficha comparativa de tres de ellos. El reto de programación: un pequeño servicio que, dado un dominio, te devuelva su proveedor y lo que hayas registrado sobre él. El reto de verdad: distinguir un dato verificable de un eslogan.

### R7 · Cazador de N+1
*A partir de la UD5.*
Un filtro o *listener* que registre, para cada petición, el número de consultas SQL y el de elementos devueltos, y que escriba un aviso en el log cuando la relación sea sospechosa. Pruébalo contra tu proyecto de DWES: casi seguro que encuentras alguno.

## Nivel 3 · Para lucirse

### R8 · Panel de sostenibilidad de tu proyecto de DWES
*A partir de la UD5.*
Añade a tu aplicación de DWES una página `/sostenibilidad` que muestre en tiempo real: peticiones servidas, bytes enviados, gramos de CO₂ estimados, etiqueta energética y la intensidad de red usada en el cálculo. Con un aviso visible de que son estimaciones y con el método explicado.

### R9 · Inventario circular con QR
*A partir de la UD4.*
Amplía ReUsa para que cada equipo tenga un código QR que abra su ficha: estado, años de vida, huella anualizada e historial de cambios. Genera los QR desde el servidor y una hoja imprimible con todas las etiquetas.

### R10 · Comparador de memorias de sostenibilidad
*A partir de la UD6.*
Una aplicación que cargue los indicadores de dos o tres empresas del sector y las compare por dimensión, con indicadores de intensidad para que la comparación sea justa. Lo difícil no es programarlo: es decidir qué indicadores son realmente comparables entre empresas que miden de formas distintas.

### R11 · Presupuesto de peso en la integración continua
*A partir de la UD3.*
Un script que se ejecute en un *pipeline* (GitHub Actions, por ejemplo) y falle si la página principal supera un tope de peso. Así el proyecto no engorda sin que nadie se entere.

!!! tip "Si haces alguno, enséñalo"
    Estos retos dan material excelente para el proyecto final del ciclo y para enseñar en una entrevista. Un panel de huella con su método explicado dice más de ti que cualquier certificado.
