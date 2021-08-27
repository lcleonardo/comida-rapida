package com.ceiba.pedido.servicio.mock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.ServicioCrearPedido;
import com.ceiba.pedido.servicio.testdatabuilder.PedidoTestDataBuilder;

@RunWith(MockitoJUnitRunner.class)
public class ServicioCrearPedidoMockTest {

	@Mock
	RepositorioPedido repositorioPedido;
	
	@Mock
	RepositorioDescuento repositorioDescuento;
	
	@InjectMocks
	ServicioCrearPedido servicioCrearPedido;
	
	@Test
	public void crearPedidoTest() {
		// Arrange
		Pedido pedido = new PedidoTestDataBuilder()
				.conFecha("2021-08-23")
				.conCodigoCliente("1094")
				.conCodigoProducto("0001")
				.conDirecionDomicilio("San juan de carolina.")
				.conPlacaVehiculo("VKH525")
				.conPrecioCompra(50.000)
				.build();
		// Act
		// Assert
		Assert.assertNotNull(servicioCrearPedido.ejecutar(pedido));
	}

}



