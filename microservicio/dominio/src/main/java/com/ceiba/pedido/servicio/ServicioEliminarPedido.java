package com.ceiba.pedido.servicio;

import com.ceiba.dominio.excepcion.ExcepcionAccionNoPermitida;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioEliminarPedido {

    private static final String PARA_ELIMINAR_EL_PEDIDO_DEBE_ELIMINAR_LOS_PEDIDOS_CREADOS_EN_FECHAS_MENORES = "Para eliminar el pedido debe eliminar los pedidos creados en fechas anteriores.";
    private static final String PARA_ELIMINAR_EL_PEDIDO_DEBE_ELIMINAR_LOS_PEDIDOS_CREADOS_EN_FECHAS_MAYORES = "Para eliminar el pedido debe eliminar los pedidos creados en fechas posteriores.";

    private final RepositorioPedido repositorioPedido;

    public ServicioEliminarPedido(RepositorioPedido repositorioPedido) {
        this.repositorioPedido = repositorioPedido;
    }

    public void ejecutar(Long id) {
        validarSiSePuedeEliminarElPedido(id);
        this.repositorioPedido.eliminar(id);
    }

    private void validarSiSePuedeEliminarElPedido(Long id) {
        Long idDelPrimerDescuentoCreado = this.repositorioPedido.obtenerIdDelPrimerDescuentoCreado();
        if (idDelPrimerDescuentoCreado.equals(id)) {
            validarSiExisteUnPedidoCreadoConUnaFechaMayor(id);
        } else {
            validarSiExisteUnPedidoCreadoConUnaFechaMenor(id);
        }
    }

    private void validarSiExisteUnPedidoCreadoConUnaFechaMayor(Long id) {
        boolean respuesta = this.repositorioPedido.existeUnPedidoCreadoConUnaFechaMayor(id);
        if (respuesta) {
            throw new ExcepcionAccionNoPermitida(PARA_ELIMINAR_EL_PEDIDO_DEBE_ELIMINAR_LOS_PEDIDOS_CREADOS_EN_FECHAS_MAYORES);
        }

    }

    private void validarSiExisteUnPedidoCreadoConUnaFechaMenor(Long id) {
        boolean respuesta = this.repositorioPedido.existeUnPedidoCreadoConUnaFechaMenor(id);
        if (respuesta) {
            throw new ExcepcionAccionNoPermitida(PARA_ELIMINAR_EL_PEDIDO_DEBE_ELIMINAR_LOS_PEDIDOS_CREADOS_EN_FECHAS_MENORES);
        }
    }

}
