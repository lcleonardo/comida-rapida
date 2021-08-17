package com.ceiba.pedido.modelo.entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ceiba.conductor.modelo.entidad.Conductor;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.producto.modelo.entidad.Producto;

import lombok.Getter;

@Getter
public class Pedido {

	/*
	 * 2. La ganancia de los conductores es 5% sobre el valor total del pedido. Los
	 * días viernes y sábados los conductores ganan otro 5 % más sobre el valor
	 * total del domicilio.
	 * 
	 */

	private static final String LA_FECHA_ES_OBLIGATORIA = "La fecha es obligatoria.";
	private static final String PARA_REALIZAR_LA_COMPRA_DEBE_SELECCIONAR_AL_MENOS_UN_PRODUCTO = "Para realizar la compra debe seleccionar al menos un producto.";
	private static final String NOMBRE_OBLIGATORIO = "El nombre es obligatorio.";
	private static final String DIRECCION_OBLIGATORIA = "La direción es obligatoria.";

	private Long id;
	private LocalDate fecha;
	private Long idProducto;
	private Long idConductor;
	private String nombreCompletoCliente;
	private String direccionDomicilio;
	private Double precioDomicilio;
	private Double precioTotalCompra;

	public static Pedido crear(Long id, LocalDate fecha, Long idProducto, Long idConductor,
			String nombreCompletoCliente, String direccionDomicilio, Double precioTotalCompra) {

		ValidadorArgumento.validarObligatorio(fecha, LA_FECHA_ES_OBLIGATORIA);
		ValidadorArgumento.validarNoVacio(Arrays.asList(nombreCompletoCliente), NOMBRE_OBLIGATORIO);
		ValidadorArgumento.validarNoVacio(Arrays.asList(direccionDomicilio), DIRECCION_OBLIGATORIA);
		
		//2. Regla de negocio
		Double precioDomicilio = Conductor.calcularPrecioDomicilio(fecha, precioTotalCompra);
				
		return new Pedido(id, fecha, idProducto, idConductor, nombreCompletoCliente, direccionDomicilio,
				precioDomicilio, precioTotalCompra);
	}

	private Pedido(Long id, LocalDate fecha, Long idProducto, Long idConductor, String nombreCompletoCliente,
			String direccionDomicilio, Double precioDomicilio, Double precioTotalCompra) {
		this.id = id;
		this.fecha = fecha;
		this.idProducto = idProducto;
		this.idConductor = idConductor;
		this.nombreCompletoCliente = nombreCompletoCliente;
		this.direccionDomicilio = direccionDomicilio;
		this.precioDomicilio = precioDomicilio;
		this.precioTotalCompra = precioTotalCompra;
	}

}
