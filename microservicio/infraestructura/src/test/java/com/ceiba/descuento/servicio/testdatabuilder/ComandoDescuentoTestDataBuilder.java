package com.ceiba.descuento.servicio.testdatabuilder;

import com.ceiba.descuento.comando.ComandoDescuento;

public class ComandoDescuentoTestDataBuilder {

	private Long id;
	private String fecha;
	private Double porcentaje;
	
	public ComandoDescuentoTestDataBuilder() {
		this.id = 0L;
		this.fecha = "2021-08-26";
		this.porcentaje = 0.0;
	}
	
	public ComandoDescuentoTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public ComandoDescuentoTestDataBuilder conFecha(String fecha) {
		this.fecha = fecha;
		return this;
	}
	
	public ComandoDescuentoTestDataBuilder conPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
		return this;
	}
	
	public ComandoDescuento build() {
		return new ComandoDescuento(this.id, this.fecha, this.porcentaje);
	}
}



