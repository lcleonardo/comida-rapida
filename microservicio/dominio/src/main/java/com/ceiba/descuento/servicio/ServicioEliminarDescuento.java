package com.ceiba.descuento.servicio;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.dominio.excepcion.ExcepcionAccionNoPermitida;

public class ServicioEliminarDescuento {

    private static final String NO_SE_PUEDE_ELIMINAR_EL_DESCUENTO_PORQUE_TIENE_PEDIDOS_ASIGNADOS = "No se puede eliminar el descuento porque tiene pedidos asignados.";

    private final RepositorioDescuento repositorioDescuento;

    public ServicioEliminarDescuento(RepositorioDescuento repositorioDescuento) {
        this.repositorioDescuento = repositorioDescuento;
    }

    public void ejecutar(Long id) {
        validarSiElDescuentoTienePedidosAsignados(id);
        this.repositorioDescuento.eliminar(id);
    }

    private void validarSiElDescuentoTienePedidosAsignados(Long id) {
        boolean existe = this.repositorioDescuento.existenPedidosAsignadosAUnDescuento(id);
        if (existe) {
            throw new ExcepcionAccionNoPermitida(NO_SE_PUEDE_ELIMINAR_EL_DESCUENTO_PORQUE_TIENE_PEDIDOS_ASIGNADOS);
        }
    }


}
