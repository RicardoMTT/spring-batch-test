package com.example.prueba_batch.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Al implementar CommandLineRunner se ejecutara de forma inmediata
// luego que se levante la aplicacion.
/*
@Component
public class JobRunner implements CommandLineRunner {

    @Autowired
    private JobLauncher jobLauncher;

    // empleadoJob es el job batch que procesa los empleados
    @Autowired
    private Job empleadoJob;

    @Override
    public void run(String... args) throws Exception {
        jobLauncher.run(empleadoJob, new org.springframework.batch.core.JobParameters());
    }

}
*/