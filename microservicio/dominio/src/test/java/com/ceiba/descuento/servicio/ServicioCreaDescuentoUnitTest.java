package com.ceiba.descuento.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.servicio.testdatabuilder.DescuentoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;

public class ServicioCreaDescuentoUnitTest {

	@Test
	public void crearDescuentoTest() {
		// 1. Arrange
		DescuentoTestDataBuilder descuentoTestDataBuilder = new DescuentoTestDataBuilder()
				.conFecha("2021-08-25");
		// 2. Act
		Descuento descuento = descuentoTestDataBuilder.
				build();

		// 3. Assert
		assertEquals("2021-08-25", descuento.getFecha().format(DateTimeFormatter.ISO_DATE));
		assertEquals(5.0, descuento.getPorcentaje().doubleValue());
	}

	@Test
	public void crearDescuentoConFechaNulaTest() {
		// 1. Arrange
		DescuentoTestDataBuilder descuentoTestDataBuilder = new DescuentoTestDataBuilder()
				.conFecha(null);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class,
				() -> descuentoTestDataBuilder.build(), "Fecha incorrecta, debe tener el siguiente formato: yyyy-MM-dd.");
	}

	@Test
	public void crearDescuentoConFechaFormatoIncorrectoTest() {
		// 1. Arrange
		DescuentoTestDataBuilder descuentoTestDataBuilder = new DescuentoTestDataBuilder()
				.conFecha("08-25-2021");
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorInvalido.class, 
				() -> descuentoTestDataBuilder.build(), "Fecha incorrecta, debe tener el siguiente formato: yyyy-MM-dd.");
	}

	@Test
	public void crearDescuentoConPorcentajeNuloTest() {
		// 1. Arrange
		DescuentoTestDataBuilder descuentoTestDataBuilder = new DescuentoTestDataBuilder().conPorcentaje(null);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class,
				() -> descuentoTestDataBuilder.build(), "El porcentaje de descuento debe ser un número mayor a 0.0");
	}

	
	@Test
	public void crearDescuentoConPorcentajeMenorACeroTest() {
		// 1. Arrange
		DescuentoTestDataBuilder descuentoTestDataBuilder = new DescuentoTestDataBuilder().conPorcentaje(-10.0);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorInvalido.class,
				() -> descuentoTestDataBuilder.build(), "El porcentaje de descuento debe ser un número mayor a 0.0");
	}


}
