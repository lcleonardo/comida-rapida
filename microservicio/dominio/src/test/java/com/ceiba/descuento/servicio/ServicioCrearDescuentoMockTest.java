package com.ceiba.descuento.servicio;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.ceiba.core.BasePrueba;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.ServicioCrearDescuento;
import com.ceiba.descuento.servicio.testdatabuilder.DescuentoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServicioCrearDescuentoMockTest {

    private RepositorioDescuento repositorioDescuento;
    private ServicioCrearDescuento servicioCrearDescuento;

    @Before
    public void preparar() {
        // Arrange
        this.repositorioDescuento = mock(RepositorioDescuento.class);
        this.servicioCrearDescuento = new ServicioCrearDescuento(this.repositorioDescuento);
    }

    @Test
    public void deberiaCrearUnDescuento() {
        // Arrange
        Descuento descuento = new DescuentoTestDataBuilder()
                .conFecha(LocalDate.now().toString())
                .conPorcentaje(10.0)
                .build();
        // Act
        // Assert
        Long id = servicioCrearDescuento.ejecutar(descuento);
        assertNotNull(id);
    }

    @Test
    public void deberiaValidarQueYAExisteUnDescuentoAsignadoALaFecha() {
        // Arrange
        Descuento descuento = new DescuentoTestDataBuilder()
                .conFecha(LocalDate.now().toString())
                .conPorcentaje(10.0).build();
        // Act
        when(this.repositorioDescuento
                .obtenerPorcentajePorFecha(LocalDate.now()))
                .thenReturn(10.0);
        // Assert
        BasePrueba.assertThrows(() -> this.servicioCrearDescuento
                        .ejecutar(descuento), ExcepcionDuplicidad.class,
                "Ya existe un descuento asignado a la fecha.");
    }

}
