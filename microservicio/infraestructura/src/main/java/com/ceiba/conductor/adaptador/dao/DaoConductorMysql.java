package com.ceiba.conductor.adaptador.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.conductor.modelo.dto.DtoConductor;
import com.ceiba.conductor.puerto.dao.DaoConductor;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Component
public class DaoConductorMysql implements DaoConductor {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	public DaoConductorMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@SqlStatement(namespace = "conductor", value = "buscarPorId")
	private static String sqlBuscarPorId;

	@Override
	public DtoConductor buscarPorId(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		DtoConductor conductor = null;
		try {
			conductor = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
					.queryForObject(sqlBuscarPorId, paramSource, new MapeoConductor());
		} catch (Exception e) {
			conductor = new DtoConductor(0L, "", "");
		}
		return conductor;
	}

}
