package com.ceiba.pedido.servicio;

import com.ceiba.dominio.excepcion.ExcepcionAccionNoPermitida;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioEliminarPedido {

    private static final String PARA_ELIMINAR_EL_PEDIDO_DEBE_ELIMINAR_LOS_PEDIDOS_CREADOS_EN_FECHAS_POSTERIORES = "Para eliminar el pedido debe eliminar los pedidos creados en fechas posteriores.";

    private final RepositorioPedido repositorioPedido;

    public ServicioEliminarPedido(RepositorioPedido repositorioPedido) {
        this.repositorioPedido = repositorioPedido;
    }

    public void ejecutar(Long id) {
        validarSiExisteUnPedidoConUnaFechaMayor(id);
        this.repositorioPedido.eliminar(id);
    }

    private void validarSiExisteUnPedidoConUnaFechaMayor(Long id) {
        boolean respuesta = this.repositorioPedido.existeUnPedidoConUnaFechaMayor(id);
        if (respuesta) {
            throw new ExcepcionAccionNoPermitida(PARA_ELIMINAR_EL_PEDIDO_DEBE_ELIMINAR_LOS_PEDIDOS_CREADOS_EN_FECHAS_POSTERIORES);
        }
    }

}
