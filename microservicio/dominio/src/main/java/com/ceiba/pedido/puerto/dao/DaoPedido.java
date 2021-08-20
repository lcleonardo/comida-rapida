package com.ceiba.pedido.puerto.dao;

import java.util.List;

import com.ceiba.pedido.modelo.dto.DtoPedido;

public interface DaoPedido {

	public List<DtoPedido> listar();

	public boolean existePorId(Long id);
}
