package com.ceiba.pedido.modelo.entidad;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ceiba.dominio.ValidadorArgumento;

import lombok.Getter;

@Getter
public class CarroCompra {

	private static final String LA_FECHA_ES_OBLIGATORIA = "La fecha es obligatoria.";
	private static final String PARA_REALIZAR_LA_COMPRA_DEBE_SELECCIONAR_AL_MENOS_UN_PRODUCTO = "Para realizar la compra debe seleccionar al menos un producto.";
	private static final String NOMBRE_OBLIGATORIO = "El nombre es obligatorio.";
	private static final String DIRECCION_OBLIGATORIA = "La direción es obligatoria.";

	private Long id;
	private LocalDateTime fecha;
	private List<Producto> productos;
	private Conductor conductor;
	private String nombreCompletoCliente;
	private String direccionDomicilio;
	private Double precioCompra;
	private Double precioDomicilio;
	private Double precioTotalCompra;
	private boolean pedidoEntregado;

	public static CarroCompra crear(Long id, LocalDateTime fecha, List<Producto> productos, Conductor conductor,
			String nombreCompletoCliente, String direccionDomicilio) {
		
		ValidadorArgumento.validarObligatorio(fecha, LA_FECHA_ES_OBLIGATORIA);
		ValidadorArgumento.validarNoVacio(productos, PARA_REALIZAR_LA_COMPRA_DEBE_SELECCIONAR_AL_MENOS_UN_PRODUCTO);
		ValidadorArgumento.validarNoVacio(Arrays.asList(nombreCompletoCliente), NOMBRE_OBLIGATORIO);
		ValidadorArgumento.validarNoVacio(Arrays.asList(direccionDomicilio), DIRECCION_OBLIGATORIA);

		Double precioCompra = calcularTotalPrecioCompra(productos);
		Double precioDomicilio = conductor.calcularPrecioDomicilio(fecha, precioCompra);
		Double totalPrecioCompra = precioCompra - precioDomicilio;

		return new CarroCompra(id, fecha, productos, conductor, nombreCompletoCliente, direccionDomicilio, precioCompra,
				precioDomicilio, totalPrecioCompra, false);
	}

	public CarroCompra(Long id, LocalDateTime fecha, List<Producto> productos, Conductor conductor,
			String nombreCompletoCliente, String direccionDomicilio, Double precioCompra, Double precioDomicilio,
			Double precioTotalCompra, boolean pedidoEntregado) {
		this.id = id;
		this.fecha = fecha;
		this.productos = productos;
		this.conductor = conductor;
		this.nombreCompletoCliente = nombreCompletoCliente;
		this.direccionDomicilio = direccionDomicilio;
		this.precioCompra = precioCompra;
		this.precioDomicilio = precioDomicilio;
		this.precioTotalCompra = precioTotalCompra;
		this.pedidoEntregado = pedidoEntregado;
	}

	public Double calcularPrecioGananciaConductor(Double totalPrecioCompra) {
		return this.conductor.calcularPrecioDomicilio(this.fecha, totalPrecioCompra);
	}

	public static Double calcularTotalPrecioCompra(List<Producto> productos) {
		Double total = 0.0;
		for (Producto producto : productos) {
			total += producto.getPrecio();
		}
		return total;
	}

}
