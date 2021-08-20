package com.ceiba.pedido.controlador;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.pedido.comando.ComandoPedido;
import com.ceiba.pedido.comando.manejador.ManejadorCrearPedido;
import com.ceiba.pedido.comando.manejador.ManejadorEliminarPedido;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pedidos")
@Api(tags = { "Controlador comando pedido" })
public class ComandoControladorPedido {

	private final ManejadorCrearPedido manejadorCrearPedido;
	private final ManejadorEliminarPedido manejadorEliminarPedido;

	public ComandoControladorPedido(ManejadorCrearPedido manejadorCrearPedido,
			ManejadorEliminarPedido manejadorEliminarPedido) {
		this.manejadorCrearPedido = manejadorCrearPedido;
		this.manejadorEliminarPedido = manejadorEliminarPedido;
	}

	@PostMapping
	@ApiOperation("Crear Pedido")
	public ComandoRespuesta<Long> crear(@RequestBody ComandoPedido comandoPedido) {
		return manejadorCrearPedido.ejecutar(comandoPedido);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation("Eliminar Pedido")
	public void eliminar(@PathVariable Long id) {
		this.manejadorEliminarPedido.ejecutar(id);
	}

}
