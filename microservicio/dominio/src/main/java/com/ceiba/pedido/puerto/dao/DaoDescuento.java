package com.ceiba.pedido.puerto.dao;

import com.ceiba.descuento.modelo.dto.DtoDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;

public interface DaoDescuento {

	public DtoDescuento obtenerPorFecha(String fecha); 

}
