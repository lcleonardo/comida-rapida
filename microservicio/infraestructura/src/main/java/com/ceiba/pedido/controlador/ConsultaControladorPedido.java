package com.ceiba.pedido.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.pedido.consulta.ManejadorListarPedido;
import com.ceiba.pedido.modelo.dto.DtoPedido;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pedidos")
@Api(tags = { "Controlador consulta pedido" })
public class ConsultaControladorPedido {

	private ManejadorListarPedido manejadorListarPedidos;

	public ConsultaControladorPedido(ManejadorListarPedido manejadorListarPedidos) {
		this.manejadorListarPedidos = manejadorListarPedidos;
	}

	@GetMapping
	@ApiOperation("Listar Pedidos")
	public List<DtoPedido> listar() {
		return this.manejadorListarPedidos.ejecutar();
	}

}


