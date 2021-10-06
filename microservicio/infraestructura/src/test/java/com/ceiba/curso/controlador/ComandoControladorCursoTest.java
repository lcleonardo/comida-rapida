package com.ceiba.curso.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.curso.comando.ComandoCurso;
import com.ceiba.curso.modelo.dto.DtoCurso;
import com.ceiba.curso.puerto.dao.DaoCurso;
import com.ceiba.curso.servicio.testdatabuilder.ComandoCursoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
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
                .andDo(resultado -> {
                    DtoCurso curso1 = daoCurso.obtenerPorId(1L);
                    Assert.assertEquals("DDD en java", curso1.getNombre());
                    Assert.assertEquals(1000.0, curso1.getDuracion(), 0.0);
                });
    }

}















