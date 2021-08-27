package com.ceiba.pedido.servicio;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.servicio.testdatabuilder.PedidoTestDataBuilder;

public class ServicioCrearPedidoUnitTest {

	private final static Integer NO_APLICA_PROMOCION = 0;
	private final static Integer SI_APLICA_PROMOCION = 1;
	
	@Test
	public void validarFechaNoNulaTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha(null);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class, ()-> pedidoTestDataBuilder.build(), "Fecha incorrecta, debe tener el siguiente formato: yyyy-MM-dd.");
	}
	
	@Test
	public void validarFechaFormatoIncorrectoTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha("20-08-2021");
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorInvalido.class, ()-> pedidoTestDataBuilder.build(), "Fecha incorrecta, debe tener el siguiente formato: yyyy-MM-dd.");
	}
	
	@Test
	public void validarCodigoClienteNoNuloTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conCodigoCliente(null);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class, ()-> pedidoTestDataBuilder.build(), "El código del cliente es obligatorio.");
	}
	
	@Test
	public void validarCodigoClienteNoVacioTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conCodigoCliente("");
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class, ()-> pedidoTestDataBuilder.build(), "El código del cliente es obligatorio.");
	}
	
	
	@Test
	public void validarCodigoProductoNoNuloTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conCodigoProducto(null);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class, ()-> pedidoTestDataBuilder.build(), "El código del producto es obligatorio.");
	}
	
	@Test
	public void validarCodigoProductoNoVacioTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conCodigoProducto("");
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class, ()-> pedidoTestDataBuilder.build(), "El código del producto es obligatorio.");
	}
	
	
	@Test
	public void validarDireccionClienteNoNulaTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conDirecionDomicilio(null);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class, ()-> pedidoTestDataBuilder.build(), "La direción es obligatoria.");
	}
	
	@Test
	public void validarDireccionDomicilioNoVaciaTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conDirecionDomicilio("");
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class, ()-> pedidoTestDataBuilder.build(), "La direción es obligatoria.");
	}
	
	@Test
	public void validarPlacaVehiculoNoNulaTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conPlacaVehiculo(null);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class, ()-> pedidoTestDataBuilder.build(), "La placa del vehiculo es obligatoria y debe terminar en un número entero del 0 al 9.");
	}
	
	@Test
	public void validarPlacaVehiculoNoVaciaTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conPlacaVehiculo("");
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class, ()-> pedidoTestDataBuilder.build(), "La placa del vehiculo es obligatoria y debe terminar en un número entero del 0 al 9.");
	}
	
	@Test
	public void validarPlacaVehiculoFormatoCorrectoTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conPlacaVehiculo("VDF120");
		// 2. Act
		// 3. Assert
		assertDoesNotThrow(()-> pedidoTestDataBuilder.build(), "La placa del vehiculo es obligatoria y debe terminar en un número entero del 0 al 9.");
	}
	
	@Test
	public void validarPlacaVehiculoFormatoIncorrectoTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conPlacaVehiculo("VDF12W");
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorInvalido.class, ()-> pedidoTestDataBuilder.build(), "La placa del vehiculo es obligatoria y debe terminar en un número entero del 0 al 9.");
	}
	
	@Test
	public void validarSiLaPlacaVehiculoTerminadaEnNumeroParNoTengaPicoYPlacaTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha("2021-08-23")
				.conPlacaVehiculo("VKH234");	
		// 2. Act
		// 3. Assert
		assertDoesNotThrow(()-> pedidoTestDataBuilder.build(), "El conductor no puede realizar el domicilio porque tiene pico y placa.");
	}
	
	@Test
	public void validarSiLaPlacaVehiculoTerminadaEnNumeroParTienePicoYPlacaTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha("2021-08-24")
				.conPlacaVehiculo("VKH234");	
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorInvalido.class, ()-> pedidoTestDataBuilder.build(), "El conductor no puede realizar el domicilio porque tiene pico y placa.");
	}
	
	@Test
	public void validarSiLaPlacaVehiculoTerminadaEnNumeroImparNoTengaPicoYPlacaTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha("2021-08-23")
				.conPlacaVehiculo("VKH233");	
		// 2. Act
		// 3. Assert
		assertDoesNotThrow(()-> pedidoTestDataBuilder.build(), "El conductor no puede realizar el domicilio porque tiene pico y placa.");
	}
	
	@Test
	public void validarSiLaPlacaVehiculoTerminadaEnNumeroImparTienePicoYPlacaTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha("2021-08-27")
				.conPlacaVehiculo("VKH233");	
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorInvalido.class, ()-> pedidoTestDataBuilder.build(), "El conductor no puede realizar el domicilio porque tiene pico y placa.");
	}
	
	//
	@Test
	public void validarPorcentajeDescuentoNoNuloTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conPorcentajeDescuento(null);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class, ()-> pedidoTestDataBuilder.build(), "El porcentaje de descuento debe ser un número mayor a 0.0");
	}
	
	@Test
	public void validarPorcentajeDescuentoMenorACeroTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conPorcentajeDescuento(-10.0);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorInvalido.class, ()-> pedidoTestDataBuilder.build(), "El porcentaje de descuento debe ser un número mayor a 0.0");
	}
	
	@Test
	public void validarPrecioCompraNoNuloTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conPrecioCompra(null);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class, ()-> pedidoTestDataBuilder.build(), "El precio de la compra es obligatorio.");
	}
	
	@Test
	public void validarPrecioCompraMenorACeroTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conPrecioCompra(-50.000);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorInvalido.class, ()-> pedidoTestDataBuilder.build(), "El precio de la compra es obligatorio.");
	}
	
	@Test
	public void validarPedidoSinPorcentajeDeDescuentoTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conPorcentajeDescuento(0.0)
				.conPrecioCompra(20.000);
		// 2. Act
		Pedido pedido = pedidoTestDataBuilder.build();
		// 3. Assert
		assertEquals(0.0, pedido.getPorcentajeDescuento().doubleValue());
		assertEquals(20.000, pedido.getPrecioCompra().doubleValue());
	}
	
	@Test
	public void validarPedidoCon10PorcientoDeDescuentoTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conPorcentajeDescuento(10.0)
				.conPrecioCompra(20.000);
		// 2. Act
		Pedido pedido = pedidoTestDataBuilder.build();
		// 3. Assert
		assertEquals(10.0, pedido.getPorcentajeDescuento().doubleValue());
		assertEquals(20.000, pedido.getPrecioCompra().doubleValue());
		assertEquals(18.000, pedido.getPrecioTotal().doubleValue());
	}
	
	@Test
	public void validarPedidoConPrecioNormalDeDomicilioTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha("2021-08-23")
				.conPorcentajeDescuento(0.0)
				.conPrecioCompra(20.000);
		// 2. Act
		Pedido pedido = pedidoTestDataBuilder.build();
		// 3. Assert
		assertEquals(1.000, pedido.getPrecioDomicilio().doubleValue());
	}
	
	@Test
	public void validarPedidoConUnCincoPorcientoMasEnElPrecioDomicilioTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha("2021-08-28")
				.conPrecioCompra(20.000);
		// 2. Act
	Pedido pedido = pedidoTestDataBuilder.build();
		// 3. Assert
		assertEquals(2.000, pedido.getPrecioDomicilio().doubleValue());
	}
	
	@Test
	public void validarAplicaPromocionNoNuloTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha("2021-08-28")
				.conPrecioCompra(20.000)
				.conAplicaPromocion(null);
		// 2. Act
		// 3. Assert
	assertThrows(ExcepcionValorObligatorio.class, ()-> pedidoTestDataBuilder.build(), "Aplica promoción es obligatorio.");
	}
	
	
	@Test
	public void validarNoAplicaPromocionTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha("2021-08-28")
				.conPrecioCompra(50.000)
				.conPorcentajeDescuento(0.0)
				.conAplicaPromocion(NO_APLICA_PROMOCION);
		// 2. Act
	Pedido pedido = pedidoTestDataBuilder.build();
		// 3. Assert
	assertEquals(50.000, pedido.getPrecioTotal().doubleValue());
	}
	
	@Test
	public void validarSiAplicaPromocionTest() {
		// 1. Arrange
		PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
				.conFecha("2021-08-28")
				.conPrecioCompra(50.000)
				.conPorcentajeDescuento(0.0)
				.conAplicaPromocion(SI_APLICA_PROMOCION);
		// 2. Act
	Pedido pedido = pedidoTestDataBuilder.build();
		// 3. Assert
	assertEquals(25.000, pedido.getPrecioTotal().doubleValue());
	}
	
}
