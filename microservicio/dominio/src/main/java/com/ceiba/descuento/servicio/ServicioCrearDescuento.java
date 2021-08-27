package com.ceiba.descuento.servicio;

import java.time.format.DateTimeFormatter;

import com.ceiba.descuento.modelo.entidad.Descuento;
import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearDescuento {

	private static final String YA_EXISTE_UN_DESCUENTO_ASIGNADO_A_LA_FECHA = "Ya existe un descuento asignado a la fecha.";

	private RepositorioDescuento repositorioDescuento;

	public ServicioCrearDescuento(RepositorioDescuento repositorioDescuento) {
		this.repositorioDescuento = repositorioDescuento;
	}

	public Long ejecutar(Descuento descuento) {
		validarSiExisteDescuento(descuento);
		return this.repositorioDescuento.crear(descuento);
	}

	private void validarSiExisteDescuento(Descuento descuento) {
		String fecha = descuento.getFecha().format(DateTimeFormatter.ISO_DATE);
		boolean respuesta = this.repositorioDescuento.existePorFecha(fecha); 
		if (respuesta) {
			throw new ExcepcionDuplicidad(YA_EXISTE_UN_DESCUENTO_ASIGNADO_A_LA_FECHA);
		}
	}

}
