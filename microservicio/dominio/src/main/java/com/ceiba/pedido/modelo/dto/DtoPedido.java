package com.ceiba.pedido.modelo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoPedido {
	
	private Long id;
	private LocalDate fecha;
	private String codigoCliente;
	private String codigoProducto;
	private String direccionDomicilio;
	private String placaVehiculo;
	private Double precioDomicilio;
	private Double precioTotalCompra;
	
}
