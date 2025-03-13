package com.example.prueba_batch.repositories;

import com.example.prueba_batch.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

// Usado para interactuar con la base de datos
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
