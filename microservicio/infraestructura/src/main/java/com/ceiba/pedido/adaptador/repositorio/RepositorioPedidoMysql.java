package com.ceiba.pedido.adaptador.repositorio;

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

}
