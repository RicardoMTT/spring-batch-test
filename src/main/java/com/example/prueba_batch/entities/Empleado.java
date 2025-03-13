package com.example.prueba_batch.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Empleado {

    @Id
    private Long id;

    private String nombre;

    private Double salario;

    public Empleado() {

    }

    public Empleado(Long id, String nombre, Double salario) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
