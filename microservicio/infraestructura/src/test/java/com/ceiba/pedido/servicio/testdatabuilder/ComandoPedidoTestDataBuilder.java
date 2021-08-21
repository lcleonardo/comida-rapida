package com.ceiba.pedido.servicio.testdatabuilder;

import com.ceiba.pedido.comando.ComandoPedido;

public class ComandoPedidoTestDataBuilder {

	private Long id;
	private String fecha;
	private String codigoCliente;
	private String codigoProducto;
	private String direccionDomicilio;
	private String placaVehiculo;
	private Double precioTotalCompra;

	public ComandoPedidoTestDataBuilder() {
		this.id = 1L;
		this.fecha = "2021-08-20";
		this.codigoCliente = "1094911832";
		this.codigoProducto = "0001";
		this.direccionDomicilio = "Vereda san juan, Casa la chiquita";
		this.placaVehiculo = "VKH526";
		this.precioTotalCompra = 25.000;
	}
	
	public ComandoPedidoTestDataBuilder conFecha(String fecha) {
		this.fecha = fecha;
		return this;
	}
	
	public ComandoPedidoTestDataBuilder conPlaca(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo;
		return this;
	}

	public ComandoPedido build() {
		return  new ComandoPedido(id, fecha, codigoCliente, codigoProducto, direccionDomicilio, placaVehiculo, precioTotalCompra.toString());
	}

}
