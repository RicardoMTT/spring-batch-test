package com.example.prueba_batch.batch;


import com.example.prueba_batch.entities.Empleado;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

// Tambien se podia manejar como un bean en BatchConfig
// Obtiene los empleados desde un csv
//Indica que esta clase leerá un archivo plano (CSV) y convertirá cada fila
// en una instancia de Empleado

@Component
@StepScope  // Esta anotación es crucial para acceder a los jobParameters
public class EmpleadoItemReader extends FlatFileItemReader<Empleado> {

    // Constructor que recibe el parámetro filePath del job
    public EmpleadoItemReader(@Value("#{jobParameters['filePath']}") String filePath) {
        // Configura el recurso usando la ruta de archivo pasada como parámetro
        if (filePath != null) {
            setResource(new FileSystemResource(filePath));
        } else {
            // Proporciona un recurso por defecto en caso de que filePath sea null
            setResource(new ByteArrayResource(new byte[0]));
        }

        setLinesToSkip(1); // Omitir encabezado

        // Configura el lineMapper
        setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("id", "nombre", "salario");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(Empleado.class);
            }});
        }});
    }
}