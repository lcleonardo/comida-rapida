package com.ceiba.pedido.servicio;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioEliminarPedidoMockTest {

	private static final Long ID_EN_BD = 1L;
	private static final Long ID_NO_VALIDO = null;
	
	@Test
	public void validarEliminarPedido(){
		// Arrange
		// Act
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		ServicioEliminarPedido servicioEliminarPedido = new ServicioEliminarPedido(repositorioPedido);
		// Assert
		Assertions.assertDoesNotThrow(()-> servicioEliminarPedido.ejecutar(ID_EN_BD),
				 "El id es obligatotio para eliminar un pedido.");
	}
	
	@Test
	public void validarEliminarPedidoConIdNoValido(){
		// Arrange
		// Act
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		ServicioEliminarPedido servicioEliminarPedido = new ServicioEliminarPedido(repositorioPedido);
		// Assert
		Assertions.assertThrows( ExcepcionValorObligatorio.class, ()-> servicioEliminarPedido.ejecutar(ID_NO_VALIDO),
				 "El id es obligatotio para eliminar un pedido.");
	}

}
