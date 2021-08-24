package com.ceiba.pedido.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioEliminarPedido {

	private static final String ID_OBLIGATORIO_PARA_ELIMINAR_UN_PEDIDO = "El id es obligatotio para eliminar un pedido.";

	private final RepositorioPedido repositorioPedido;

	public ServicioEliminarPedido(RepositorioPedido repositorioPedido) {
		this.repositorioPedido = repositorioPedido;
	}

	public void ejecutar(Long id) {
		if (id == null) {
			throw new ExcepcionValorInvalido(ID_OBLIGATORIO_PARA_ELIMINAR_UN_PEDIDO);
		}
		this.repositorioPedido.eliminar(id);
	}

}
