package com.ceiba.descuento.controlador;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.comando.manejador.ManejadorCrearDescuento;
import com.ceiba.descuento.comando.manejador.ManejadorEliminarDescuento;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/descuento")
@Api(tags = { "Controlador comando descuento" })
public class ComandoControladorDescuento {
	
	private ManejadorCrearDescuento manejadorCrearDescuento;
	private ManejadorEliminarDescuento manejadorEliminarDescuento;
	
	public ComandoControladorDescuento(ManejadorCrearDescuento manejadorCrearDescuento,
			ManejadorEliminarDescuento manejadorEliminarDescuento) {
		this.manejadorCrearDescuento = manejadorCrearDescuento;
		this.manejadorEliminarDescuento = manejadorEliminarDescuento;
	}
	

	@PostMapping
	@ApiOperation("Crear Descuento")
	public ComandoRespuesta<Long> crear(@RequestBody ComandoDescuento comandoDescuento) {
		return manejadorCrearDescuento.ejecutar(comandoDescuento);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation("Eliminar Descuento")
	public void eliminar(@PathVariable Long id) {
		this.manejadorEliminarDescuento.ejecutar(id);
	}
}













