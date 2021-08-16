package com.ceiba.pedido.servicio;

import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioCrearPedido {
	
	
	private RepositorioPedido repositorioPedido;
	
	public void setRepositorioPedido(RepositorioPedido repositorioPedido) {
		this.repositorioPedido = repositorioPedido;
	}
	
	public Long ejecutar(Pedido pedido) {
		//TODO: Validar si la placa del conductor tiene pico y placa.
		
		return this.repositorioPedido.crear(pedido);
	}
	
	
	
	
}
