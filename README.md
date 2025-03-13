## Spring Batch CSV Processor

Este proyecto implementa un procesador batch en Spring Boot para procesar archivos CSV de empleados y subirlo a una base de datos. La aplicación proporciona un endpoint REST para cargar archivos CSV y procesarlos mediante Spring Batch.

### Características

Carga de archivos CSV a través de una API REST
Procesamiento asíncrono de datos con Spring Batch
Base de datos en memoria H2 para almacenamiento temporal
Configuración de jobs y steps con Spring Batch
Procesamiento de registros de empleados (id, nombre, salario)

### Requisitos previos

Java 17 o superior
Maven 3.6 o superior

### Solución de problemas comunes

Error: "Table 'BATCH_JOB_INSTANCE' not found"
Asegúrese de que la propiedad spring.batch.jdbc.initialize-schema=always esté correctamente configurada para permitir la creación automática de las tablas necesarias de Spring Batch.
Error: "Input resource must exist (reader is in 'strict' mode)"
Este error ocurre cuando el reader no puede encontrar el archivo CSV. Verifique que:

El archivo se está subiendo correctamente
La ruta del archivo temporal se está pasando correctamente como parámetro del job
El reader está configurado con @StepScope para acceder a los parámetros del job
