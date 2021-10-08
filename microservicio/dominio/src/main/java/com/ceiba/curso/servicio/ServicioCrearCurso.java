package com.ceiba.curso.servicio;

import com.ceiba.curso.modelo.entidad.Curso;
import com.ceiba.curso.puerto.repositorio.RepositorioCurso;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearCurso {

    private final static String EL_CURSO_YA_EXISTE_EN_EL_SISTEMA = "El curso ya existe en el sistema.";

    private RepositorioCurso repositorioCurso;

    public ServicioCrearCurso(RepositorioCurso repositorioCurso) {
        this.repositorioCurso = repositorioCurso;
    }


    public Long ejecutar(Curso curso) {
        validarSiExisteUnCursoConElMismoNombre(curso.getNombre());
        return this.repositorioCurso.crear(curso);
    }

    private void validarSiExisteUnCursoConElMismoNombre(String nombre) {
        Boolean respuesta = this.repositorioCurso.existePorNombre(nombre);
        if (respuesta) {
            throw new ExcepcionDuplicidad(EL_CURSO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }

}
