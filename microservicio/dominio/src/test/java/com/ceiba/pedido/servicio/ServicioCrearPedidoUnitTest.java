package com.ceiba.pedido.servicio;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.Test;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.servicio.estdatabuilder.PedidoTestDataBuilder;

public class ServicioCrearPedidoUnitTest {

	private static final Long ID = 1L;
	private static final String FECHA = "2021-08-20";
	private static final String FECHA_INCORRECTA = "20-0820L";
	private static final String FECHA_DIA_MARTES_PLACAS_TERMINADAS_EN_NUMERO_PAR = "2021-08-17";
	private static final String FECHA_DIA_MARTES_PLACAS_TERMINADAS_EN_NUMERO_IMPAR = "2021-08-18";
	private static final String FECHA_DIA_VIERNES_PRECIO_DOMICILIO_CON_UN_5_PORCIENTO_MAS = "2021-08-20";
	private static final String CODIGO_CLIENTE = "1094911832";
	private static final String CODIGO_PRODUCTO = "0001";
	private static final String DIRECCION_DOMICILIO = "San juan de carolina Calle 123";
	private static final String PLACA = "VKH526";
	private static final String PLACA_TERMINADA_EN_NUMERO_PAR = "VKH526";
	private static final String PLACA_TERMINADA_EN_NUMERO_IMPAR = "VKH57";
	private static final Double PRECIO_TOTAL_COMPRA_20000 = 20.000;
	private static final Double PRECIO_DOMICILIO_MAS_UN_5_PORCIENTO_ESPERADO_2000 = 2.000;

	@Test
	public void crearPedidoTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder().conId(ID).conFecha(FECHA)
				.conCodigoCliente(CODIGO_CLIENTE).conCodigoProducto(CODIGO_PRODUCTO)
				.conDirecionDomicilio(DIRECCION_DOMICILIO).conPlacaVehiculo(PLACA)
				.conPrecioTotalCompra(PRECIO_TOTAL_COMPRA_20000);

		// 2. Act
		Pedido pedido = pedidoTestDataBuilder.build();

		// 3. Assert
		assertEquals(ID, pedido.getId());
		assertEquals(LocalDate.parse(FECHA), pedido.getFecha());
		assertEquals(CODIGO_CLIENTE, pedido.getCodigoCliente());
		assertEquals(CODIGO_PRODUCTO, pedido.getCodigoProducto());
		assertEquals(DIRECCION_DOMICILIO, pedido.getDireccionDomicilio());
		assertEquals(PLACA, pedido.getPlacaVehiculo());
		assertEquals(PRECIO_TOTAL_COMPRA_20000, pedido.getPrecioTotalCompra(), 0.001);
		}

	@Test
	public void crearPedidoConFechaIncorrectaTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conId(ID)
				.conFecha(FECHA_INCORRECTA)
				.conCodigoCliente(CODIGO_CLIENTE)
				.conCodigoProducto(CODIGO_PRODUCTO)
				.conDirecionDomicilio(DIRECCION_DOMICILIO).conPlacaVehiculo(PLACA)
				.conPrecioTotalCompra(PRECIO_TOTAL_COMPRA_20000);
		// 2. Act

		// 3. Assert
		BasePrueba.assertThrows(() -> {
			pedidoTestDataBuilder.build();
		}, ExcepcionValorInvalido.class, "La fecha debe tener el siguiente formato: yyyy-MM-dd.");
	}

	@Test
	public void validarPlacasTerminadasEnNumeroParTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder().conId(ID)
				.conFecha(FECHA_DIA_MARTES_PLACAS_TERMINADAS_EN_NUMERO_PAR).conCodigoCliente(CODIGO_CLIENTE)
				.conCodigoProducto(CODIGO_PRODUCTO).conDirecionDomicilio(DIRECCION_DOMICILIO)
				.conPlacaVehiculo(PLACA_TERMINADA_EN_NUMERO_PAR).conPrecioTotalCompra(PRECIO_TOTAL_COMPRA_20000);

		// 2. Act

		// 3. Assert
		Throwable throwable = assertThrows(ExcepcionValorInvalido.class, pedidoTestDataBuilder::build);
		assertEquals( "El conductor no puede realizar el domicilio porque tiene pico y placa",throwable.getMessage());
	}

	@Test
	public void validarPlacasTerminadasEnNumeroImparTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder().conId(ID)
				.conFecha(FECHA_DIA_MARTES_PLACAS_TERMINADAS_EN_NUMERO_IMPAR).conCodigoCliente(CODIGO_CLIENTE)
				.conCodigoProducto(CODIGO_PRODUCTO).conDirecionDomicilio(DIRECCION_DOMICILIO)
				.conPlacaVehiculo(PLACA_TERMINADA_EN_NUMERO_IMPAR).conPrecioTotalCompra(PRECIO_TOTAL_COMPRA_20000);

		// 2. Act

		// 3. Assert
		Throwable throwable = assertThrows(ExcepcionValorInvalido.class, pedidoTestDataBuilder::build);
		assertEquals("El conductor no puede realizar el domicilio porque tiene pico y placa", throwable.getMessage());
	}

	@Test
	public void calcularPrecioDomicilioTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder().conId(ID)
				.conFecha(FECHA_DIA_VIERNES_PRECIO_DOMICILIO_CON_UN_5_PORCIENTO_MAS).conCodigoCliente(CODIGO_CLIENTE)
				.conCodigoProducto(CODIGO_PRODUCTO).conDirecionDomicilio(DIRECCION_DOMICILIO)
				.conPlacaVehiculo(PLACA_TERMINADA_EN_NUMERO_PAR).conPrecioTotalCompra(PRECIO_TOTAL_COMPRA_20000);

		// 2. Act
		Pedido pedido = pedidoTestDataBuilder.build();

		// 3. Assert
		assertEquals(PRECIO_DOMICILIO_MAS_UN_5_PORCIENTO_ESPERADO_2000, pedido.getPrecioDomicilio(), 0.001);
	}

}
