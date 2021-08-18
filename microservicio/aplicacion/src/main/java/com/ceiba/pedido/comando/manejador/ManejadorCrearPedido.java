package com.ceiba.pedido.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.pedido.comando.ComandoPedido;
import com.ceiba.pedido.comando.fabrica.FabricaPedido;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.servicio.ServicioCrearPedido;

@Component
public class ManejadorCrearPedido implements ManejadorComandoRespuesta<ComandoPedido, ComandoRespuesta<Long>> {

	private final FabricaPedido fabricaPedido;
	private final ServicioCrearPedido servicioCrearPedido;

	public ManejadorCrearPedido(FabricaPedido fabricaPedido, ServicioCrearPedido servicioCrearPedido) {
		this.fabricaPedido = fabricaPedido;
		this.servicioCrearPedido = servicioCrearPedido;
	}

	@Override
	public ComandoRespuesta<Long> ejecutar(ComandoPedido comando) {
		Pedido pedido = this.fabricaPedido.crear(comando);
		return new ComandoRespuesta<>(this.servicioCrearPedido.ejecutar(pedido));
	}

}
