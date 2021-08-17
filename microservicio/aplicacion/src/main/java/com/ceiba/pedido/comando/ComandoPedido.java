package com.ceiba.pedido.comando;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComandoPedido {
	
	private Long id;
	private LocalDateTime fecha;
	private Long idProducto;
	private Long idConductor;
	private String nombreCompletoCliente;
	private String direccionDomicilio;
	private Double precioDomicilio;
	private Double precioTotalCompra;

}
