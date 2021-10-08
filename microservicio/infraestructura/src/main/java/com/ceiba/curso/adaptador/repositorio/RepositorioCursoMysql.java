package com.ceiba.curso.adaptador.repositorio;

import com.ceiba.curso.modelo.entidad.Curso;
import com.ceiba.curso.puerto.repositorio.RepositorioCurso;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioCursoMysql implements RepositorioCurso {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;


    @SqlStatement(namespace = "curso", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "curso", value = "existePorNombre")
    private static String sqlExistePorNombre;

    public RepositorioCursoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Curso curso) {
        return this.customNamedParameterJdbcTemplate.crear(curso, sqlCrear);
    }

    @Override
    public Boolean existePorNombre(String nombre) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombre", nombre);
        try {
            return this.customNamedParameterJdbcTemplate
                    .getNamedParameterJdbcTemplate().queryForObject(sqlExistePorNombre, paramSource, Boolean.class);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}














