package com.ceiba.descuento.consulta;

import java.util.List;

import org.springframework.stereotype.Component;



import com.ceiba.descuento.modelo.dto.DtoDescuento;
import com.ceiba.pedido.puerto.dao.DaoDescuento;

@Component
public class ManejadorListarDescuento {
	
	private final DaoDescuento daoDescuento;

	public ManejadorListarDescuento(DaoDescuento daoDescuento) {
		this.daoDescuento = daoDescuento;
	}

	public List<DtoDescuento> ejecutar() {
		return this.daoDescuento.listar();
	}
	
}
