package com.ceiba.producto.puerto.dao;

import java.util.List;

import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.modelo.entidad.Producto;

public interface DaoProducto {
	
	//public List<Producto> listar();
	
	public DtoProducto buscarPorId(Long id);
	
}
