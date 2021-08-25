package com.ceiba.descuento.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.descuento.servicio.ServicioEliminarDescuento;
import com.ceiba.manejador.ManejadorComando;

@Component
public class ManejadorEliminarDescuento implements ManejadorComando<Long>{

	private final ServicioEliminarDescuento servicioEliminarDescuento;

	public ManejadorEliminarDescuento(ServicioEliminarDescuento servicioEliminarDescuento) {
		this.servicioEliminarDescuento = servicioEliminarDescuento;
	}

	@Override
	public void ejecutar(Long id) {
		this.servicioEliminarDescuento.ejecutar(id);
	}
	
	
	
	
}
