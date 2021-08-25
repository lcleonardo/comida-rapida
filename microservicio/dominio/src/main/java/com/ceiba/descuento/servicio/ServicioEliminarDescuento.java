package com.ceiba.descuento.servicio;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;

public class ServicioEliminarDescuento {

	private final RepositorioDescuento repositorioDescuento;

	public ServicioEliminarDescuento(RepositorioDescuento repositorioDescuento) {
		this.repositorioDescuento = repositorioDescuento;
	}
	
	public void ejecutar(Long id) {
		 this.repositorioDescuento.eliminar(id);
	}

	
}
