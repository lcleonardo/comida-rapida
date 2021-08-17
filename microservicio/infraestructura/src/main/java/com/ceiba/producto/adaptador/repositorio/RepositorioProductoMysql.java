package com.ceiba.producto.adaptador.repositorio;

import org.springframework.stereotype.Repository;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.producto.modelo.entidad.Producto;
import com.ceiba.producto.puerto.repositorio.RepositorioProducto;

@Repository
public class RepositorioProductoMysql implements RepositorioProducto {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	@SqlStatement(namespace = "producto", value = "crear")
	private static String sqlCrear;
	
	@SqlStatement(namespace = "producto", value = "eliminar")
	private static String sqlEliminar;

	public RepositorioProductoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public Long crear(Producto producto) {
		return this.customNamedParameterJdbcTemplate.crear(producto, sqlCrear);
	}

	@Override
	public void eliminar(Long id) {
		 this.customNamedParameterJdbcTemplate.actualizar(id, sqlEliminar);
	}

}
