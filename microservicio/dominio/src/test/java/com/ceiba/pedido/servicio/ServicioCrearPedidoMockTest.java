package com.ceiba.pedido.servicio;


import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ceiba.descuento.puerto.repositorio.RepositorioDescuento;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.testdatabuilder.PedidoTestDataBuilder;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServicioCrearPedidoMockTest {

    private final static int UN_DIA = 1;
    private final static int SIETE_DIAS = 7;

    private ArgumentCaptor<Pedido> capturadorDePedido;
    private RepositorioDescuento repositorioDescuento;
    private RepositorioPedido repositorioPedido;
    private ServicioCrearPedido servicioCrearPedido;

    @Before
    public void before() {
        capturadorDePedido = ArgumentCaptor.forClass(Pedido.class);
        repositorioDescuento = Mockito.mock(RepositorioDescuento.class);
        repositorioPedido = Mockito.mock(RepositorioPedido.class);
        servicioCrearPedido = new ServicioCrearPedido(repositorioPedido, repositorioDescuento);
    }

    @Test
    public void deberiaCrearUnPedidoConPorcentajeDeDescuento() {
        Pedido primerPedido = new PedidoTestDataBuilder()
                .conFechaTipoLocalDate(LocalDate.now())
                .conCodigoCliente("1094")
                .conCodigoProducto("0001")
                .conPlacaVehiculoSinPicoYPlaca()
                .conPrecioCompra(500.000)
                .build();
        // Act
        when(repositorioDescuento.obtenerPorcentajePorFecha(primerPedido.getFecha())).thenReturn(10.0);
        when(repositorioPedido.crear(capturadorDePedido.capture())).thenReturn(1L);

        when(servicioCrearPedido.ejecutar(primerPedido)).thenReturn(1L);
        Pedido pedidoConPromocion = capturadorDePedido.getValue();
        //Assert
        Assert.assertEquals(450.000, pedidoConPromocion.getPrecioTotal(), 0.0);
    }

    @Test
    public void deberiaCrearUnPedidoConPorcentajeDePromocion() {
        Pedido primerPedido = new PedidoTestDataBuilder()
                .conFechaTipoLocalDate(LocalDate.now())
                .conCodigoCliente("1094")
                .conCodigoProducto("0001")
                .conPlacaVehiculoSinPicoYPlaca()
                .conPrecioCompra(500.000)
                .build();
        // Act
        when(repositorioPedido.obtenerPorcentajePromocion(primerPedido)).thenReturn(50.0);
        when(repositorioPedido.crear(capturadorDePedido.capture())).thenReturn(1L);

        when(servicioCrearPedido.ejecutar(primerPedido)).thenReturn(1L);
        Pedido pedidoConPromocion = capturadorDePedido.getValue();
        //Assert
        Assert.assertEquals(250.000, pedidoConPromocion.getPrecioTotal(), 0.0);
    }

}






