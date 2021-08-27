package com.ceiba.pedido.servicio.testdatabuilder;

import com.ceiba.pedido.comando.ComandoPedido;

public class ComandoPedidoTestDataBuilder {
	
	private final static Integer NO_APLICA_PROMOCION  =0;
	
	private Long id;
	private String fecha;
	private String codigoCliente;
	private String codigoProducto;
	private String direccionDomicilio;
	private String placaVehiculo;
	private Double porcentajeDescuento;
	private Double precioCompra;
	private Integer aplicaPromocion;

	public ComandoPedidoTestDataBuilder() {
		this.id = 1L;
		this.fecha = "2021-08-20";
		this.codigoCliente = "1094911832";
		this.codigoProducto = "0001";
		this.direccionDomicilio = "Vereda san juan, Casa la chiquita";
		this.placaVehiculo = "VKH526";
		this.porcentajeDescuento = 0.0;
		this.precioCompra = 20.000;
		this.aplicaPromocion = NO_APLICA_PROMOCION;
	}

	public ComandoPedidoTestDataBuilder conFecha(String fecha) {
		this.fecha = fecha;
		return this;
	}

	public ComandoPedidoTestDataBuilder conPlaca(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo;
		return this;
	}
	
	public ComandoPedidoTestDataBuilder conAplicaPromocion(Integer aplicaPromocion) {
		this.aplicaPromocion = aplicaPromocion;
		return this;
	}

	public ComandoPedido build() {
		return new ComandoPedido(id, fecha, codigoCliente, codigoProducto, direccionDomicilio, placaVehiculo,
				porcentajeDescuento, precioCompra, aplicaPromocion);
	}

}
