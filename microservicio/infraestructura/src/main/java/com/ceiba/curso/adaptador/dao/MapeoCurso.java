package com.ceiba.curso.adaptador.dao;

import com.ceiba.curso.modelo.dto.DtoCurso;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoCurso implements RowMapper<DtoCurso>, MapperResult {

    @Override
    public DtoCurso mapRow(ResultSet rs, int rowNum) throws SQLException {
        String nombre = rs.getString("NOMBRE");
        Double duracion = rs.getDouble("DURACION");
        return new DtoCurso(nombre, duracion);
    }

}
