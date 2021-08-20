package com.ceiba.pedido.consulta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.pedido.modelo.dto.DtoPedido;
import com.ceiba.pedido.puerto.dao.DaoPedido;

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
