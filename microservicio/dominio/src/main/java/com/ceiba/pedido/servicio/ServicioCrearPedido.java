package com.ceiba.pedido.servicio;

import java.time.format.DateTimeFormatter;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioCrearPedido {

	private static final String LA_FECHA_NO_PUEDE_SER_MENOR_A_LA_FECHA_ACTUAL = "La fecha de un pedido no puede ser menor a la fecha actual";
	
	private RepositorioPedido repositorioPedido;
	private RepositorioDescuento repositorioDescuento;

	public ServicioCrearPedido(RepositorioPedido repositorioPedido, RepositorioDescuento repositorioDescuento) {
		this.repositorioPedido = repositorioPedido;
		this.repositorioDescuento = repositorioDescuento;
	}

	public Long ejecutar(Pedido pedido) {
		ValidadorArgumento.validarFechaMenorAFechaActual(pedido.getFecha(), LA_FECHA_NO_PUEDE_SER_MENOR_A_LA_FECHA_ACTUAL);
		pedido = calcularPorcentajeYPromocionDeDescuento(pedido);
		return this.repositorioPedido.crear(pedido);
	}

	private Pedido calcularPorcentajeYPromocionDeDescuento(Pedido pedido) {
		Double porcentajeDescuento = this.repositorioDescuento.obtenerPorcentaje(pedido.getFecha());
		Integer aplicarPromocion = this.repositorioPedido.aplicarPromocion(pedido);
		return Pedido.crear(pedido.getFecha().format(DateTimeFormatter.ISO_DATE),
					pedido.getCodigoCliente(),
					pedido.getCodigoProducto(),
					pedido.getDireccionDomicilio(),
					pedido.getPlacaVehiculo(),
					porcentajeDescuento,
					pedido.getPrecioCompra(),
					aplicarPromocion);
	}

}
