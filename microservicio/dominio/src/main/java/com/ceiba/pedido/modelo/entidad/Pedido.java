package com.ceiba.pedido.modelo.entidad;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import lombok.Getter;

@Getter
public class Pedido {

	private static final String CODIGO_PRODUCTO_OBLIGATORIO = "El código del producto es obligatorio.";
	private static final String CODIGO_CLIENTE_OBLIGATORIO = "El código del cliente es obligatorio.";
	private static final String DIRECCION_CLIENTE_OBLIGATORIA = "La direción es obligatoria.";
	private static final String FORMATO_FECHA_INCORRECTO = "La fecha debe tener el siguiente formato: yyyy-MM-dd.";
	private static final String PLACA_VEHICULO_OBLIGATORIA = "La placa delvehiculo es obligatoria";
	private static final String FORMATO_PLACA_OBLIGATORIO = "La placa del vehiculo debe terminar en un número entero del 0 al 9.";
	private static final String NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA = "El conductor no puede realizar el domicilio porque tiene pico y placa";
	private static final Double PORCENTAJE_DE_GANANCIA_DOMICILIO = 5.0;
	private static final String PRECIO_TOTAl_LA_COMPRA_OBLIGATOIO = "El precio total de la compra es obligatorio.";

	private Long id;
	private LocalDate fecha;
	private String codigoCliente;
	private String codigoProducto;
	private String direccionDomicilio;
	private String placaVehiculo;
	private Double precioDomicilio;
	private Double precioTotalCompra;

	public static Pedido crear(Long id, String fecha, String codigoCliente, String codigoProducto,
			String direccionDomicilio, String placaVehiculo, String precioTotalCompra) {

		validarFecha(fecha);
		LocalDate fechaCorrecta = LocalDate.parse(fecha);

		ValidadorArgumento.validarNoVacio(Arrays.asList(codigoProducto), CODIGO_PRODUCTO_OBLIGATORIO);
		ValidadorArgumento.validarNoVacio(Arrays.asList(codigoCliente), CODIGO_CLIENTE_OBLIGATORIO);
		ValidadorArgumento.validarNoVacio(Arrays.asList(direccionDomicilio), DIRECCION_CLIENTE_OBLIGATORIA);
		validarprecioTotalCompra(precioTotalCompra, PRECIO_TOTAl_LA_COMPRA_OBLIGATOIO);

		ValidadorArgumento.validarNoVacio(Arrays.asList(placaVehiculo), PLACA_VEHICULO_OBLIGATORIA);
		validarSiTienePicoYPlaca(fechaCorrecta, placaVehiculo);

		Double precioTotalCompraCorrecto = Double.parseDouble(precioTotalCompra);
		Double precioDomicilio = calcularPrecioDomicilio(fechaCorrecta, precioTotalCompraCorrecto);

		return new Pedido(id, fechaCorrecta, codigoCliente, codigoProducto, direccionDomicilio, placaVehiculo,
				precioDomicilio, precioTotalCompraCorrecto);
	}

	public static void validarFecha(String fecha) {
		try {
			LocalDate.parse(fecha);
		} catch (DateTimeParseException e) {
			throw new ExcepcionValorInvalido(FORMATO_FECHA_INCORRECTO);
		}
	}

	public static void validarSiTienePicoYPlaca(LocalDate fechaPedido, String placa) {
		Enum<DayOfWeek> diaDeLaSemana = fechaPedido.getDayOfWeek();
		if (placaTerminadaEnNumeroPar(placa)) {
			if (diaDeLaSemana.equals(DayOfWeek.TUESDAY) || diaDeLaSemana.equals(DayOfWeek.THURSDAY)) {
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
		Enum<DayOfWeek> diaDeLaSemana = fecha.getDayOfWeek();
		if (diaDeLaSemana.equals(DayOfWeek.FRIDAY) || diaDeLaSemana.equals(DayOfWeek.SATURDAY)) {
			return PORCENTAJE_DE_GANANCIA_DOMICILIO + 5.0;
		}
		return PORCENTAJE_DE_GANANCIA_DOMICILIO;
	}

	private static boolean placaTerminadaEnNumeroPar(String placa) {
		return ultimoDigitoPlaca(placa) % 2 == 0;
	}

	private static int ultimoDigitoPlaca(String placa) {
		try {
			return Integer.parseInt(placa.substring(placa.length() - 1));
		} catch (NullPointerException | NumberFormatException e) {
			throw new ExcepcionValorInvalido(FORMATO_PLACA_OBLIGATORIO);
		}
	}

	public static void validarprecioTotalCompra(String precioTotalCompra, String mensaje) {
		try {
			Double.parseDouble(precioTotalCompra);
		} catch (NumberFormatException numberFormatException) {
			throw new ExcepcionValorInvalido(mensaje);
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
