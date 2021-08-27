package com.ceiba.descuento.consulta;

import org.springframework.stereotype.Component;



import com.ceiba.descuento.modelo.dto.DtoDescuento;
import com.ceiba.pedido.puerto.dao.DaoDescuento;

@Component
public class ManejadorListarDescuento {
	
	private final DaoDescuento daoDescuento;

	public ManejadorListarDescuento(DaoDescuento daoDescuento) {
		this.daoDescuento = daoDescuento;
	}

	public DtoDescuento ejecutar(String fecha) {
		return this.daoDescuento.obtenerPorFecha(fecha);
	}
}
