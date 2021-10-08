package com.ceiba.curso.modelo.entidad;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Curso {

    private static final String EL_NOMBRE_DEL_CURSO_ES_OBLIGATORIO = "El nombre del curso es obligatorio.";
    private static final String EL_NOMBRE_DEL_CURSO_NO_PUEDE_TENER_MAS_DE_255_CARACTERES = "El nombre del curso no puede tener más de 255 caracteres.";
    private static final String LA_DURACION__DEL_CURSO_ES_OBLIGATORIA = "La duración del curso es obligatorio.";


    private String nombre;
    private Double duracion;

    public Curso(String nombre, Double duracion) {

        validarObligatorio(nombre, EL_NOMBRE_DEL_CURSO_ES_OBLIGATORIO);
        validarNoVacio(nombre, EL_NOMBRE_DEL_CURSO_ES_OBLIGATORIO);
        validarLongitudMaxima(nombre, 255, EL_NOMBRE_DEL_CURSO_NO_PUEDE_TENER_MAS_DE_255_CARACTERES);

        validarObligatorio(duracion, LA_DURACION__DEL_CURSO_ES_OBLIGATORIA);
        validarPositivo(duracion, LA_DURACION__DEL_CURSO_ES_OBLIGATORIA);

        this.nombre = nombre;
        this.duracion = duracion;
    }
}
