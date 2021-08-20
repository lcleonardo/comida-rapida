package com.ceiba.pedido.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.pedido.servicio.ServicioEliminarPedido;

@Component
public class ManejadorEliminarPedido implements ManejadorComando<Long> {

	private final ServicioEliminarPedido servicioEliminarPedido;

	public ManejadorEliminarPedido(ServicioEliminarPedido servicioEliminarPedido) {
		this.servicioEliminarPedido = servicioEliminarPedido;
	}

	@Override
	public void ejecutar(Long id) {
		this.servicioEliminarPedido.ejecutar(id);
	}

}
