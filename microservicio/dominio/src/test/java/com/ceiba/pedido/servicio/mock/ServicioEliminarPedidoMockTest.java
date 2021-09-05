package com.ceiba.pedido.servicio.mock;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionAccionNoPermitida;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.ServicioEliminarPedido;

import static org.mockito.Mockito.*;

public class ServicioEliminarPedidoMockTest {

    private static final Long ID = 2L;
    private RepositorioPedido repositorioPedido;
    private ServicioEliminarPedido servicioEliminarPedido;

    @Before
    public void preparar() {
        // Arrange
        this.repositorioPedido = mock(RepositorioPedido.class);
        this.servicioEliminarPedido = new ServicioEliminarPedido(repositorioPedido);
    }

    @Test
    public void deberiaEliminarUnPedido() {
        // Act
        // Assert
        Assertions.assertDoesNotThrow(() -> servicioEliminarPedido.ejecutar(ID));
    }

    @Test
    public void deberiaLanzarExcepcionCuandoSeVaAElimiarUnPedidoYExisteUnPedidoCreadoEnUnaFechaPosterior() {
        // Act
        when(this.repositorioPedido.existeUnPedidoConUnaFechaMayor(anyLong())).thenReturn(true);
        // Assert
        BasePrueba.assertThrows(() -> this.servicioEliminarPedido.ejecutar(ID),
                ExcepcionAccionNoPermitida.class,
                "Para eliminar el pedido debe eliminar los pedidos creados en fechas posteriores.");
    }


}








