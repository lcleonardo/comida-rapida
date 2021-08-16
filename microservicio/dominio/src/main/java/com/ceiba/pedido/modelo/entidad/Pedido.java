package com.ceiba.pedido.modelo.entidad;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Pedido {
	
	private Long id;
	private Producto producto;
	private Conductor conductor;
	private String nombreCompletoCliente;
	private String direccionDomicilio;
	private Double precioPedido;
	private LocalDateTime fecha; 

	
	private Double calcularPrecioGananciaDelConductor() {
		Double ganancia = this.precioPedido / 100 * this.conductor.calcularPorcentajeDeGanancia(this.fecha);
		return ganancia;
	}
	
	
	
}
