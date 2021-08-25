package com.ceiba.pedido.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.servicio.estdatabuilder.PedidoTestDataBuilder;

public class ServicioCrearPedidoUnitTest {

	private static final String FECHA = "2021-08-20";
	private static final String FECHA_INCORRECTA = "20-08-2021";
	private static final String DIA_MARTES_PICO_Y_PLACA_PLACA_TERMINADA_EN_NUMERO_PAR = "2021-08-17";
	private static final String DIA_MIERCOLES_PICO_Y_PLACA_PLACA_TERMINADA_EN_NUMERO_IMPAR = "2021-08-18";
	private static final String PLACA_TERMINADA_EN_NUMERO_PAR = "VKH526";
	private static final String PLACA_TERMINADA_EN_NUMERO_IMPAR = "VKH57";
	private static final Double PRECIO_COMPRA = 20.000;

	@Test
	public void crearPedidoJuevesNoAplicaPorcentajeAdicionalDeDomicilioTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha("2021-08-19")
				.conPlacaVehiculo("VKH525")
				.conPorcentajeDescuento(0.0)
				.conPrecioCompra(20.000);

		// 2. Act
		Pedido pedido = pedidoTestDataBuilder.build();

		// 3. Assert
		
		assertEquals("2021-08-19", pedido.getFecha().format(DateTimeFormatter.ISO_DATE));
		assertEquals("1094911832", pedido.getCodigoCliente());
		assertEquals("0001", pedido.getCodigoProducto());
		assertEquals("San juan de carolina, calle 123", pedido.getDireccionDomicilio());
		assertEquals("VKH525", pedido.getPlacaVehiculo());
		assertEquals(0.0, pedido.getPorcentajeDescuento().doubleValue());
		assertEquals(1.000, pedido.getPrecioDomicilio().doubleValue());
		assertEquals(20.000, pedido.getPrecioCompra().doubleValue());
		}

	@Test
	public void crearPedidoConFechaIncorrectaTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha(FECHA_INCORRECTA);
		// 2. Act

		// 3. Assert
		BasePrueba.assertThrows(() -> {
			pedidoTestDataBuilder.build();
		}, ExcepcionValorInvalido.class, "Fecha incorrecta, debe tener el siguiente formato: yyyy-MM-dd.");
	}

	@Test
	public void validarPlacasTerminadasEnNumeroParTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha(DIA_MARTES_PICO_Y_PLACA_PLACA_TERMINADA_EN_NUMERO_PAR)
				.conPlacaVehiculo(PLACA_TERMINADA_EN_NUMERO_PAR);
		// 2. Act

		// 3. Assert
		Throwable throwable = assertThrows(ExcepcionValorInvalido.class, pedidoTestDataBuilder::build);
		assertEquals( "El conductor no puede realizar el domicilio porque tiene pico y placa.",throwable.getMessage());
	}

	@Test
	public void validarPlacasTerminadasEnNumeroImparTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha(DIA_MIERCOLES_PICO_Y_PLACA_PLACA_TERMINADA_EN_NUMERO_IMPAR)
				.conPlacaVehiculo(PLACA_TERMINADA_EN_NUMERO_IMPAR);
		// 2. Act

		// 3. Assert
		Throwable throwable = assertThrows(ExcepcionValorInvalido.class, pedidoTestDataBuilder::build);
		assertEquals("El conductor no puede realizar el domicilio porque tiene pico y placa.", throwable.getMessage());
	}

	@Test
	public void calcularPrecioDomicilioTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha(FECHA)
				.conPlacaVehiculo(PLACA_TERMINADA_EN_NUMERO_PAR)
				.conPorcentajeDescuento(0.0)
				.conPrecioCompra(20.000);

		// 2. Act
		Pedido pedido = pedidoTestDataBuilder.build();

		// 3. Assert
		assertEquals(2.000, pedido.getPrecioDomicilio().doubleValue());
		
	}

}
