package com.ceiba.pedido.adaptador.repositorio;

import org.springframework.dao.EmptyResultDataAccessException;
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

    @SqlStatement(namespace = "pedido", value = "totalComprasALaFechaDelPedido")
    private String sqlTotalComprasALaFechaDelPedido;

    @SqlStatement(namespace = "pedido", value = "obtenerPorcentajePromocion")
    private static String sqlObtenerPorcentajePromocion;

    @SqlStatement(namespace = "pedido", value = "existeUnPedidoConUnaFechaMayor")
    private static String sqlExisteUnPedidoConUnaFechaMayor;

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
    public Double obtenerPorcentajePromocion(Pedido pedido) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigoCliente", pedido.getCodigoCliente());
        paramSource.addValue("fechaDesde", pedido.getFecha().minusDays(7));
        paramSource.addValue("fechaHasta", pedido.getFecha());
        try {
            return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                    .queryForObject(sqlObtenerPorcentajePromocion, paramSource,
                            Double.class) == 0.0 ? 50.0 : 0.0;
        } catch (EmptyResultDataAccessException | NullPointerException e) {
            return 0.0;
        }
    }

    @Override
    public Double totalComprasALaFechaDelPedido(Pedido pedido) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigoCliente", pedido.getCodigoCliente());
        paramSource.addValue("fechaDesde", pedido.getFecha().minusDays(7));
        paramSource.addValue("fechaHasta", pedido.getFecha());
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlTotalComprasALaFechaDelPedido,
                paramSource, Double.class);
    }

    @Override
    public boolean existeUnPedidoConUnaFechaMayor(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        try {
            Integer respuesta = this.customNamedParameterJdbcTemplate
                    .getNamedParameterJdbcTemplate().queryForObject(sqlExisteUnPedidoConUnaFechaMayor,
                            paramSource, Integer.class);
            return respuesta == 1;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }


}
