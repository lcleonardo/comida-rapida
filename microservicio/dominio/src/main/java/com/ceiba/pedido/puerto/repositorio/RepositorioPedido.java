package com.ceiba.pedido.puerto.repositorio;

import com.ceiba.pedido.modelo.entidad.Pedido;

public interface RepositorioPedido {

	public Long crear(Pedido pedido);

	public void eliminar(Long id);
	
	public Double totalCompraEnEstaSemana(String codigoCliente);

}
