package com.ceiba.pedido.servicio.mock;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.ServicioEliminarPedido;

public class ServicioEliminarPedidoMockTest {

	private static final Long ID = 1L;
	
	@Test
	public void validarEliminarPedidoTest(){
		// Arrange
		// Act
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		ServicioEliminarPedido servicioEliminarPedido = new ServicioEliminarPedido(repositorioPedido);
		// Assert
		Assertions.assertDoesNotThrow(()-> servicioEliminarPedido.ejecutar(ID));
	}
	
}
