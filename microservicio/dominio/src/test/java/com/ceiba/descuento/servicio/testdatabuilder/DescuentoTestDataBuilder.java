package com.ceiba.descuento.servicio.testdatabuilder;



import com.ceiba.descuento.modelo.entidad.Descuento;

public class DescuentoTestDataBuilder {
	
	private String fecha;
	private Double porcentaje;
	
	public DescuentoTestDataBuilder() {
		this.fecha = "2021-08-21";
		this.porcentaje = 5.0;
	}
	
	public DescuentoTestDataBuilder conFecha(String fecha) {
		this.fecha = fecha;
	return this;
	}
	
	public DescuentoTestDataBuilder conPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
		return this;
	}
	
	public Descuento build() {
		return Descuento.crear(fecha, porcentaje);
	}
	
}
