package com.ceiba.descuento.puerto.repositorio;

import java.time.LocalDate;

import com.ceiba.descuento.modelo.entidad.Descuento;

public interface RepositorioDescuento {
	
	public Long crear(Descuento descuento);
	
	public void eliminar(Long id);
	
	public Double obtenerPorcentajePorFecha(LocalDate fecha);

	public boolean existenPedidosAsignadosAUnDescuento(Long id);
}
