package com.ceiba.curso.testdatabuilder;

import com.ceiba.curso.modelo.entidad.Curso;

public class CursoTestDataBuilder {

    private String nombre;
    private Double duracion;

    public CursoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public CursoTestDataBuilder conDuracion(Double duracion) {
        this.duracion = duracion;
        return this;
    }

    public Curso build() {
        return new Curso(this.nombre, this.duracion);
    }
}
