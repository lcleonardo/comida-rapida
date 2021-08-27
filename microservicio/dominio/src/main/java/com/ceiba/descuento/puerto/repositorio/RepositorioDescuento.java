package com.ceiba.descuento.puerto.repositorio;

import com.ceiba.descuento.modelo.entidad.Descuento;

public interface RepositorioDescuento {
	
	public Long crear(Descuento descuento);
	
	public void eliminar(Long id);
	
	public boolean existePorFecha(String fecha);
	
	
}
