package com.ceiba.curso.servicio.testdatabuilder;

import com.ceiba.curso.comando.ComandoCurso;

public class ComandoCursoTestDataBuilder {

    private String nombre;
    private Double duracion;

    public ComandoCursoTestDataBuilder() {
        this.nombre = "";
        this.duracion = 0.0;
    }

    public ComandoCursoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ComandoCursoTestDataBuilder conDuracion(Double duracion) {
        this.duracion = duracion;
        return this;
    }


    public ComandoCurso build() {
        return new ComandoCurso(this.nombre, this.duracion);
    }
}
