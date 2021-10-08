package com.ceiba.curso.modelo.entidad;

import lombok.Getter;

@Getter
public class Curso {

    private String nombre;
    private Double duracion;

    public Curso(String nombre, Double duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }
}