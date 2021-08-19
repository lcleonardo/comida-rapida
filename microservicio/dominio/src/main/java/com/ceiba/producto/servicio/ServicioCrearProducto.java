package com.ceiba.producto.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.producto.modelo.entidad.Producto;
import com.ceiba.producto.puerto.repositorio.RepositorioProducto;

public class ServicioCrearProducto {

	private static String EL_PRODUCTO_YA_EXISTE_EN_EL_SISTEMA = "El producto ya existe en elsistema.";
	
	private final RepositorioProducto repositorioProducto;

	public ServicioCrearProducto(RepositorioProducto repositorioProducto) {
		this.repositorioProducto = repositorioProducto;
	}
	
	public void ejecutar(Producto producto) {
		validarExisteProducto(producto);
		this.repositorioProducto.crear(producto);
	}
	
	private void validarExisteProducto(Producto producto) {
		boolean existe =this.repositorioProducto.existe(producto.getNombre());
		if(existe) {
			throw new ExcepcionDuplicidad(EL_PRODUCTO_YA_EXISTE_EN_EL_SISTEMA);
		}
	}
	
	
}
