package com.ceiba.descuento.controlador;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.descuento.consulta.ConsultaListarDescuento;
import com.ceiba.descuento.modelo.dto.DtoDescuento;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/descuento")
@Api(tags = { "Controlador consulta descuentos" })
public class ConsultaControladorDescuento {


	private ConsultaListarDescuento manejadorListarDescuento;

	public ConsultaControladorDescuento(ConsultaListarDescuento manejadorListarDescuento) {
		this.manejadorListarDescuento = manejadorListarDescuento;
	}

	@GetMapping(value = "/{fecha}")
	@ApiOperation("Listar descuentos")
	public DtoDescuento obtenerPorFecha(@PathVariable String fecha) {
		return this.manejadorListarDescuento.ejecutar(fecha);
	}
	
}
