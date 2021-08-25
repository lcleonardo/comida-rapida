package com.ceiba.descuento.servicio;

import java.time.format.DateTimeFormatter;

import com.ceiba.descuento.modelo.dto.DtoDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pedido.puerto.dao.DaoDescuento;

public class ServicioCrearDescuento {

	private final String YA_EXISTE_UN_DESCUENTO_ASIGNADO_A_LA_FECHA="Ya existe un descuento asignado a la fecha.";
	
	private RepositorioDescuento repositorioDescuento;
	private DaoDescuento daoDescuento;

	public ServicioCrearDescuento(RepositorioDescuento repositorioDescuento, DaoDescuento daoDescuento) {
		this.repositorioDescuento = repositorioDescuento;
		this.daoDescuento = daoDescuento;
	}
	
	public Long ejecutar(Descuento descuento) {
		validarSiExisteDescuento(descuento);
		return this.repositorioDescuento.crear(descuento);
	}
	
	private void validarSiExisteDescuento(Descuento descuento){
		String fecha = descuento.getFecha().format(DateTimeFormatter.ISO_DATE);
		if(daoDescuento.obtenerPorFecha(fecha) != null) {
			 throw new ExcepcionDuplicidad(YA_EXISTE_UN_DESCUENTO_ASIGNADO_A_LA_FECHA);
		}
	}

	
}
