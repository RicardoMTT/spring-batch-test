package com.example.prueba_batch.services;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class BatchService {


    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job empleadoJob;

    public String ejecutarBatchConArchivo(MultipartFile file) throws Exception {
        // Guardar archivo temporalmente
        String filePath = "uploads/" + file.getOriginalFilename();
        File destFile = new File(filePath);
        destFile.getParentFile().mkdirs(); // Crear carpeta si no existe
        file.transferTo(destFile);

        // Ejecutar el job batch con el archivo
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("filePath", filePath) // Pasar la ruta del archivo
                .addLong("time", System.currentTimeMillis()) // Asegurar ejecución única
                .toJobParameters();

        jobLauncher.run(empleadoJob, jobParameters);
        return "Proceso batch iniciado con el archivo: " + file.getOriginalFilename();
    }

}
