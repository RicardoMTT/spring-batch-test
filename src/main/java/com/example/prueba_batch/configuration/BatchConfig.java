package com.example.prueba_batch.configuration;

import com.example.prueba_batch.entities.Empleado;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@EnableBatchProcessing
public class BatchConfig {

    /*
    @Bean
    @StepScope
    public FlatFileItemReader<Empleado> empleadoItemReader(
            @Value("#{jobParameters['filePath']}") String filePath) {

        FlatFileItemReader<Empleado> reader = new FlatFileItemReader<>();

        // Usa la ruta del archivo que viene del parámetro
        reader.setResource(new FileSystemResource(filePath));
        reader.setLinesToSkip(1);

        DefaultLineMapper<Empleado> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "nombre", "salario");

        BeanWrapperFieldSetMapper<Empleado> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Empleado.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);

        return reader;
    }
    */

    @Bean
    public Job empleadoJob(JobRepository jobRepository, Step empleadoStep) {
        return new JobBuilder("empleadoJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(empleadoStep)
                .build();
    }

    @Bean
    public Step empleadoStep(JobRepository jobRepository,
                             PlatformTransactionManager transactionManager,
                             ItemReader<Empleado> reader,
                             ItemProcessor<Empleado, Empleado> processor,
                             ItemWriter<Empleado> writer) {
        return new StepBuilder("empleadoStep", jobRepository)
                .<Empleado, Empleado>chunk(2, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}