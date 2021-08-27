package com.ceiba.descuento.puerto.repositorio;

import java.time.LocalDate;

import com.ceiba.descuento.modelo.dto.DtoDescuento;
import com.ceiba.descuento.modelo.entidad.Descuento;

public interface RepositorioDescuento {
	
	public Long crear(Descuento descuento);
	
	public void eliminar(Long id);
	
	public boolean existePorFecha(String fecha);
	
	public Double obtenerPorcentaje(LocalDate fecha);
	
}
