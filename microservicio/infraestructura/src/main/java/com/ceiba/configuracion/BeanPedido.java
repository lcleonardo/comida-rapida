package com.ceiba.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.conductor.puerto.dao.DaoConductor;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.ServicioCrearPedido;
import com.ceiba.producto.puerto.dao.DaoProducto;

@Configuration
public class BeanPedido {

	@Bean
	public ServicioCrearPedido servicioCrearPedido(RepositorioPedido repositorioPedido, DaoProducto daoProducto,
			DaoConductor daoConductor) {
		return new ServicioCrearPedido(repositorioPedido, daoProducto, daoConductor);
	}

}
