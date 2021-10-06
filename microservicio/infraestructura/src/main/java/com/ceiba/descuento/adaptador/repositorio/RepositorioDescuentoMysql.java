package com.ceiba.descuento.adaptador.repositorio;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;


@Repository
public class RepositorioDescuentoMysql implements RepositorioDescuento {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "descuento", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "descuento", value = "eliminarPorId")
    private static String sqlEliminarPorId;

    @SqlStatement(namespace = "descuento", value = "obtenerPorcentajePorFecha")
    private static String sqlObtenerPorcentajePorFecha;

    @SqlStatement(namespace = "descuento", value = "exitenPedidosAsignadosAUnDescuento")
    private static String sqlExitenPedidosAsignadosAUnDescuento;


    public RepositorioDescuentoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Descuento descuento) {
        return this.customNamedParameterJdbcTemplate.crear(descuento, sqlCrear);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .update(sqlEliminarPorId, paramSource);
    }

    @Override
    public Double obtenerPorcentajePorFecha(LocalDate fecha) {
        try {
            MapSqlParameterSource paramSource = new MapSqlParameterSource();
            paramSource.addValue("fecha", fecha);
            return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                    .queryForObject(sqlObtenerPorcentajePorFecha, paramSource, Double.class);
        } catch (EmptyResultDataAccessException e) {
            return 0.0;
        }
    }

    @Override
    public boolean existenPedidosAsignadosAUnDescuento(Long id) {
        try {
            MapSqlParameterSource paramSource = new MapSqlParameterSource();
            paramSource.addValue("id", id);
            Integer respuesta = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                    .queryForObject(sqlExitenPedidosAsignadosAUnDescuento, paramSource, Integer.class);
            return respuesta == 1;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
