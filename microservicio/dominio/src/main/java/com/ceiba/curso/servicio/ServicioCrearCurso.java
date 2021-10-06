package com.ceiba.curso.servicio;

import com.ceiba.curso.modelo.Curso;
import com.ceiba.curso.puerto.repositorio.RepositorioCurso;

public class ServicioCrearCurso {

    private RepositorioCurso repositorioCurso;

    public ServicioCrearCurso(RepositorioCurso repositorioCurso) {
        this.repositorioCurso = repositorioCurso;
    }

    //TODO: Se debe validar que no exista un curso
    public Long ejecutar(Curso curso) {
        return this.repositorioCurso.crear(curso);
    }
}
