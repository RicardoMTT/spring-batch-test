package com.example.prueba_batch.batch;

import com.example.prueba_batch.entities.Empleado;
import com.example.prueba_batch.repositories.EmpleadoRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoItemWriter implements ItemWriter<Empleado> {
    @Autowired
    private EmpleadoRepository repository;

    @Override
    public void write(Chunk<? extends Empleado> chunk) throws Exception {
        repository.saveAll(chunk);
    }
}
