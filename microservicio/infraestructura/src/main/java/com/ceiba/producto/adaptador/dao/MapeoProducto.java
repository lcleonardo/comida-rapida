package com.ceiba.producto.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.producto.modelo.dto.DtoProducto;

public class MapeoProducto implements RowMapper<DtoProducto>, MapperResult {

	@Override
	public DtoProducto mapRow(ResultSet rs, int rowNum) throws SQLException {

		Long id = rs.getLong("id");
		String codigo = rs.getString("codigo");
		String nombre = rs.getString("nombre");
		Double precio = rs.getDouble("precio");

		return new DtoProducto(id, codigo, nombre, precio);
	}

}
