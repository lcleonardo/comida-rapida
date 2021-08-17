package com.ceiba.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.ServicioCrearPedido;

@Configuration
public class BeanPedido {

	@Bean
	public ServicioCrearPedido crear(RepositorioPedido repositorioPedido) {
		return new ServicioCrearPedido(repositorioPedido);
	}
}
