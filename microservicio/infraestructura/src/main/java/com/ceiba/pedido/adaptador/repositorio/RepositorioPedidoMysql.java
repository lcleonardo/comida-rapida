package com.ceiba.pedido.adaptador.repositorio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

@Repository
public class RepositorioPedidoMysql implements RepositorioPedido {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	public RepositorioPedidoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@SqlStatement(namespace = "pedido", value = "crear")
	private String sqlCrear;

	@SqlStatement(namespace = "pedido", value = "eliminarPorId")
	private String sqlEliminarPorId;
	
	@SqlStatement(namespace = "pedido", value = "totalCompraEnEstaSemana")
	private String sqlTotalCompraEnEstaSemana;
	
	@SqlStatement(namespace = "pedido", value = "ultimaFechaPromocion")
	private static String sqlUltimaFechaPromocion;



	@Override
	public Long crear(Pedido pedido) {
		return this.customNamedParameterJdbcTemplate.crear(pedido, sqlCrear);
	}

	@Override
	public void eliminar(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarPorId, paramSource);
	}
	
	@Override
	public String ultimaFechaPromocion(String codigoCliente) {
		try {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("codigoCliente", codigoCliente);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlUltimaFechaPromocion, paramSource,
				String.class);	
		} catch (Exception e) {
			return null;
		}
	}
	
	
	@Override
	public Double totalCompraEnEstaSemana(String codigoCliente) {
		LocalDate fechaActual = LocalDate.now();
		String ultimaFecha = ultimaFechaPromocion(codigoCliente);
		if(ultimaFecha != null) {
			fechaActual = LocalDate.parse(ultimaFecha);
		}
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("codigoCliente", codigoCliente);
		paramSource.addValue("fechaDesde", fechaActual.minusDays(7).format(DateTimeFormatter.ISO_DATE));
		paramSource.addValue("fechaHasta", fechaActual.format(DateTimeFormatter.ISO_DATE));
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlTotalCompraEnEstaSemana,
				paramSource, Double.class);
		
	}

}
