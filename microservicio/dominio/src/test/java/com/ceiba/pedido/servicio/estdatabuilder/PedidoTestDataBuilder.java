package com.ceiba.pedido.servicio.estdatabuilder;

import com.ceiba.pedido.modelo.entidad.Pedido;

public class PedidoTestDataBuilder {

	private String fecha;
	private String codigoCliente;
	private String codigoProducto;
	private String direccionDomicilio;
	private String placaVehiculo;
	private Double porcentajeDescuento;
	private Double precioCompra;

	public PedidoTestDataBuilder() {
		this.fecha = "2021-08-20";
		this.codigoCliente = "1094911832";
		this.codigoProducto = "0001";
		this.direccionDomicilio = "San juan de carolina, calle 123";
		this.placaVehiculo = "VKH526";
		this.porcentajeDescuento = 0.0;
		this.precioCompra = 20.000;
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

	public PedidoTestDataBuilder conPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
		return this;
	}

	public PedidoTestDataBuilder conPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
		return this;
	}

	public Pedido build() {
		return Pedido.crear(fecha, codigoCliente, codigoProducto, direccionDomicilio, placaVehiculo,
				porcentajeDescuento, precioCompra);
	}

}
