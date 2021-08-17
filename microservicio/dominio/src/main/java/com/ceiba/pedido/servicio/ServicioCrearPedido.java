package com.ceiba.pedido.servicio;

import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioCrearPedido {

	private RepositorioPedido repositorioPedido;

	public ServicioCrearPedido(RepositorioPedido repositorioPedido) {
		super();
		this.repositorioPedido = repositorioPedido;
	}

	public Long ejecutar(Pedido pedido) {
		// TODO: validar
		return this.repositorioPedido.crear(pedido);
	}

}
