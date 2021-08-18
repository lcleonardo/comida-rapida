package com.ceiba.pedido.modelo.entidad;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import lombok.Getter;

@Getter
public class Pedido {

	/*
	 * 1. Los dueños de las motos que hacen domicilios no pueden trabajar el día de
	 * pico y placa. No circulan placas terminadas en: martes y jueves numero par,
	 * miercoles y viernes número impar, sábados, domingos y lunes no aplica.
	 */

	/*
	 * 2. La ganancia de los conductores es 5% sobre el valor total del pedido. Los
	 * días viernes y sábados los conductores ganan otro 5 % más sobre el valor
	 * total del domicilio.
	 * 
	 */

	public static final String CODIGO_PRODUCTO_OBLIGATORIO = "El código del producto es obligatorio.";
	public static final String CODIGO_CLIENTE_OBLIGATORIO = "El código del cliente es obligatorio.";
	public static final String DIRECCION_CLIENTE_OBLIGATORIA = "La direción es obligatoria.";
	public static final String FORMATO_FECHA_INCORRECTO = "La fecha debe tener el siguiente formato: yyyy-MM-dd.";
	public static final String PLACA_VEHICULO_OBLIGATORIA = "La placa delvehiculo es obligatoria";
	public static final String FORMATO_PLACA_OBLIGATORIO = "La placa del vehiculo debe terminar en un número entero del 0 al 9.";
	public static final String NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA = "El conductor no puede realizar el domicilio porque tiene pico y placa";
	public static final Double PORCENTAJE_DE_GANANCIA_DOMICILIO = 5.0;

	private Long id;
	private LocalDate fecha;
	private String codigoCliente;
	private String codigoProducto;
	private String direccionDomicilio;
	private String placaVehiculo;
	private Double precioDomicilio;
	private Double precioTotalCompra;

	public static Pedido crear(Long id, String fecha, String codigoCliente, String codigoProducto,
			String direccionDomicilio, String placaVehiculo, Double precioTotalCompra) {

		validarFecha(fecha);
		LocalDate fechaCorrecta = LocalDate.parse(fecha);

		ValidadorArgumento.validarNoVacio(Arrays.asList(codigoCliente), CODIGO_CLIENTE_OBLIGATORIO);
		ValidadorArgumento.validarNoVacio(Arrays.asList(direccionDomicilio), DIRECCION_CLIENTE_OBLIGATORIA);

		ValidadorArgumento.validarNoVacio(Arrays.asList(placaVehiculo), PLACA_VEHICULO_OBLIGATORIA);
		validarSiTienePicoYPlaca(fechaCorrecta, placaVehiculo);

		Double precioDomicilio = calcularPrecioDomicilio(fechaCorrecta, precioTotalCompra);

		return new Pedido(id, fechaCorrecta, codigoCliente, codigoProducto, direccionDomicilio, placaVehiculo,
				precioDomicilio, precioTotalCompra);
	}

	public static void validarFecha(String fecha) {
		try {
			LocalDate.parse(fecha);
		} catch (Exception e) {
			throw new ExcepcionValorInvalido(FORMATO_FECHA_INCORRECTO);
		}
	}

	public static void validarSiTienePicoYPlaca(LocalDate fechaPedido, String placa) {
		Enum<DayOfWeek> diaDeLaSemana = fechaPedido.getDayOfWeek();
		if (placaTerminadaEnNumeroPar(placa)) {
			if (diaDeLaSemana == DayOfWeek.TUESDAY || diaDeLaSemana == DayOfWeek.THURSDAY) {
				throw new ExcepcionValorInvalido(NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA);
			}
		} else {
			if (diaDeLaSemana == DayOfWeek.WEDNESDAY || diaDeLaSemana == DayOfWeek.FRIDAY) {
				throw new ExcepcionValorInvalido(NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA);
			}
		}
	}

	public static Double calcularPrecioDomicilio(LocalDate fecha, Double totalPrecioCompra) {
		return (totalPrecioCompra / 100) * calcularPorcentajeDeGanancia(fecha);
	}

	private static Double calcularPorcentajeDeGanancia(LocalDate fecha) {
		if (fecha.getDayOfWeek() == DayOfWeek.FRIDAY || fecha.getDayOfWeek() == DayOfWeek.SATURDAY) {
			return PORCENTAJE_DE_GANANCIA_DOMICILIO + 5.0;
		}
		return PORCENTAJE_DE_GANANCIA_DOMICILIO;
	}

	private static boolean placaTerminadaEnNumeroPar(String placa) {
		if (ultimoDigitoPlaca(placa) % 2 == 0) {
			return true;
		}
		return false;
	}

	private static int ultimoDigitoPlaca(String placa) {
		try {
			return Integer.parseInt(placa.substring(placa.length() - 1, placa.length()));
		} catch (Exception e) {
			throw new ExcepcionValorInvalido(FORMATO_PLACA_OBLIGATORIO);
		}
	}

	private Pedido(Long id, LocalDate fecha, String codigoCliente, String codigoProducto, String direccionDomicilio,
			String placaVehiculo, Double precioDomicilio, Double precioTotalCompra) {
		this.id = id;
		this.fecha = fecha;
		this.codigoCliente = codigoCliente;
		this.codigoProducto = codigoProducto;
		this.direccionDomicilio = direccionDomicilio;
		this.placaVehiculo = placaVehiculo;
		this.precioDomicilio = precioDomicilio;
		this.precioTotalCompra = precioTotalCompra;
	}

}
