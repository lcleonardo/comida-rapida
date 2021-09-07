package com.ceiba.descuento.servicio;


import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionAccionNoPermitida;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.ServicioEliminarDescuento;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServicioEliminarDescuentoMockTest {

    private static final Long ID = 1L;

    private RepositorioDescuento repositorioDescuento;
    private ServicioEliminarDescuento servicioEliminarDescuento;

    @Before
    public void preparar() {
        // 1. Arrange
        this.repositorioDescuento = mock(RepositorioDescuento.class);
        this.servicioEliminarDescuento = new ServicioEliminarDescuento(this.repositorioDescuento);
    }

    @Test
    public void deberiaEliminarUnDescuento() {
        //Act Assert
        Assertions.assertDoesNotThrow(
                () -> this.servicioEliminarDescuento.ejecutar(ID));
    }

    @Test
    public void deberiaLanzarExcepcionAlEliminarUnDescuentoPorqueTienePedidosAsignados() {
        //Act
        when(repositorioDescuento.existenPedidosAsignadosAUnDescuento(anyLong())).thenReturn(true);
        //Assert
        BasePrueba.assertThrows(() -> this.servicioEliminarDescuento.ejecutar(ID)
                , ExcepcionAccionNoPermitida.class,
                "No se puede eliminar el descuento porque tiene pedidos asignados.");
        ;
    }

}
