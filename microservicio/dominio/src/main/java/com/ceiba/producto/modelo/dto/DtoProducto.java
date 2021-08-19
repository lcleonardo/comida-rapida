package com.ceiba.producto.modelo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoProducto {
	
	private Long id;
	private String nombre;
	private String detalle;
	private Double precio;
	private LocalDateTime fechaDosPorUNo;
}
