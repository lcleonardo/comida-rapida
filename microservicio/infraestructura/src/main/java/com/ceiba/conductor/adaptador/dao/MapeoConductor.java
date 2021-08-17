package com.ceiba.conductor.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.conductor.modelo.dto.DtoConductor;
import com.ceiba.infraestructura.jdbc.MapperResult;

public class MapeoConductor implements RowMapper<DtoConductor>, MapperResult {

	@Override
	public DtoConductor mapRow(ResultSet rs, int rowNum) throws SQLException {

		Long id = rs.getLong("id");
		String nombre = rs.getString("nombre");
		String placa = rs.getString("placa");

		return new DtoConductor(id, nombre, placa);
	}

}
