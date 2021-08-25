package com.ceiba.descuento.modelo.entidad;

import java.time.LocalDate;

import com.ceiba.dominio.ValidadorArgumento;

import lombok.Getter;

@Getter
public class Descuento {

	private static final String FECHA_INCORRECTA = "Fecha incorrecta, debe tener el siguiente formato: yyyy-MM-dd.";
	private static final String PORCENTAJE_DE_DESCUENTO_NO_VALIDO = "El porcentaje de descuento debe ser un número mayor a 0.0";
	
	
	private Long id;
	private LocalDate fecha;
	private Double porcentaje;
	
	public static Descuento crear(String fecha , Double porcentaje) {
		
		ValidadorArgumento.validarFechaFormatoYYYMMDD(fecha, FECHA_INCORRECTA);
//		ValidadorArgumento.validarObligatorio(descuento, PORCENTAJE_DE_DESCUENTO_NO_VALIDO);
//		ValidadorArgumento.validarPositivo(descuento, PORCENTAJE_DE_DESCUENTO_NO_VALIDO);
		
		LocalDate fechaValida = LocalDate.parse(fecha);
		
		return new Descuento(fechaValida, porcentaje);
	}

	
	
	private Descuento(LocalDate fecha, Double porcentaje) {
		this.id = 0L;
		this.fecha = fecha;
		this.porcentaje = porcentaje;
	}
	
	
	
}
