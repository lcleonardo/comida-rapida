package com.ceiba.descuento.controlador;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping
	@ApiOperation("Listar descuentos")
	public List<DtoDescuento> obtenerPorFecha() {
		return this.manejadorListarDescuento.ejecutar();
	}
		
}
