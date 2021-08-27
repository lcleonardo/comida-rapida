package com.ceiba.pedido.servicio;

import java.time.format.DateTimeFormatter;

import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioCrearPedido {

	private final  Double DOSCIENTOS_MIL = 200.000;
	private final  Double CINCUENTA_PORCIENTO_DE_DESCUENTO = 2.0;
	
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
					pedido.getPrecioCompra() / CINCUENTA_PORCIENTO_DE_DESCUENTO);
		}
		return pedido;
	}
	
	

}
