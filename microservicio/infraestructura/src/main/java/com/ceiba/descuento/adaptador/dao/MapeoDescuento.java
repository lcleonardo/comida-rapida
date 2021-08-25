package com.ceiba.descuento.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.descuento.modelo.dto.DtoDescuento;
import com.ceiba.infraestructura.jdbc.MapperResult;

public class MapeoDescuento implements RowMapper<DtoDescuento>, MapperResult {

	@Override
	public DtoDescuento mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("ID");
		LocalDate fecha = LocalDate.parse(rs.getDate("FECHA").toString());
		Double porcentaje= rs.getDouble("PORCENTAJE");
		return new DtoDescuento(id, fecha, porcentaje);
	}

}
