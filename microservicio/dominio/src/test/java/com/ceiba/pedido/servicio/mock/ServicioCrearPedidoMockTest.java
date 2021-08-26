package com.ceiba.pedido.servicio.mock;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.ServicioCrearPedido;
import com.ceiba.pedido.servicio.testdatabuilder.PedidoTestDataBuilder;

public class ServicioCrearPedidoMockTest {

	@Test
	public void validarCrearPedido() {
		// Arrange
		Pedido pedido = new PedidoTestDataBuilder()
				.conFecha("2021-08-20")
				.conPlacaVehiculo("VKH244").build();
		// Act
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		ServicioCrearPedido servicioCrearPedido = new ServicioCrearPedido(repositorioPedido);
		// Assert
		Assert.assertNotNull(servicioCrearPedido.ejecutar(pedido));
	}

}
