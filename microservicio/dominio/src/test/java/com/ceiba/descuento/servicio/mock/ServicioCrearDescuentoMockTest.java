package com.ceiba.descuento.servicio.mock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ceiba.core.BasePrueba;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.ServicioCrearDescuento;
import com.ceiba.descuento.servicio.testdatabuilder.DescuentoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

@RunWith(MockitoJUnitRunner.class)
public class ServicioCrearDescuentoMockTest {

	@Mock
	private RepositorioDescuento repositorioDescuento;

	@InjectMocks
	private ServicioCrearDescuento servicioCrearDescuento;

	@Test
	public void validarCrearDescuentoTest() {
		// Arrange
		Descuento descuento = new DescuentoTestDataBuilder()
				.conFecha("2021-08-26")
				.conPorcentaje(10.0).build();
		// Act
		// Assert
		Long id = servicioCrearDescuento.ejecutar(descuento);
		Assert.assertNotNull(id);
	}

	@Test
	public void validarYaExisteunDescuentoAsignadoALaFechaTest() {
		// Arrange
		Descuento descuento = new DescuentoTestDataBuilder()
				.conFecha("2021-08-26")
				.conPorcentaje(10.0).build();
		// Act
		Mockito.when(repositorioDescuento.existePorFecha("2021-08-26")).thenReturn(true);
		// Assert
		BasePrueba.assertThrows(() -> servicioCrearDescuento.ejecutar(descuento), ExcepcionDuplicidad.class,
				"Ya existe un descuento asignado a la fecha.");
	}

}
