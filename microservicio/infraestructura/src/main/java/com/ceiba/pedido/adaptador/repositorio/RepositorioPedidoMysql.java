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

    @SqlStatement(namespace = "pedido", value = "totalCompraEnEstaSemana")
    private String sqlTotalCompraEnEstaSemana;

    @SqlStatement(namespace = "pedido", value = "aplicaPromocion")
    private static String sqlAplicaPromocion;

    @SqlStatement(namespace = "pedido", value = "buscarPedidoPorIdEnFechaMenor")
    private static String sqlBuscarPedidoPorIdEnFechaMenor;

    @SqlStatement(namespace = "pedido", value = "buscarPedidoPorIdEnFechaMayor")
    private static String sqlBuscarPedidoPorIdEnFechaMayor;

    @SqlStatement(namespace = "pedido", value = "obtenerIdDelPrimerPedidoCreado")
    private static String sqlObtenerIdDelPrimerPedidoCreado;


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

    @Override
    public boolean existeUnPedidoCreadoConUnaFechaMenor(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        try {
            Integer respuesta = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlBuscarPedidoPorIdEnFechaMenor,
                    paramSource, Integer.class);
            return respuesta == 1;
        } catch (EmptyResultDataAccessException e) {
        }
        return false;
    }

    @Override
    public boolean existeUnPedidoCreadoConUnaFechaMayor(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        try {
            Integer respuesta = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlBuscarPedidoPorIdEnFechaMenor,
                    paramSource, Integer.class);
            return respuesta == 1;
        } catch (EmptyResultDataAccessException e) {
        }
        return false;
    }

    @Override
    public Long obtenerIdDelPrimerDescuentoCreado() {
        try {
            Long id = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().
                    queryForObject(sqlObtenerIdDelPrimerPedidoCreado, new MapSqlParameterSource(), Long.class);
            return id;
        } catch (EmptyResultDataAccessException e) {
        }
        return 0L;
    }

}
