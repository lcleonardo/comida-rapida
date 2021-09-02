package com.ceiba.pedido.servicio.mock;



import java.time.LocalDate;
import java.util.List;

import org.hamcrest.core.Every;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.ServicioCrearPedido;
import com.ceiba.pedido.servicio.testdatabuilder.PedidoTestDataBuilder;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServicioCrearPedidoMockTest {

	@Test
	public void crearPedidoTest() {
		Pedido primerPedido = new PedidoTestDataBuilder()
				.conFechaDate(LocalDate.now())
				.conCodigoCliente("1094")
				.conCodigoProducto("0001")
				.conPlacaVehiculo("VDG123")
				.conPrecioCompra(500.000)
				.build();
		ArgumentCaptor<Pedido> captor = ArgumentCaptor.forClass(Pedido.class);
		RepositorioDescuento repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		ServicioCrearPedido servicioCrearPedido = new ServicioCrearPedido(repositorioPedido, repositorioDescuento);
		// Act
		when(repositorioPedido.testAplicaPromocion(primerPedido)).thenReturn(1);
		when(repositorioPedido.crear(captor.capture())).thenReturn(1L);

		when(servicioCrearPedido.ejecutar(primerPedido)).thenReturn(1L);
		Pedido pedidoConPromocion = captor.getValue();
		//Assert
		Assert.assertEquals(250.000, pedidoConPromocion.getPrecioTotal(), 0.0);
	}

}






