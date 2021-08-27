package com.ceiba.pedido.servicio.mock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.ServicioCrearPedido;
import com.ceiba.pedido.servicio.testdatabuilder.PedidoTestDataBuilder;

@RunWith(MockitoJUnitRunner.class)
public class ServicioCrearPedidoMockTest {
	
	@Mock
	RepositorioPedido repositorioPedido;
	
	@InjectMocks
	ServicioCrearPedido servicioCrearPedido;
	
	@Test
	public void crearPedidoTest() {
		// Arrange
		Pedido pedido = new PedidoTestDataBuilder()
				.conFecha("2021-08-23")
				.conCodigoCliente("1094911832")
				.conCodigoProducto("0001")
				.conDirecionDomicilio("San juan de carolina, calle 123")
				.conPlacaVehiculo("VKH525")
				.conPorcentajeDescuento(10.0)
				.conPrecioCompra(50.000)
				.build();
		// Act
		// Assert
		Assert.assertNotNull(servicioCrearPedido.ejecutar(pedido));
	}

}



