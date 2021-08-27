package com.ceiba.pedido.consulta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.descuento.puerto.dao.DaoPedido;
import com.ceiba.pedido.modelo.dto.DtoPedido;

@Component
public class ManejadorListarPedido {

	private final DaoPedido daoPedido;

	public ManejadorListarPedido(DaoPedido daoPedido) {
		this.daoPedido = daoPedido;
	}

	public List<DtoPedido> ejecutar() {
		return this.daoPedido.listar();
	}
	
}
