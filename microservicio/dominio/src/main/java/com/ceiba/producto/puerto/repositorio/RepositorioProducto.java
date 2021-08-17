package com.ceiba.producto.puerto.repositorio;

import com.ceiba.producto.modelo.entidad.Producto;

public interface RepositorioProducto {

	public Long crear(Producto producto);
	public void eliminar(Long id);
	
}
