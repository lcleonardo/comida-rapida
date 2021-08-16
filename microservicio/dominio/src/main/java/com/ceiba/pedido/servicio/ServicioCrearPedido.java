package com.ceiba.pedido.servicio;

import com.ceiba.pedido.modelo.entidad.CarroCompra;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioCrearPedido {
	
	
	private RepositorioPedido repositorioPedido;
	
	public void setRepositorioPedido(RepositorioPedido repositorioPedido) {
		this.repositorioPedido = repositorioPedido;
	}
	
	public Long ejecutar(CarroCompra pedido) {
		return this.repositorioPedido.crear(pedido);
	}
	
	
	
	
}
