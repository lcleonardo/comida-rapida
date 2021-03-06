package com.ceiba.configuracion;

import com.ceiba.curso.puerto.repositorio.RepositorioCurso;
import com.ceiba.curso.servicio.ServicioCrearCurso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.descuento.servicio.ServicioCrearDescuento;
import com.ceiba.descuento.servicio.ServicioEliminarDescuento;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.ServicioCrearPedido;
import com.ceiba.pedido.servicio.ServicioEliminarPedido;

@Configuration
public class BeanConfiguracion {


    @Bean
    public ServicioCrearCurso servicioCrearCurso(RepositorioCurso repositorioCurso) {
        return new ServicioCrearCurso(repositorioCurso);
    }

    @Bean
    public ServicioCrearPedido servicioCrearPedido(RepositorioPedido repositorioPedido, RepositorioDescuento repositorioDescuento) {
        return new ServicioCrearPedido(repositorioPedido, repositorioDescuento);
    }

    @Bean
    public ServicioEliminarPedido servicioEliminarPedido(RepositorioPedido repositorioPedido) {
        return new ServicioEliminarPedido(repositorioPedido);
    }

    @Bean
    public ServicioCrearDescuento servicioCrearDescuento(RepositorioDescuento repositorioDescuento) {
        return new ServicioCrearDescuento(repositorioDescuento);
    }


    @Bean
    public ServicioEliminarDescuento servicioEliminarDescuento(RepositorioDescuento repositorioDescuento) {
        return new ServicioEliminarDescuento(repositorioDescuento);
    }

}
