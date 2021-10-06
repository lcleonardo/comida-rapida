package com.ceiba.curso.comando.fabrica;

import com.ceiba.curso.comando.ComandoCurso;
import com.ceiba.curso.modelo.entidad.Curso;
import org.springframework.stereotype.Component;

@Component
public class FabricaCurso {

    public Curso crear(ComandoCurso comando ) {
        return new Curso(comando.getNombre(), comando.getDuracion());
    }

}
