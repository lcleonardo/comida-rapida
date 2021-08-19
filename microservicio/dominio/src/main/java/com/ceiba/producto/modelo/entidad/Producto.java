package com.ceiba.producto.modelo.entidad;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import com.ceiba.dominio.ValidadorArgumento;

import lombok.Getter;

@Getter
public class Producto {

	public static final String NOMBRE_OBLIGATORIO = "El nombre del producto es obligatorio.";
	public static final String DETALLE_OBLIGATORIO = "El detalle del producto es obligatorio.";
	public static final String PRECIO_OBLIGATORIO = "El precio del producto es obligatorio.";
	public static final String FECHA_DOS_POR_UNO_OBLIGATORIO = "La fecha dos por uno es obligatoria.";
	
	private Long id;
	private String nombre;
	private String detalle;
	private Double precio;
	private LocalDateTime fechaDosPorUNo;

	public Producto crear(String nombre, String detalle , Double valor, LocalDateTime fechaDosPorUno){
		ValidadorArgumento.validarNoVacio(Arrays.asList(nombre), NOMBRE_OBLIGATORIO);
		ValidadorArgumento.validarNoVacio(Arrays.asList(detalle), DETALLE_OBLIGATORIO);
		ValidadorArgumento.validarPositivo(valor, PRECIO_OBLIGATORIO);
		ValidadorArgumento.validarObligatorio(fechaDosPorUno, FECHA_DOS_POR_UNO_OBLIGATORIO);
		return new Producto(0L,nombre, detalle, valor, fechaDosPorUno);
	}
	
	private Producto(Long id,String nombre, String detalle, Double valor, LocalDateTime fechaDosPorUNo) {
		this.id = id;
		this.nombre = nombre;
		this.detalle = detalle;
		this.precio = valor;
		this.fechaDosPorUNo = fechaDosPorUNo;
	}

}
