package com.ceiba.pedido.servicio;

import java.time.format.DateTimeFormatter;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioCrearPedido {

    private RepositorioPedido repositorioPedido;
    private RepositorioDescuento repositorioDescuento;

    public ServicioCrearPedido(RepositorioPedido repositorioPedido, RepositorioDescuento repositorioDescuento) {
        this.repositorioPedido = repositorioPedido;
        this.repositorioDescuento = repositorioDescuento;
    }

    public Long ejecutar(Pedido pedido) {
        pedido = calcularPorcentajes(pedido);
        return this.repositorioPedido.crear(pedido);
    }

    private Pedido calcularPorcentajes(Pedido pedido) {
        Double porcentajeDescuento = this.repositorioDescuento.obtenerPorcentajePorFecha(pedido.getFecha());
        Double porcentajePromocion = this.repositorioPedido.obtenerPorcentajePromocion(pedido);
        return Pedido.crear(pedido.getFecha().format(DateTimeFormatter.ISO_DATE),
                pedido.getCodigoCliente(),
                pedido.getCodigoProducto(),
                pedido.getDireccionDomicilio(),
                pedido.getPlacaVehiculo(),
                pedido.getPrecioCompra(),
                porcentajeDescuento,
                porcentajePromocion
        );
    }

}
