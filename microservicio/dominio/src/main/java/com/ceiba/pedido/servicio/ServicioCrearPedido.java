package com.ceiba.pedido.servicio;

import com.ceiba.conductor.modelo.dto.DtoConductor;
import com.ceiba.conductor.modelo.entidad.Conductor;
import com.ceiba.conductor.puerto.dao.DaoConductor;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.modelo.entidad.Producto;
import com.ceiba.producto.puerto.dao.DaoProducto;
import com.ceiba.producto.puerto.repositorio.RepositorioProducto;

public class ServicioCrearPedido {

	/*
	 * 1. Los dueños de las motos que hacen domicilios no pueden trabajar el día de
	 * pico y placa. Ejemplo: No circulan placas terminadas en: martes numero par,
	 * miércoles numero impar, jueves numero par, viernes numero impar, sábados,
	 * domingos y lunes no aplica.
	 */

	private final String NO_EXISTE_EL_PRODUCTO_CON_EL_ID = "No existe el producto con el id: ";
	private final String NO_EXISTE_EL_CONDUCTOR_CON_EL_ID = "No existe el conductor con el id: ";

	private RepositorioPedido repositorioPedido;
	private DaoProducto daoProducto;
	private DaoConductor daoConductor;

	public ServicioCrearPedido(RepositorioPedido repositorioPedido, DaoProducto daoProducto,
			DaoConductor daoConductor) {
		this.repositorioPedido = repositorioPedido;
		this.daoProducto = daoProducto;
		this.daoConductor = daoConductor;
	}

	public Long ejecutar(Pedido pedido) {
		DtoConductor conductor = this.daoConductor.buscarPorId(pedido.getIdConductor());
		validarConductor(pedido, conductor);
		// 1. Regla de negocio
		Conductor.validarSiTienePicoYPlaca(pedido.getFecha(), conductor.getPlaca());

		DtoProducto producto = this.daoProducto.buscarPorId(pedido.getIdProducto());
		validarExisteProducto(pedido, producto);

		return this.repositorioPedido.crear(pedido);
	}

	private void validarConductor(Pedido pedido, DtoConductor conductor) {
		if (!conductor.existe()) {
			throw new ExcepcionValorInvalido(NO_EXISTE_EL_CONDUCTOR_CON_EL_ID + pedido.getIdConductor());
		}
	}

	private void validarExisteProducto(Pedido pedido, DtoProducto producto) {
		if (!producto.existe()) {
			throw new ExcepcionValorInvalido(NO_EXISTE_EL_PRODUCTO_CON_EL_ID + pedido.getIdProducto());
		}
	}

}
