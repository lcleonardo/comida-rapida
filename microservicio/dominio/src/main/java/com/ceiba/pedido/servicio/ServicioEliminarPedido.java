package com.ceiba.pedido.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.pedido.puerto.dao.DaoPedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioEliminarPedido {

	private final String NO_EXISTE_EL_PEDIDO = "No existe el pedido: ";

	private final RepositorioPedido repositorioPedido;
	private final DaoPedido daoPedido;

	public ServicioEliminarPedido(RepositorioPedido repositorioPedido, DaoPedido daoPedido) {
		this.repositorioPedido = repositorioPedido;
		this.daoPedido = daoPedido;
	}

	public void ejecutar(Long id) {
		validarExistePedido(id);
		this.repositorioPedido.eliminar(id);
	}

	private void validarExistePedido(Long id) {
		boolean existe = this.daoPedido.existePorId(id);
		if (!existe) {
			throw new ExcepcionValorInvalido(NO_EXISTE_EL_PEDIDO + id);
		}
	}

}
