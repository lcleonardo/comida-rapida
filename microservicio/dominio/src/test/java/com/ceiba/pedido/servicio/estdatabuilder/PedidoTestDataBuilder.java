package com.ceiba.pedido.servicio.estdatabuilder;

import com.ceiba.pedido.modelo.entidad.Pedido;

public class PedidoTestDataBuilder {

	private Long id;
	private String fecha;
	private String codigoCliente;
	private String codigoProducto;
	private String direccionDomicilio;
	private String placaVehiculo;
	private Double precioTotalCompra;

	public PedidoTestDataBuilder() {
		this.id = 1L;
		this.fecha = "2021-08-20";
		this.codigoCliente = "1094911832";
		this.codigoProducto = "0001";
		this.direccionDomicilio = "San juan de carolina Calle 123";
		this.placaVehiculo = "VKH526";
		this.precioTotalCompra = 20.000;
	}

	public PedidoTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}

	public PedidoTestDataBuilder conFecha(String fecha) {
		this.fecha = fecha;
		return this;
	}

	public PedidoTestDataBuilder conCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
		return this;
	}

	public PedidoTestDataBuilder conCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
		return this;
	}

	public PedidoTestDataBuilder conDirecionDomicilio(String direccionDomicilio) {
		this.direccionDomicilio = direccionDomicilio;
		return this;
	}

	public PedidoTestDataBuilder conPlacaVehiculo(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo;
		return this;
	}

	public PedidoTestDataBuilder conPrecioTotalCompra(Double precioTotalCompra) {
		this.precioTotalCompra = precioTotalCompra;
		return this;
	}

	public Pedido build() {
		return Pedido.crear(id, fecha, codigoCliente, codigoProducto, direccionDomicilio, placaVehiculo, precioTotalCompra);
	}

}
