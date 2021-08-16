package com.ceiba.producto.modelo.entidad;

import java.util.Arrays;

import com.ceiba.dominio.ValidadorArgumento;

import lombok.Getter;

@Getter
public class Producto {

	private static final String EL_NOMBRE_ES_OBLIGATORIO = "El nombre es obligatorio";
	private static final String EL_PRECIO_ES_OBLIGATORIO = "El precio es obligatorio";
	
	private Long id;
	private String codigo;
	private String nombre;
	private Double precio;

	public static Producto crear(String nombre, Double precio) {
		//TODO: Validar codigo debe ser unico y obligatoio
		ValidadorArgumento.validarNoVacio(Arrays.asList(nombre), EL_NOMBRE_ES_OBLIGATORIO);
		ValidadorArgumento.validarPositivo(precio, EL_PRECIO_ES_OBLIGATORIO);
		return new Producto(nombre, precio);
	}

	private Producto(String nombre, Double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

}
