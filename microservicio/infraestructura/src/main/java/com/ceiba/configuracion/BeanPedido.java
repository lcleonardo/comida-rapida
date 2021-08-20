package com.ceiba.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.pedido.puerto.dao.DaoPedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.ServicioCrearPedido;
import com.ceiba.pedido.servicio.ServicioEliminarPedido;

@Configuration
public class BeanPedido {

	@Bean
	public ServicioCrearPedido servicioCrearPedido(RepositorioPedido repositorioPedido) {
		return new ServicioCrearPedido(repositorioPedido);
	}

	@Bean
	public ServicioEliminarPedido servicioEliminarPedido(RepositorioPedido repositorioPedido, DaoPedido daoPedido) {
		return new ServicioEliminarPedido(repositorioPedido, daoPedido);
	}

}
