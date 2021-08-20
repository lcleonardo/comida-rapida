package com.ceiba.pedido.controlador;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ceiba.ApplicationMock;
import com.ceiba.pedido.comando.ComandoPedido;
import com.ceiba.pedido.servicio.testdatabuilder.ComandoPedidoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorPedido.class)
public class ComandoControladorPedidoTest {

	private final Long ID_EN_BASE_DE_DATOS_DE_PRUEBA = 1L;
	private final String PLACA_TERMINADA_EN_NUMERO_PAR = "VKH242";
	private final String FECHA_PICO_Y_PLACA_PARA_PLACA_TERMINADA_EN_NUMERO_PAR = "2021-08-18";

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mocMvc;

	@Test
	public void crearPedido() throws Exception {

		// arrange
		ComandoPedido pedido = new ComandoPedidoTestDataBuilder().conPlaca(PLACA_TERMINADA_EN_NUMERO_PAR)
				.conFecha(FECHA_PICO_Y_PLACA_PARA_PLACA_TERMINADA_EN_NUMERO_PAR).build();

		// act - assert
		mocMvc.perform(MockMvcRequestBuilders.post("/pedidos").content(objectMapper.writeValueAsString(pedido))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.valor").exists());
	}

	@Test
	public void eliminarPedido() throws Exception {
		// arrange
		// act - assert
		mocMvc.perform(MockMvcRequestBuilders.delete("/pedidos/" + ID_EN_BASE_DE_DATOS_DE_PRUEBA)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
