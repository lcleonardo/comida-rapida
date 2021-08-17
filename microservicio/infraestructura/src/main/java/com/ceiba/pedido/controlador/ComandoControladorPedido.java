package com.ceiba.pedido.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.pedido.comando.ComandoPedido;
import com.ceiba.pedido.comando.manejador.ManejadorCrearPedido;
import com.ceiba.usuario.comando.ComandoUsuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pedidos")
@Api(tags = { "Controlador comando pedido" })
public class ComandoControladorPedido {

	private final ManejadorCrearPedido manejadorCrearPedido;

	@Autowired
	public ComandoControladorPedido(ManejadorCrearPedido manejadorCrearPedido) {
		this.manejadorCrearPedido = manejadorCrearPedido;
	}

	@PostMapping
	@ApiOperation("Crear pedido")
	public ComandoRespuesta<Long> crear(@RequestBody ComandoPedido comandoPedido) {
		return manejadorCrearPedido.ejecutar(comandoPedido);
	}

}
