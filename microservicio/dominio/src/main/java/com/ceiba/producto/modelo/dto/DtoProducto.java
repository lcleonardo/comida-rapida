package com.ceiba.producto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoProducto {
	
	private Long id;
	private String codigo;
	private String nombre;
	private Double precio;
	
	public boolean existe() {
		return this.id != 0;
	}
	
}
