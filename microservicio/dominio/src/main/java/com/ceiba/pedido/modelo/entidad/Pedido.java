package com.ceiba.pedido.modelo.entidad;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Pedido {
	
	private Long id;
	private LocalDateTime fecha; 
	private Producto producto;
	private Conductor conductor;
	private String nombreCompletoCliente;
	private String direccionDomicilio;
	private Double precioPedido;
	private boolean entrego;

	
	private Double calcularPrecioGananciaDelConductor() {
		Double ganancia = this.precioPedido / 100 * this.conductor.calcularPrecioDeGanancia(this.fecha, this.precioPedido);
		return ganancia;
	}
	
	
	
}
