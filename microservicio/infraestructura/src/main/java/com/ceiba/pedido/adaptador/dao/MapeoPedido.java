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

		Long id = rs.getLong("ID");
		LocalDate fecha = LocalDate.parse(rs.getDate("FECHA").toString());
		String codigoCliente = rs.getString("CODIGO_CLIENTE");
		String codigoProducto = rs.getString("CODIGO_PRODUCTO");
		String direccionDomicilio = rs.getString("DIRECCION_DOMICILIO");
		String placaVehiculo = rs.getString("PLACA_VEHICULO");
		Double precioDomicilio = rs.getDouble("PRECIO_DOMICILIO");
		Double porcentajeDescuento = rs.getDouble("PORCENTAJE_DESCUENTO");
		Double precioCompra = rs.getDouble("PRECIO_COMPRA");
		Double precioTotal = rs.getDouble("PRECIO_TOTAL");

		return new DtoPedido(id, fecha, codigoCliente, codigoProducto, direccionDomicilio, placaVehiculo,
				precioDomicilio, porcentajeDescuento, precioCompra, precioTotal);
	}

}
