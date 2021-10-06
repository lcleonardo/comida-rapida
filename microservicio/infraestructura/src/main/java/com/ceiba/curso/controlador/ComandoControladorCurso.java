package com.ceiba.curso.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.curso.comando.ComandoCurso;
import com.ceiba.curso.comando.manejador.ManejadorCrearCurso;
import com.ceiba.descuento.comando.ComandoDescuento;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
@Api(tags = {"Controlador comando curso"})
public class ComandoControladorCurso {

    private ManejadorCrearCurso manejadorCrearCurso;

    public ComandoControladorCurso(ManejadorCrearCurso manejadorCrearCurso) {
        this.manejadorCrearCurso = manejadorCrearCurso;
    }

    @PostMapping
    @ApiOperation("Crear curso")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoCurso comandoCurso) {
        return this.manejadorCrearCurso.ejecutar(comandoCurso);
    }
}
















