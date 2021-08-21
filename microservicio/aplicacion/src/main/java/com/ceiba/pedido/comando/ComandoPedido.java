
package com.ceiba.pedido.comando;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPedido{

	private Long id;
	private String fecha;
	private String codigoCliente;
	private String codigoProducto;
	private String direccionDomicilio;
	private String placaVehiculo;
	private String precioTotalCompra;
}
