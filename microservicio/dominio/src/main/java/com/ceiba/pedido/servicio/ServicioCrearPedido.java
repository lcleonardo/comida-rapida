package com.ceiba.pedido.servicio;

import java.time.format.DateTimeFormatter;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioCrearPedido {

	private final static Double DOSCIENTOS_MIL = 200000.0;
	private final static Integer APLICA_PROMOCION = 1;
	private final static Integer NO_APLICA_PROMOCION = 0;

	private RepositorioPedido repositorioPedido;
	private RepositorioDescuento repositorioDescuento;

	public ServicioCrearPedido(RepositorioPedido repositorioPedido, RepositorioDescuento repositorioDescuento) {
		this.repositorioPedido = repositorioPedido;
		this.repositorioDescuento = repositorioDescuento;
	}

	public Long ejecutar(Pedido pedido) {
		Pedido pedidoVerificado = verificarTotalCompra(pedido);
		return this.repositorioPedido.crear(pedidoVerificado);
	}

	public Pedido verificarTotalCompra(Pedido pedido) {
			return Pedido.crear(pedido.getFecha().format(DateTimeFormatter.ISO_DATE), 
					pedido.getCodigoCliente(),
					pedido.getCodigoProducto(),
					pedido.getDireccionDomicilio(),
					pedido.getPlacaVehiculo(),
					obtenerPorcentajeDescuento(pedido),
					pedido.getPrecioCompra(),
					obtenerAplicaPromocion(pedido));
	}

	private Double obtenerPorcentajeDescuento(Pedido pedido) {
		return this.repositorioDescuento.obtenerPorcentaje(pedido.getFecha());
	}
	
	private Integer obtenerAplicaPromocion(Pedido pedido) {
		boolean aplicaPromocion = this.repositorioPedido.aplicaPromocion(pedido);
		Double totalComprasSemanaActual = this.repositorioPedido.totalComprasSemanaActual(pedido);
		boolean respuesta = aplicaPromocion && totalComprasSemanaActual > DOSCIENTOS_MIL;
		return respuesta ? APLICA_PROMOCION : NO_APLICA_PROMOCION;
	}

}
