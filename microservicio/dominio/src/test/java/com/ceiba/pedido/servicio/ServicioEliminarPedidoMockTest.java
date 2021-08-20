package com.ceiba.pedido.servicio;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.dao.DaoPedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.estdatabuilder.PedidoTestDataBuilder;

public class ServicioEliminarPedidoMockTest {

	@Test
	public void validarNoExisteIdPedidoEnBaseDeDatos() {
		// Arrange
		Pedido pedido = new PedidoTestDataBuilder().conId(1L).build();
		// Act
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		DaoPedido daoPedido = Mockito.mock(DaoPedido.class);
		ServicioEliminarPedido servicioEliminarPedido = new ServicioEliminarPedido(repositorioPedido, daoPedido);
		// Assert
		BasePrueba.assertThrows(() -> {
			servicioEliminarPedido.ejecutar(pedido.getId());
		}, ExcepcionValorInvalido.class, "No existe el pedido: " + pedido.getId());
	}

}
