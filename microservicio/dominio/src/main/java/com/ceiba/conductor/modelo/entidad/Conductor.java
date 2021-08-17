package com.ceiba.conductor.modelo.entidad;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import lombok.Getter;

@Getter
public class Conductor {

	private static final Double PORCENTAJE_NORMAL_DE_GANANCIA = 5.0;
	private static final Double DOBLE_PORCENTAJE_DE_GANANCIA = PORCENTAJE_NORMAL_DE_GANANCIA + 5.0;
	private static final String NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA = "El conductor no puede realizar el domicilio, porque tiene pico y placa.";
	private static String EL_NOMBRE_ES_OBLIGATORIO = "El nombre es obligatorio";
	private static String LA_PLACA_ES_OBLIGTORIA = "La placa es obligatoia";
	private static String LA_PLACA_DEBE_TENER_MINIMO_SEIS_CARATERES = "La placa debe tener minimo 6 caracteres.";
	private static String LA_PLACA_DEBE_TERMINAR_EN_NUMERO_ENTERO = "La placa debe terminar en numero entero.";

	private Long id;
	private String nombre;
	private String placa;

	public static Conductor crear(Long id, String nombre, String placa) {
		ValidadorArgumento.validarNoVacio(Arrays.asList(nombre), EL_NOMBRE_ES_OBLIGATORIO);
		ValidadorArgumento.validarNoVacio(Arrays.asList(placa), LA_PLACA_ES_OBLIGTORIA);
		ValidadorArgumento.validarLongitudMinima(placa, 6, LA_PLACA_DEBE_TENER_MINIMO_SEIS_CARATERES);
		validarUltimoDigitoPlaca(placa, LA_PLACA_DEBE_TERMINAR_EN_NUMERO_ENTERO);
		return new Conductor(id, nombre, placa);
	}

	private Conductor(Long id, String nombre, String placa) {
		this.id = id;
		this.nombre = nombre;
		this.placa = placa;
	}

	/*
	 * Los dueños de las motos que hacen domicilios no pueden trabajar el día de
	 * pico y placa. No circulan placas terminadas en: martes y jueves numero par,
	 * miírcoles y viernes número impar, sábados, domingos y lunes no aplica.
	 */

	public static void validarSiTienePicoYPlaca(LocalDate fechaPedido, String placa) {
		Enum<DayOfWeek> dayOfWeek = fechaPedido.getDayOfWeek();
		if (placaTerminadaEnNumeroPar(placa)) {
			if (dayOfWeek == DayOfWeek.TUESDAY || dayOfWeek == DayOfWeek.THURSDAY) {
				throw new ExcepcionValorInvalido(NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA);
			}
		} else {
			if (dayOfWeek == DayOfWeek.WEDNESDAY || dayOfWeek == DayOfWeek.FRIDAY) {
				throw new ExcepcionValorInvalido(NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA);
			}

		}
	}

	/*
	 * La ganancia de los conductores es 5% sobre el valor total del pedido. Los
	 * días viernes y s�bados los conductores ganan otro 5 % m�s sobre el precio del
	 * pedido.
	 */

	public static Double calcularPrecioDomicilio(LocalDate fecha, Double totalPrecioCompra) {
		return totalPrecioCompra / 100 * calcularPorcentajeDeGanancia(fecha);
	}

	private static Double calcularPorcentajeDeGanancia(LocalDate fecha) {
		if (fecha.getDayOfWeek() == DayOfWeek.FRIDAY || fecha.getDayOfWeek() == DayOfWeek.SATURDAY) {
			return DOBLE_PORCENTAJE_DE_GANANCIA;
		}
		return PORCENTAJE_NORMAL_DE_GANANCIA;
	}

	private static boolean placaTerminadaEnNumeroPar(String placa) {
		if (ultimoDigitoPlaca(placa) % 2 == 0) {
			return true;
		}
		return false;
	}

	private static void validarUltimoDigitoPlaca(String placa, String mensaje) {
		try {
			Integer.parseInt(placa.substring(placa.length() - 1, placa.length()));
		} catch (Exception e) {
			ValidadorArgumento.validarObligatorio(placa, mensaje);
		}
	}

	private static int ultimoDigitoPlaca(String placa) {
		return Integer.parseInt(placa.substring(placa.length() - 1, placa.length()));
	}

}
