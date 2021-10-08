package com.ceiba.curso;

import com.ceiba.curso.modelo.entidad.Curso;
import com.ceiba.curso.testdatabuilder.CursoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CursoUnitTest {

    private static final String EL_NOMBRE_DEL_CURSO_ES_OBLIGATORIO = "El nombre del curso es obligatorio.";
    private static final String EL_NOMBRE_DEL_CURSO_NO_PUEDE_TENER_MAS_DE_255_CARACTERES = "El nombre del curso no puede tener más de 255 caracteres.";
    private static final String LA_DURACION_DEL_CURSO_ES_OBLIGATORIA = "La duración del curso es obligatorio.";

    @Test
    public void deberiaCrearUnaInstanciaDeCursoCorrectamente() {
        Curso curso = new CursoTestDataBuilder()
                .conNombre("Curso1")
                .conDuracion(60.0)
                .build();
        Assert.assertEquals("Curso1", curso.getNombre());
        Assert.assertEquals(60.0, curso.getDuracion(), 0.0);
    }

    @Test
    public void elNombreDelCursoNoPuedeSerNull() {
        CursoTestDataBuilder cursoTestDataBuilder = new CursoTestDataBuilder()
                .conNombre(null)
                .conDuracion(60.0);
        assertThrows(ExcepcionValorObligatorio.class, () -> cursoTestDataBuilder.build(), EL_NOMBRE_DEL_CURSO_ES_OBLIGATORIO);
    }

    @Test
    public void elNombreDelCursoNoPuedeEstarVacio() {
        CursoTestDataBuilder cursoTestDataBuilder = new CursoTestDataBuilder()
                .conNombre("")
                .conDuracion(60.0);
        assertThrows(ExcepcionValorObligatorio.class, () -> cursoTestDataBuilder.build(), EL_NOMBRE_DEL_CURSO_ES_OBLIGATORIO);
    }

    @Test
    public void elNombreDelCursoNoPuedeTenerMasDe255Caracteres() {
        CursoTestDataBuilder cursoTestDataBuilder = new CursoTestDataBuilder()
                .conNombre("llLorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
                        "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. " +
                        "Donec quam felis, ultricies nec, pellentesque eu, pretium quis,")
                .conDuracion(60.0);
        assertThrows(ExcepcionLongitudValor.class, () -> cursoTestDataBuilder.build(), EL_NOMBRE_DEL_CURSO_NO_PUEDE_TENER_MAS_DE_255_CARACTERES);
    }


    @Test
    public void laDuracionDelCursoNoPuedeSerNull() {
        CursoTestDataBuilder cursoTestDataBuilder = new CursoTestDataBuilder()
                .conNombre("Curso1")
                .conDuracion(null);
        assertThrows(ExcepcionValorObligatorio.class, () -> cursoTestDataBuilder.build(), LA_DURACION_DEL_CURSO_ES_OBLIGATORIA);
    }

    @Test
    public void laDuracionDelCursoNoPuedeSerMenorOIgualACero() {
        CursoTestDataBuilder cursoTestDataBuilder = new CursoTestDataBuilder()
                .conNombre("Curso1")
                .conDuracion(0.0);
        assertThrows(ExcepcionValorInvalido.class, () -> cursoTestDataBuilder.build(), LA_DURACION_DEL_CURSO_ES_OBLIGATORIA);
    }

}













