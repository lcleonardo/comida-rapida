package com.ceiba.descuento.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoDescuento {
		
		private Long id;
		private String fecha;
		private Double porcentaje;
	
}
