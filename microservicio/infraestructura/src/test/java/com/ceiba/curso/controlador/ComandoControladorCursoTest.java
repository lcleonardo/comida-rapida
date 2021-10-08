package com.ceiba.curso.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.curso.comando.ComandoCurso;
import com.ceiba.curso.modelo.dto.DtoCurso;
import com.ceiba.curso.puerto.dao.DaoCurso;
import com.ceiba.curso.servicio.testdatabuilder.ComandoCursoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorCurso.class)
public class ComandoControladorCursoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private DaoCurso daoCurso;

    @Autowired
    protected Flyway flyway;

    @Before
    public void init() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void deberiaCrearUnCurso() throws Exception {
        //Arr
        ComandoCurso comandoCurso = new ComandoCursoTestDataBuilder().
                conNombre("DDD en java")
                .conDuracion(1000.0)
                .build();
        //Act
        //Assert
        mocMvc.perform(MockMvcRequestBuilders.post(
                                        "/cursos"
                                ).content(objectMapper.writeValueAsString(comandoCurso))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk()
                ).andExpect(content().json("{'valor': 1}"))
                .andDo(result -> {
                    DtoCurso curso = daoCurso.obtenerPorId(1L);
                    Assert.assertEquals(comandoCurso.getNombre(), curso.getNombre());
                    Assert.assertEquals(comandoCurso.getDuracion(), curso.getDuracion(), 0.0);
                });
    }

    @Test
    public void noSePuedeCrearUnCursoConElMismoNombre() throws Exception {
        //Arr
        ComandoCurso comandoCurso = new ComandoCursoTestDataBuilder().
                conNombre("DDD en java")
                .conDuracion(1000.0)
                .build();
        //Act
        mocMvc.perform(MockMvcRequestBuilders.post(
                                "/cursos"
                        ).content(objectMapper.writeValueAsString(comandoCurso))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()
        ).andExpect(content().json("{'valor': 1}"));
        //Assert
        mocMvc.perform(MockMvcRequestBuilders.post(
                                "/cursos"
                        ).content(objectMapper.writeValueAsString(comandoCurso))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()
        ).andExpect(content().json("{'nombreExcepcion':'ExcepcionDuplicidad', 'mensaje':'El curso ya existe en el sistema.'}"));

    }

}















