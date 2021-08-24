package com.ceiba.pedido.servicio;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.estdatabuilder.PedidoTestDataBuilder;

public class ServicioEliminarPedidoMockTest {

	private static final Long ID_NO_VALIDO = null;
	
	@Test
	public void validarEliminarPedido(){
		// Arrange
		Pedido pedido = new PedidoTestDataBuilder().conId(1L).build();
		// Act
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		ServicioEliminarPedido servicioEliminarPedido = new ServicioEliminarPedido(repositorioPedido);
		// Assert
		Assertions.assertDoesNotThrow(()-> servicioEliminarPedido.ejecutar(pedido.getId()),
				 "El id es obligatotio para eliminar un pedido.");
	}
	
	@Test
	public void validarEliminarPedidoConIdNoValido(){
		// Arrange
		Pedido pedido = new PedidoTestDataBuilder().conId(ID_NO_VALIDO).build();
		// Act
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		ServicioEliminarPedido servicioEliminarPedido = new ServicioEliminarPedido(repositorioPedido);
		// Assert
		Assertions.assertThrows( ExcepcionValorInvalido.class, ()-> servicioEliminarPedido.ejecutar(pedido.getId()),
				 "El id es obligatotio para eliminar un pedido.");
	}

}
