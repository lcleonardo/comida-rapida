package com.ceiba.descuento.servicio.mock;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.ServicioEliminarDescuento;

@RunWith(MockitoJUnitRunner.class)
public class ServicioEliminarDescuentoMockTest {
	
	private final Long ID = 1L;
	
	@Mock
	private RepositorioDescuento repositorioDescuento;
	
	@InjectMocks
	private ServicioEliminarDescuento servicioEliminarDescuento;
	
	@Test 
	public void validarEliminarDescuento() {
		Assertions.assertDoesNotThrow(()->this.servicioEliminarDescuento.ejecutar(ID));
	}
	
	

}
