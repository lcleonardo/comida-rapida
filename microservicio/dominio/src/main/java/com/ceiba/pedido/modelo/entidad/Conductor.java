package com.ceiba.pedido.modelo.entidad;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import lombok.Getter;

@Getter
public class Conductor {

	private final Double PORCENTAJE_NORMAL_DE_GANANCIA = 5.0;
	private final Double DOBLE_PORCENTAJE_DE_GANANCIA = PORCENTAJE_NORMAL_DE_GANANCIA + 5.0;
	private final String NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA = "No puede realizar el domicilio, porque tiene pico y placa.";

	private String nombre;
	private String placa;

	/*
	 * Los dueños de las motos que hacen domicilios no pueden trabajar el día de
	 * pico y placa. No circulan placas terminadas en: martes y jueves
	 * numero par, miércoles y viernes numero impar, sábados, domingos y lunes no
	 * aplica.
	 */

	public void validarSiTienePicoYPlaca(LocalDateTime fechaPedido) {
		Enum<DayOfWeek> dayOfWeek = fechaPedido.getDayOfWeek();
		if (dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.THURSDAY && placaTerminadaEnNumeroPar()) {
			throw new ExcepcionValorInvalido(NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA);
		}
		if (dayOfWeek == DayOfWeek.WEDNESDAY || dayOfWeek == DayOfWeek.FRIDAY && !placaTerminadaEnNumeroPar()) {
			throw new ExcepcionValorInvalido(NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA);
		}
	}

	private boolean placaTerminadaEnNumeroPar() {
		if (ultimoDigitoPlaca() % 2 == 0) {
			return true;
		}
		return false;
	}

	private int ultimoDigitoPlaca() {
		return Integer.parseInt(placa.substring(placa.length() - 1, placa.length()));
	}

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
