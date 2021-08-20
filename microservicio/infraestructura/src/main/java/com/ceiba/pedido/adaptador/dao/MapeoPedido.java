package com.ceiba.pedido.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pedido.modelo.dto.DtoPedido;

public class MapeoPedido implements RowMapper<DtoPedido>, MapperResult {

	@Override
	public DtoPedido mapRow(ResultSet rs, int rowNum) throws SQLException {

		Long id = rs.getLong("id");
		LocalDate fecha = LocalDate.parse(rs.getDate("fecha").toString());
		String codigoCliente = rs.getString("codigo_cliente");
		String codigoProducto = rs.getString("codigo_producto");
		String direccionDomicilio = rs.getString("direccion_domicilio");
		String placaVehiculo = rs.getString("placa_vehiculo");
		Double precioDomicilio = rs.getDouble("precio_domicilio");
		Double precioTotalCompra = rs.getDouble("precio_total_compra");

		return new DtoPedido(id, fecha, codigoCliente, codigoProducto, direccionDomicilio, placaVehiculo,
				precioDomicilio, precioTotalCompra);
	}

}
