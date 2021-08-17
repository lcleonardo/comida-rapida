package com.ceiba.pedido.servicio;

import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.producto.puerto.repositorio.RepositorioProducto;

public class ServicioCrearPedido {

	private RepositorioPedido repositorioPedido;
	private RepositorioProducto repositorioProducto;


	public ServicioCrearPedido(RepositorioPedido repositorioPedido) {
		super();
		this.repositorioPedido = repositorioPedido;
	}

	public Long ejecutar(Pedido pedido) {
		
		return this.repositorioPedido.crear(pedido);
	}
	
	
	private void validar(Pedido pedido) {
		
	}
	
//	Double precioDomicilio = conductor.calcularPrecioDomicilio(fecha, producto.getPrecio());
//	Double totalPrecioCompra = producto.getPrecio() - precioDomicilio;
	


}
