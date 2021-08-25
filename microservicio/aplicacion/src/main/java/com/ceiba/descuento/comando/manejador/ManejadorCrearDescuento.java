package com.ceiba.descuento.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;
import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.comando.fabrica.FabricaDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.servicio.ServicioCrearDescuento;
import com.ceiba.manejador.ManejadorComandoRespuesta;

@Component
public class ManejadorCrearDescuento implements ManejadorComandoRespuesta<ComandoDescuento, ComandoRespuesta<Long>>{

	private final FabricaDescuento fabricaDescuento;
	private final ServicioCrearDescuento servicioCrearDescuento;
	
	public ManejadorCrearDescuento(FabricaDescuento fabricaDescuento, ServicioCrearDescuento servicioCrearDescuento) {
		this.fabricaDescuento = fabricaDescuento;
		this.servicioCrearDescuento = servicioCrearDescuento;
	}

	@Override
	public ComandoRespuesta<Long> ejecutar(ComandoDescuento comando) {
		Descuento descuento = this.fabricaDescuento.crear(comando);
		return new ComandoRespuesta<>(this.servicioCrearDescuento.ejecutar(descuento));
	}

}
