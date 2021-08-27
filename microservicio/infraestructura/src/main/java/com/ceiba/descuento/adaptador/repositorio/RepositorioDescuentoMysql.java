package com.ceiba.descuento.adaptador.repositorio;


import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Repository
public class RepositorioDescuentoMysql implements RepositorioDescuento {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	@SqlStatement(namespace = "descuento", value = "crear")
	private static String sqlCrear;

	@SqlStatement(namespace = "descuento", value = "eliminar")
	private static String sqlEliminar;

	@SqlStatement(namespace = "descuento", value = "existePorFecha")
	private static String sqlExistePorFecha;

	
	
	public RepositorioDescuentoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public Long crear(Descuento descuento) {
		return this.customNamedParameterJdbcTemplate.crear(descuento, sqlCrear);
	}
	
	@Override
	public boolean existePorFecha(String fecha) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("fecha", fecha);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorFecha, paramSource,
				Boolean.class);
	}
	
	@Override
	public void eliminar(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
	}

}
