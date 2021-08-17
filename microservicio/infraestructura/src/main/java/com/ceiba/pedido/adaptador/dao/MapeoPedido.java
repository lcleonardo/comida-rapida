package com.ceiba.pedido.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pedido.modelo.dto.DtoPedido;
import com.ceiba.usuario.modelo.dto.DtoUsuario;

public class MapeoPedido implements RowMapper<DtoPedido>, MapperResult {

	@Override
	public DtoPedido mapRow(ResultSet rs, int rowNum) throws SQLException {

		Long id = rs.getLong("");
		Timestamp timestamp = new Timestamp(rs.getDate("fecha").getTime());
		LocalDateTime fecha = timestamp.toLocalDateTime();
		Long idProducto = rs.getLong("id_producto");
		Long idConductor = rs.getLong("id_conductor");
		String nombreCompletoCliente = rs.getString("nombre_completo_cliente");
		String direcionDomicilio = rs.getString("direccion_domicilio");
		Double precioDomicilio = rs.getDouble("precio_domicilio");
		Double precioTotalCompra = rs.getDouble("precio_total_compra");

		return new DtoPedido(id, fecha, idProducto, idConductor, nombreCompletoCliente, direcionDomicilio,
				precioDomicilio, precioTotalCompra);
	}

}
