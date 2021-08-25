package com.ceiba.descuento.modelo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoDescuento {

	private Long id;
	private LocalDate fecha;
	private Double porcentajeDescuento;
}
