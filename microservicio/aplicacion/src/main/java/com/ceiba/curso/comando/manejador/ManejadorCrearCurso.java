package com.ceiba.curso.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.curso.comando.ComandoCurso;
import com.ceiba.curso.comando.fabrica.FabricaCurso;
import com.ceiba.curso.modelo.Curso;
import com.ceiba.curso.servicio.ServicioCrearCurso;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearCurso implements ManejadorComandoRespuesta<ComandoCurso, ComandoRespuesta<Long>> {

    private final FabricaCurso fabricaCurso;
    private final ServicioCrearCurso servicioCrearCurso;

    public ManejadorCrearCurso(FabricaCurso fabricaCurso, ServicioCrearCurso servicioCrearCurso) {
        this.fabricaCurso = fabricaCurso;
        this.servicioCrearCurso = servicioCrearCurso;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoCurso comandoCurso) {
        Curso curso = new Curso(comandoCurso.getNombre(), comandoCurso.getDuracion());
        return new ComandoRespuesta<>(this.servicioCrearCurso.ejecutar(curso));
    }
}
