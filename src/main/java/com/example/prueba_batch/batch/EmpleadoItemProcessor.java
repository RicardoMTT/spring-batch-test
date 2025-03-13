package com.example.prueba_batch.batch;
import com.example.prueba_batch.entities.Empleado;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

//Se encarga de modificar los datos de los empleados antes de
//guardarlos en la base de datos.
// <Empleado, Empleado> recibe un Empleado y devuelve un Empleado
@Component
public class EmpleadoItemProcessor  implements ItemProcessor<Empleado, Empleado> {
    @Override
    public Empleado process(Empleado empleado) {
        empleado.setSalario(empleado.getSalario() * 1.1); // Aumentar salario 10%
        return empleado;
    }
}
