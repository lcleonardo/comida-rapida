package com.ceiba.pedido.servicio.testdatabuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.pedido.modelo.entidad.Pedido;

public class PedidoTestDataBuilder {

    private String fecha;
    private String codigoCliente;
    private String codigoProducto;
    private String direccionDomicilio;
    private String placaVehiculo;
    private Double precioCompra;
    private Double porcentajeDescuento;
    private Integer aplicaPromocion;

    public PedidoTestDataBuilder() {
        this.fecha = "2021-08-20";
        this.codigoCliente = "1094911832";
        this.codigoProducto = "0001";
        this.direccionDomicilio = "San juan de carolina, calle 123";
        this.placaVehiculo = "VKH526";
        this.precioCompra = 20.000;
        this.aplicaPromocion = 0;
        this.porcentajeDescuento = 0.0;
    }

    public PedidoTestDataBuilder conFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public PedidoTestDataBuilder conFechaDate(LocalDate fecha) {
        conFecha(fecha.toString());
        return this;
    }

    public PedidoTestDataBuilder conCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
        return this;
    }

    public PedidoTestDataBuilder conCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
        return this;
    }

    public PedidoTestDataBuilder conDirecionDomicilio(String direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
        return this;
    }

    public PedidoTestDataBuilder conPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
        return this;
    }

    public PedidoTestDataBuilder conPlacaVehiculoSinPicoYPlaca() {
        this.placaVehiculo = obtenerplacaDelVehiculoSinPicoYPlaca();
        return this;
    }

    private String obtenerplacaDelVehiculoSinPicoYPlaca() {
        Enum<DayOfWeek> diaDeLaSemana = LocalDate.parse(this.fecha).getDayOfWeek();
        if (diaDeLaSemana == DayOfWeek.TUESDAY || diaDeLaSemana == DayOfWeek.THURSDAY) {
            return "VKH123";
        }
        if (diaDeLaSemana == DayOfWeek.WEDNESDAY || diaDeLaSemana == DayOfWeek.FRIDAY) {
            return "HVK234";
        }
        return "KCV123";
    }

    public PedidoTestDataBuilder conPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
        return this;
    }

    public PedidoTestDataBuilder conAplicaPromocion(Integer aplicapPromocion) {
        this.aplicaPromocion = aplicapPromocion;
        return this;
    }

    public PedidoTestDataBuilder conPorcentajeDescuento(Double porcentajeDescuneto) {
        this.porcentajeDescuento = porcentajeDescuneto;
        return this;
    }

    public Pedido build() {
        return Pedido.crear(fecha,
                codigoCliente,
                codigoProducto,
                direccionDomicilio,
                placaVehiculo,
                porcentajeDescuento,
                precioCompra,
                aplicaPromocion);
    }

}
