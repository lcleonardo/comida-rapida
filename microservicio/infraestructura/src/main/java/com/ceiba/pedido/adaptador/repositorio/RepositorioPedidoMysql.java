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

    @SqlStatement(namespace = "pedido", value = "aplicaPromocion")
    private static String sqlAplicaPromocion;


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
    public Integer aplicarPromocion(Pedido pedido) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigoCliente", pedido.getCodigoCliente());
        paramSource.addValue("fechaDesde", pedido.getFecha().minusDays(7));
        paramSource.addValue("fechaHasta", pedido.getFecha());
        Integer puedeRecibirLaPromocion = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlAplicaPromocion, paramSource,
                        Integer.class);
        Integer cero = 0;
        Double doscientosmil = 200.000;
        Integer noAplicaPromocion = 0;
        Integer siAplicaPromocion = 1;
        Double totalComprasALaFecha = totalComprasALaFechaDelPedido(pedido);
        return puedeRecibirLaPromocion == cero && (totalComprasALaFecha > doscientosmil) ? siAplicaPromocion : noAplicaPromocion;
    }


    @Override
    public Double totalComprasALaFechaDelPedido(Pedido pedido) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigoCliente", pedido.getCodigoCliente());
        paramSource.addValue("fechaDesde", pedido.getFecha().minusDays(7));
        paramSource.addValue("fechaHasta", pedido.getFecha());
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlTotalCompraEnEstaSemana,
                paramSource, Double.class);

    }

}
