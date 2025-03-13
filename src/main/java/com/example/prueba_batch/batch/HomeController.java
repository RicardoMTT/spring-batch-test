package com.example.prueba_batch.batch;

import com.example.prueba_batch.services.BatchService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/batch")
public class HomeController {


    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job empleadoJob;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        File tempFile = null;
        try {
            tempFile = File.createTempFile("employees-upload-", ".csv");
            System.out.println("Archivo temporal creado en: " + tempFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Transferir el contenido del MultipartFile al archivo temporal
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
            System.out.println("Contenido del archivo escrito correctamente. Tamaño: " + file.getSize() + " bytes");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Obtener la ruta absoluta del archivo temporal
        String filePath = tempFile.getAbsolutePath();
        System.out.println("Usando filePath: " + filePath);

        // Crear los parámetros del job incluyendo la ruta del archivo
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("filePath", filePath)
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        // Lanzar el job
        try {
            JobExecution jobExecution = jobLauncher.run(empleadoJob, jobParameters);
            System.out.println("Job iniciado con ID: " + jobExecution.getId());
            return "Proceso batch iniciado con ID: " + jobExecution.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al iniciar el proceso batch: " + e.getMessage();
        } finally {
            // Opcional: programar la eliminación del archivo temporal
            tempFile.deleteOnExit();
        }

    }

}
