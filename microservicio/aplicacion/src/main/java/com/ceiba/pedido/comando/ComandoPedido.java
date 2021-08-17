
package com.ceiba.pedido.comando;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	private Long idProducto;
	private Long idConductor;
	private String nombreCompletoCliente;
	private String direccionDomicilio;
	private Double precioTotalCompra;


}
