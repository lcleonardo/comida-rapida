package com.ceiba.pedido.modelo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoPedido {
	
	private Long id;
	private LocalDateTime fecha;
	private Long idProducto;
	private Long idConductor;
	private String nombreCompletoCliente;
	private String direccionDomicilio;
	private Double precioDomicilio;
	private Double precioTotalCompra;
	
}
