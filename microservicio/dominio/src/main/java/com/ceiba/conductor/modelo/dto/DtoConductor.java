package com.ceiba.conductor.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DtoConductor {
	private Long id;
	private String nombre;
	private String placa;
	
	public boolean existe() {
		return this.id != 0;
	}
}
