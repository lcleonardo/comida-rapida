package com.ceiba.descuento.adaptador.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.descuento.modelo.dto.DtoDescuento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.pedido.puerto.dao.DaoDescuento;

@Component
public class DaoDescuentoMysql implements DaoDescuento {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	@SqlStatement(namespace = "descuento", value = "listar")
	private static String sqlListar;

	public DaoDescuentoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public List<DtoDescuento> listar() {
			return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
					.query(sqlListar, new MapeoDescuento());
	}

}
