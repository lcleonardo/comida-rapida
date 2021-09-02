package com.ceiba.descuento.controlador;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ceiba.ApplicationMock;
import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.servicio.testdatabuilder.ComandoDescuentoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorDescuento.class)
public class ComandoControladorDescuentoTest {
	
	private static final Long ID_BD = 1L;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mocMvc;
	

	@Test
	public void crearDescuento() throws Exception {
		// arrange
		ComandoDescuento comandoDescuento = new ComandoDescuentoTestDataBuilder()
				.conFecha(LocalDate.now().toString())
				.conPorcentaje(5.0)	
				.build();

		// act - assert
		mocMvc.perform(MockMvcRequestBuilders.post("/descuentos")
				.content(objectMapper.writeValueAsString(comandoDescuento))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.valor").exists());
	}

	@Test
	public void eliminarDescuento() throws Exception {
		// arrange
		// act - assert
		mocMvc.perform(MockMvcRequestBuilders.delete("/descuentos/" + ID_BD)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
}





