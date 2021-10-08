package com.ceiba.curso;

import com.ceiba.core.BasePrueba;
import com.ceiba.curso.modelo.entidad.Curso;
import com.ceiba.curso.puerto.repositorio.RepositorioCurso;
import com.ceiba.curso.servicio.ServicioCrearCurso;
import com.ceiba.curso.testdatabuilder.CursoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServicioCrearCursoTest {

    private final static String EL_CURSO_YA_EXISTE_EN_EL_SISTEMA = "El curso ya existe en el sistema.";


    @Mock
    private RepositorioCurso repositorioCurso;

    @InjectMocks
    private ServicioCrearCurso servicioCrearCurso;

    @Test
    public void deberiaCrearUnCurso() {
        //Arr
        Curso curso = new CursoTestDataBuilder()
                .conNombre("Curso1")
                .conDuracion(60.0)
                .build();
        //Act
        when(this.repositorioCurso.existePorNombre(anyString())).thenReturn(false);
        //Assert
        assertEquals("Curso1", curso.getNombre());
        assertEquals(60.0, curso.getDuracion(), 0.0);
    }

    @Test
    public void deberiaLanzarExcepcionAlCrearUnCursoConElMismoNombre() {
        //Arr
        Curso curso = new CursoTestDataBuilder()
                .conNombre("Curso1")
                .conDuracion(60.0)
                .build();
        //Act
        when(this.repositorioCurso.existePorNombre(anyString())).thenReturn(true);
        //Assert
        BasePrueba.assertThrows(() -> this.servicioCrearCurso.ejecutar(curso),
                ExcepcionDuplicidad.class, EL_CURSO_YA_EXISTE_EN_EL_SISTEMA);
    }

}















