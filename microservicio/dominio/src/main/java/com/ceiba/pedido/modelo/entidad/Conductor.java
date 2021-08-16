package com.ceiba.pedido.modelo.entidad;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Conductor {

	private final Double PORCENTAJE_NORMAL_DE_GANANCIA = 5.0;
	private final Double DOBLE_PORCENTAJE_DE_GANANCIA = PORCENTAJE_NORMAL_DE_GANANCIA + 5.0;

	private String nombre;
	private String placa;

	/*
	 * La ganancia de los conductores es 5% sobre el valor total del pedido. Los
	 * días viernes y sábados los conductores ganan otro 5 % más sobre el precio del
	 * pedido.
	 */
	public Double calcularPrecioDeGanancia(LocalDateTime fecha, Double precioPedido) {
		Double porcentajeDeGanancia = precioPedido / 100 * calcularPorcentajeDeGanancia(fecha);
		return porcentajeDeGanancia;
	}

	private Double calcularPorcentajeDeGanancia(LocalDateTime fecha) {
		if (fecha.getDayOfWeek() == DayOfWeek.FRIDAY || fecha.getDayOfWeek() == DayOfWeek.SATURDAY) {
			return DOBLE_PORCENTAJE_DE_GANANCIA;
		}
		return PORCENTAJE_NORMAL_DE_GANANCIA;
	}

}
