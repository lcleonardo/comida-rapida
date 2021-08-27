package com.ceiba.descuento.controlador;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ceiba.ApplicationMock;
import com.ceiba.descuento.consulta.ManejadorListarDescuento;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ManejadorListarDescuento.class)
public class ConsultaControladorDescuentoTest {
	
	private final String FECHA = "2021-08-26";
	
	@Autowired
	private MockMvc mocMvc;

	@Test
	public void listarDescuentoPorFecha() throws Exception {
		//arrange act - assert
		mocMvc.perform(MockMvcRequestBuilders
				.get("/descuentos/"+FECHA)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}





