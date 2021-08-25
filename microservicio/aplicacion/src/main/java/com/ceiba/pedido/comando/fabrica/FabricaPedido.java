package com.ceiba.pedido.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.pedido.comando.ComandoPedido;
import com.ceiba.pedido.modelo.entidad.Pedido;

@Component
public class FabricaPedido {

	public Pedido crear(ComandoPedido comando) {
		return Pedido.crear(comando.getFecha(), 
				comando.getCodigoCliente(),
				comando.getCodigoProducto(),
				comando.getDireccionDomicilio(),
				comando.getPlacaVehiculo(),
				comando.getPorcentajeDescuento(),
				comando.getPrecioCompra());
	}

}
