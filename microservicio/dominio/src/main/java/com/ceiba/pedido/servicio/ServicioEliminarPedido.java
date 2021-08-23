package com.ceiba.pedido.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioEliminarPedido {

	private static final String NO_EXISTE_EL_PEDIDO = "No existe el pedido: ";

	private final RepositorioPedido repositorioPedido;

	public ServicioEliminarPedido(RepositorioPedido repositorioPedido) {
		this.repositorioPedido = repositorioPedido;
	}

	public void ejecutar(Long id){
		try {
			this.repositorioPedido.eliminar(id);
		} catch (ExcepcionValorInvalido e) {
			throw new ExcepcionValorInvalido(NO_EXISTE_EL_PEDIDO + id);
		}
	}

}
