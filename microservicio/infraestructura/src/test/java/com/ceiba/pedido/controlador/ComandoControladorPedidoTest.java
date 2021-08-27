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
	
	private final Long ID = 1L;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mocMvc;

	@Test
	public void crearPedidoTest() throws Exception {
			
		// arrange
		ComandoPedido pedido = new ComandoPedidoTestDataBuilder()
				.conFecha("2021-09-13")
				.conCodigoCliente("1094")
				.conCodigoProducto("1000")
				.conDireccionDomicilio("Vereda san juan de carolina.")
				.conPlacaVehiculo("VKH525")
				.conPrecioCompra(100.000)
				.build();

		// act - assert
		mocMvc.perform(MockMvcRequestBuilders.post("/pedidos")
				.content(objectMapper.writeValueAsString(pedido))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.valor").exists());
	}

	@Test
	public void eliminarPedidoTest() throws Exception {
		// arrange
		// act - assert
		mocMvc.perform(MockMvcRequestBuilders
				.delete("/pedidos/" + ID)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
