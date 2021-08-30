package com.ceiba.pedido.servicio;

import java.time.format.DateTimeFormatter;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioCrearPedido {

	private static final Double DOSCIENTOS_MIL = 200000.0;
	private static final Integer APLICA_PROMOCION = 1;
	private static final Integer NO_APLICA_PROMOCION = 0;

	private RepositorioPedido repositorioPedido;
	private RepositorioDescuento repositorioDescuento;

	public ServicioCrearPedido(RepositorioPedido repositorioPedido, RepositorioDescuento repositorioDescuento) {
		this.repositorioPedido = repositorioPedido;
		this.repositorioDescuento = repositorioDescuento;
	}

	public Long ejecutar(Pedido pedido) {
		Pedido pedidoConPorcentajeYPromocionDescuento = obtenerPedidoConPorcentajeYPromocionDescuento(pedido);
		return this.repositorioPedido.crear(pedidoConPorcentajeYPromocionDescuento);
	}

	public Pedido obtenerPedidoConPorcentajeYPromocionDescuento(Pedido pedido) {
		Double porcentajeDescuento = obtenerPorcentajeDescuento(pedido);	
		Integer aplicaPromocion = obtenerAplicaPromocion(pedido);	
		return Pedido.crear(pedido.getFecha().format(DateTimeFormatter.ISO_DATE), 
					pedido.getCodigoCliente(),
					pedido.getCodigoProducto(),
					pedido.getDireccionDomicilio(),
					pedido.getPlacaVehiculo(),
					porcentajeDescuento,
					pedido.getPrecioCompra(),
					aplicaPromocion);
	}

	private Double obtenerPorcentajeDescuento(Pedido pedido) {
		return this.repositorioDescuento.obtenerPorcentaje(pedido.getFecha());
	}
	
	private Integer obtenerAplicaPromocion(Pedido pedido) {
		boolean aplicaPromocionDeDescuento = this.repositorioPedido.aplicaPromocionDeDescuento(pedido);
		Double totalComprasALaFechaDelPedido = this.repositorioPedido.totalComprasALaFechaDelPedido(pedido);
		return (aplicaPromocionDeDescuento 
				&& totalComprasALaFechaDelPedido > DOSCIENTOS_MIL) 
				? APLICA_PROMOCION : NO_APLICA_PROMOCION;
	}

}
