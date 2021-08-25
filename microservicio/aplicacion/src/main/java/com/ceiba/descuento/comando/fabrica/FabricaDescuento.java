package com.ceiba.descuento.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.descuento.comando.ComandoDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;

@Component
public class FabricaDescuento {

	public Descuento crear(ComandoDescuento comando ) {
		return Descuento.crear(comando.getFecha(), comando.getPorcentaje());
	}
}
