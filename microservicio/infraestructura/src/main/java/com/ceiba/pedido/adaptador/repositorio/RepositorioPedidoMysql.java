package com.ceiba.pedido.adaptador.repositorio;

import java.time.LocalDate;
import java.time.temporal.TemporalAmount;

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
	public Double totalCompraEnEstaSemana(String codigoCliente) {
		try {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("codigoCliente", codigoCliente);
		paramSource.addValue("fechaDesde", LocalDate.now().minusDays(7));
		paramSource.addValue("fechaHasta", LocalDate.now());
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlTotalCompraEnEstaSemana,
				paramSource, Double.class);
		} catch (Exception e) {
			
		}
		return 0.0;
	}

}
