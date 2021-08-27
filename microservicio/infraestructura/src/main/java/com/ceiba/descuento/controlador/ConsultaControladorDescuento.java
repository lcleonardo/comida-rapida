package com.ceiba.descuento.controlador;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.descuento.consulta.ManejadorListarDescuento;
import com.ceiba.descuento.modelo.dto.DtoDescuento;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/descuentos")
@Api(tags = { "Controlador consulta descuentos" })
public class ConsultaControladorDescuento {


	private ManejadorListarDescuento manejadorListarDescuento;

	public ConsultaControladorDescuento(ManejadorListarDescuento manejadorListarDescuento) {
		this.manejadorListarDescuento = manejadorListarDescuento;
	}

	@GetMapping(value = "/{fecha}")
	@ApiOperation("Listar descuento por fecha")
	public DtoDescuento obtenerPorFecha(@PathVariable String fecha) {
		return this.manejadorListarDescuento.ejecutar(fecha);
	}
	
	
	
}
