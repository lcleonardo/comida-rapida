package com.ceiba.pedido.servicio;

import java.time.format.DateTimeFormatter;

import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioCrearPedido {

	private final static Double DOSCIENTOS_MIL = 200.000;
	private final static Integer APLICA_PROMOCION  = 1;
	
	private RepositorioPedido repositorioPedido;

	public ServicioCrearPedido(RepositorioPedido repositorioPedido) {
		this.repositorioPedido = repositorioPedido;
	}

	public Long ejecutar(Pedido pedido) {
		Pedido pedidoVerificado = verificarTotalCompraEstaSemana(pedido);
		return this.repositorioPedido.crear(pedidoVerificado);
	}
	
	public Pedido verificarTotalCompraEstaSemana(Pedido pedido){
		Double totalCompraEnEstaSemana = this.repositorioPedido.totalCompraEnEstaSemana(pedido.getCodigoCliente());
		if(totalCompraEnEstaSemana > DOSCIENTOS_MIL) {
			return Pedido.crear(pedido.getFecha().format(DateTimeFormatter.ISO_DATE),
					pedido.getCodigoCliente(),
					pedido.getCodigoProducto(),
					pedido.getDireccionDomicilio(),
					pedido.getPlacaVehiculo(),
					pedido.getPorcentajeDescuento(),
					pedido.getPrecioCompra(),
					APLICA_PROMOCION);
		}
		return pedido;
	}
	
	

}
