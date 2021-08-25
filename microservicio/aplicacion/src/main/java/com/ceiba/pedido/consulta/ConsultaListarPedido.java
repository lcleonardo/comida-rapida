package com.ceiba.pedido.consulta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.descuento.puerto.dao.DaoPedido;
import com.ceiba.pedido.modelo.dto.DtoPedido;

@Component
public class ConsultaListarPedido {

	private final DaoPedido daoPedido;

	public ConsultaListarPedido(DaoPedido daoPedido) {
		this.daoPedido = daoPedido;
	}

	public List<DtoPedido> ejecutar() {
		return this.daoPedido.listar();
	}
	
}
