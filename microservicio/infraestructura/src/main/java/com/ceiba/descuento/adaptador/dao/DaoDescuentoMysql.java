package com.ceiba.descuento.adaptador.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.descuento.modelo.dto.DtoDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.pedido.puerto.dao.DaoDescuento;

@Component
public class DaoDescuentoMysql implements DaoDescuento {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	@SqlStatement(namespace = "descuento", value = "buscarPorFecha")
	private static String sqlBuscarPorFecha;
	
	public DaoDescuentoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	
	@Override
	public DtoDescuento obtenerPorFecha(String fecha) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("fecha", fecha);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlBuscarPorFecha,
				paramSource, new MapeoDescuento());
	}






}
