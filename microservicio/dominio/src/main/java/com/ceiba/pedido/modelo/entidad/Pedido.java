package com.ceiba.pedido.modelo.entidad;

import lombok.Getter;

@Getter
public class Pedido {
	
	private int id;
	private Producto producto;
	private Conductor conductor;
	private String nombreCompletoCliente;
	private String direccionDomicilio;
	private Double valorPedido;
	private boolean entrego;
	
	
	
}
