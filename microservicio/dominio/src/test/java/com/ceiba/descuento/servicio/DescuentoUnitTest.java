package com.ceiba.descuento.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.Test;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.servicio.testdatabuilder.DescuentoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;

public class DescuentoUnitTest {

	@Test
	public void deberiaCrearUnDescuento() {
		// 1. Arrange
		DescuentoTestDataBuilder descuentoTestDataBuilder = new DescuentoTestDataBuilder()
				.conFecha(LocalDate.now().toString());
		// 2. Act
		Descuento descuento = descuentoTestDataBuilder.
				build();

		// 3. Assert
		assertEquals(LocalDate.now(), descuento.getFecha());
		assertEquals(5.0, descuento.getPorcentaje().doubleValue());
	}

	@Test
	public void deberiaValidarQueLaFechaNoPuedeSerNull() {
		// 1. Arrange
		DescuentoTestDataBuilder descuentoTestDataBuilder = new DescuentoTestDataBuilder()
				.conFecha(null);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class,
				() -> descuentoTestDataBuilder.build(), "Fecha incorrecta, debe tener el siguiente formato: yyyy-MM-dd.");
	}

	@Test
	public void deberiaValidarQueLaFechaNoTieneElFormatoCorrecto() {
		// 1. Arrange
		DescuentoTestDataBuilder descuentoTestDataBuilder = new DescuentoTestDataBuilder()
				.conFecha("08-25-2021");
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorInvalido.class, 
				() -> descuentoTestDataBuilder.build(), "Fecha incorrecta, debe tener el siguiente formato: yyyy-MM-dd.");
	}
	
	@Test
	public void deberiaValidarQueNoSePuedeCrearUnDescuentoConFechaMenorALaActual() {
		// 1. Arrange
		DescuentoTestDataBuilder descuentoTestDataBuilder = new DescuentoTestDataBuilder()
				.conFecha(LocalDate.now().minusDays(1).toString());
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorInvalido.class, 
				() -> descuentoTestDataBuilder.build(), "La fecha de un descuento no puede ser menor a la fecha actual");
	}

	@Test
	public void deberiaValidarQueElPorcentajeNoPuedeSerNull() {
		// 1. Arrange
		DescuentoTestDataBuilder descuentoTestDataBuilder = new DescuentoTestDataBuilder().conPorcentaje(null);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorObligatorio.class,
				() -> descuentoTestDataBuilder.build(), "El porcentaje de descuento debe ser un número mayor a 0.0");
	}

	
	@Test
	public void deberiaValidarQueElPorcentajeNoPuedeSerCero() {
		// 1. Arrange
		DescuentoTestDataBuilder descuentoTestDataBuilder = new DescuentoTestDataBuilder()
				.conPorcentaje(0.0);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorInvalido.class,
				() -> descuentoTestDataBuilder.build(), "El porcentaje de descuento debe ser un número mayor a 0.0");
	}
	
	@Test
	public void deberiaValidarQueElPorcentajeNoPuedeSerMenorAcero() {
		// 1. Arrange
		DescuentoTestDataBuilder descuentoTestDataBuilder = new DescuentoTestDataBuilder()
				.conPorcentaje(-10.0);
		// 2. Act
		// 3. Assert
		assertThrows(ExcepcionValorInvalido.class,
				() -> descuentoTestDataBuilder.build(), "El porcentaje de descuento debe ser un número mayor a 0.0");
	}


}
