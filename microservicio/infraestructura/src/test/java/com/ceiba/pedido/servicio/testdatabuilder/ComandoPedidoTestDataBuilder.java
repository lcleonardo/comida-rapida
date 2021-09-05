package com.ceiba.pedido.servicio.testdatabuilder;

import com.ceiba.pedido.comando.ComandoPedido;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ComandoPedidoTestDataBuilder {

    private Long id;
    private String fecha;
    private String codigoCliente;
    private String codigoProducto;
    private String direccionDomicilio;
    private String placaVehiculo;
    private Double precioCompra;

    public ComandoPedidoTestDataBuilder() {
        this.id = 1L;
        this.fecha = "2021-08-20";
        this.codigoCliente = "1094911832";
        this.codigoProducto = "0001";
        this.direccionDomicilio = "Vereda san juan, Casa la chiquita";
        this.placaVehiculo = "VKH526";
        this.precioCompra = 20.000;
    }

    public ComandoPedidoTestDataBuilder conFecha(LocalDate fecha) {
        this.fecha = fecha.toString();
        return this;
    }

    public ComandoPedidoTestDataBuilder conPlacaVehiculoSinPicoYPlaca() {
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


    public ComandoPedidoTestDataBuilder conCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
        return this;
    }

    public ComandoPedidoTestDataBuilder conCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
        return this;
    }

    public ComandoPedidoTestDataBuilder conDireccionDomicilio(String direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
        return this;
    }

    public ComandoPedidoTestDataBuilder conPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
        return this;
    }

    public ComandoPedidoTestDataBuilder conPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
        return this;
    }

    public ComandoPedido build() {
        return new ComandoPedido(id,
                fecha,
                codigoCliente,
                codigoProducto,
                direccionDomicilio,
                placaVehiculo,
                precioCompra);
    }

}
