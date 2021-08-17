package com.ceiba.pedido.comando.fabrica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.ceiba.pedido.comando.ComandoPedido;
import com.ceiba.pedido.modelo.entidad.Pedido;

@Component
public class FabricaPedido {

	public Pedido crear(ComandoPedido comandoPedido) {
		return Pedido.crear(comandoPedido.getId(),
				comandoPedido.getFecha(),
				comandoPedido.getIdProducto(),
				comandoPedido.getIdConductor(),
				comandoPedido.getNombreCompletoCliente(),
				comandoPedido.getDireccionDomicilio(),
				comandoPedido.getPrecioTotalCompra());
	}

}
