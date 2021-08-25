package com.ceiba.descuento.servicio;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;

public class ServicioCrearDescuento {

	private RepositorioDescuento repositorioDescuento;

	public ServicioCrearDescuento(RepositorioDescuento repositorioDescuento) {
		this.repositorioDescuento = repositorioDescuento;
	}
	
	public Long ejecutar(Descuento descuento) {
		return this.repositorioDescuento.crear(descuento);
	}

	
}
