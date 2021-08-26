package com.ceiba.pedido.modelo.entidad;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import lombok.Getter;

@Getter
public class Pedido {

	private static final String FECHA_INCORRECTA = "Fecha incorrecta, debe tener el siguiente formato: yyyy-MM-dd.";
	private static final String CODIGO_PRODUCTO_OBLIGATORIO = "El código del producto es obligatorio.";
	private static final String CODIGO_CLIENTE_OBLIGATORIO = "El código del cliente es obligatorio.";
	private static final String DIRECCION_DOMICILIO_OBLIGATORIA = "La direción es obligatoria.";
	private static final String PLACA_VEHICULO_OBLIGATORIA = "La placa del vehiculo es obligatoria y debe terminar en un número entero del 0 al 9.";
	private static final String PRECIO_COMPRA_OBLIGATORIO = "El precio de la compra es obligatorio.";
	private static final String PORCENTAJE_DE_DESCUENTO_NO_VALIDO = "El porcentaje de descuento debe ser un número mayor a 0.0";
	private static final String NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA = "El conductor no puede realizar el domicilio porque tiene pico y placa.";
	private static final Double PORCENTAJE_NORMAL_DE_GANANCIA_EN_DOMICILIO = 5.0;
	private static final Double CINCO_PORCIENTO_MAS_DE_GANACIA_EN_DOMICILIO = 5.0;
	private static final Double NO_SE_CALCULA_PRECIO_DE_DESCUENTO = 0.0;

	private Long id;
	private LocalDate fecha;
	private String codigoCliente;
	private String codigoProducto;
	private String direccionDomicilio;
	private String placaVehiculo;
	private Double precioDomicilio;
	private Double porcentajeDescuento;
	private Double precioCompra;
	private Double precioTotal;

	public static Pedido crear(String fecha, String codigoCliente, String codigoProducto, String direccionDomicilio,
			String placaVehiculo, Double porcentajeDescuento, Double precioCompra) {

		ValidadorArgumento.validarObligatorio(fecha, FECHA_INCORRECTA);
		ValidadorArgumento.validarFechaFormatoYYYMMDD(fecha, FECHA_INCORRECTA);
		LocalDate fechaValida = LocalDate.parse(fecha, DateTimeFormatter.ISO_DATE);

		ValidadorArgumento.validarObligatorio(codigoCliente, CODIGO_CLIENTE_OBLIGATORIO);
		ValidadorArgumento.validarNoVacio(codigoCliente, CODIGO_CLIENTE_OBLIGATORIO);

		ValidadorArgumento.validarObligatorio(codigoProducto, CODIGO_PRODUCTO_OBLIGATORIO);
		ValidadorArgumento.validarNoVacio(codigoProducto, CODIGO_PRODUCTO_OBLIGATORIO);

		ValidadorArgumento.validarObligatorio(direccionDomicilio, DIRECCION_DOMICILIO_OBLIGATORIA);
		ValidadorArgumento.validarNoVacio(direccionDomicilio, DIRECCION_DOMICILIO_OBLIGATORIA);

		ValidadorArgumento.validarObligatorio(placaVehiculo, PLACA_VEHICULO_OBLIGATORIA);
		ValidadorArgumento.validarNoVacio(placaVehiculo, PLACA_VEHICULO_OBLIGATORIA);
		validarFormatoPlacaVehiculo(placaVehiculo, PLACA_VEHICULO_OBLIGATORIA);
		validarSiLaPlacaDelVehiculoTienePicoYPlaca(fechaValida, placaVehiculo);

		ValidadorArgumento.validarObligatorio(porcentajeDescuento, PORCENTAJE_DE_DESCUENTO_NO_VALIDO);
		ValidadorArgumento.validarMenorACero(porcentajeDescuento, PORCENTAJE_DE_DESCUENTO_NO_VALIDO);

		ValidadorArgumento.validarObligatorio(precioCompra, PRECIO_COMPRA_OBLIGATORIO);
		ValidadorArgumento.validarMenorACero(precioCompra, PRECIO_COMPRA_OBLIGATORIO);

		Double precioDescuento = calcularPrecioDescuento(porcentajeDescuento, precioCompra);
		Double precioTotal = precioCompra - precioDescuento;
		Double precioDomicilio = calcularPrecioDomicilio(fechaValida, precioTotal);

		return new Pedido(fechaValida, codigoCliente, codigoProducto, direccionDomicilio, placaVehiculo,
				precioDomicilio, porcentajeDescuento, precioCompra, precioTotal);
	}

	private static void validarFormatoPlacaVehiculo(String placaVehiculo, String mensaje) {
		try {
			String ultimoCaracter = placaVehiculo.substring(placaVehiculo.length() - 1);
			Integer.parseInt(ultimoCaracter);
		} catch (NumberFormatException e) {
			throw new ExcepcionValorInvalido(mensaje);
		}
	}

	private static void validarSiLaPlacaDelVehiculoTienePicoYPlaca(LocalDate fechaPedido, String placa) {
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

	private static Double calcularPrecioDescuento(Double porcentajeDescuento, Double precioCompra) {
		if (porcentajeDescuento <= 0) {
			return NO_SE_CALCULA_PRECIO_DE_DESCUENTO;
		}
		return (precioCompra / 100) * porcentajeDescuento;
	}

	private static Double calcularPrecioDomicilio(LocalDate fecha, Double precioTotal) {
		return (precioTotal / 100) * calcularPorcentajeDeGanancia(fecha);
	}

	private static Double calcularPorcentajeDeGanancia(LocalDate fecha) {
		Enum<DayOfWeek> diaDeLaSemana = fecha.getDayOfWeek();
		if (diaDeLaSemana == DayOfWeek.FRIDAY || diaDeLaSemana == DayOfWeek.SATURDAY) {
			return PORCENTAJE_NORMAL_DE_GANANCIA_EN_DOMICILIO + CINCO_PORCIENTO_MAS_DE_GANACIA_EN_DOMICILIO;
		}
		return PORCENTAJE_NORMAL_DE_GANANCIA_EN_DOMICILIO;
	}

	private static boolean placaTerminadaEnNumeroPar(String placa) {
		return ultimoDigitoPlaca(placa) % 2 == 0;
	}

	private static int ultimoDigitoPlaca(String placa) {
		return Integer.parseInt(placa.substring(placa.length() - 1));
	}

	private Pedido(LocalDate fecha, String codigoCliente, String codigoProducto, String direccionDomicilio,
			String placaVehiculo, Double precioDomicilio, Double porcentajeDescuento, Double precioCompra,
			Double precioTotal) {
		this.id = 0L;
		this.fecha = fecha;
		this.codigoCliente = codigoCliente;
		this.codigoProducto = codigoProducto;
		this.direccionDomicilio = direccionDomicilio;
		this.placaVehiculo = placaVehiculo;
		this.precioDomicilio = precioDomicilio;
		this.porcentajeDescuento = porcentajeDescuento;
		this.precioCompra = precioCompra;
		this.precioTotal = precioTotal;
	}

}
