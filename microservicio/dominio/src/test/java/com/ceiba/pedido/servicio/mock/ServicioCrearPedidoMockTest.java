package com.ceiba.pedido.servicio.mock;


import java.time.LocalDate;

import com.ceiba.core.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
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
import com.ceiba.pedido.servicio.ServicioCrearPedido;
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
    public void deberiaCrearUnPedidoConPromocionDeDescuentoTest() {
        Pedido primerPedido = new PedidoTestDataBuilder()
                .conFechaDate(LocalDate.now())
                .conCodigoCliente("1094")
                .conCodigoProducto("0001")
                .conPlacaVehiculoSinPicoYPlaca()
                .conPrecioCompra(500.000)
                .build();
        // Act
        when(repositorioPedido.aplicarPromocion(primerPedido)).thenReturn(1);
        when(repositorioPedido.crear(capturadorDePedido.capture())).thenReturn(1L);

        when(servicioCrearPedido.ejecutar(primerPedido)).thenReturn(1L);
        Pedido pedidoConPromocion = capturadorDePedido.getValue();
        //Assert
        Assert.assertEquals(250.000, pedidoConPromocion.getPrecioTotal(), 0.0);
    }

    @Test
    public void noDeberiaCrearUnPedidoPorqueLaFechaEsMenorALaFechaActualTest() {
        Pedido primerPedido = new PedidoTestDataBuilder()
                .conFechaDate(LocalDate.now().minusDays(UN_DIA))
                .conCodigoCliente("1094")
                .conCodigoProducto("0001")
                .conPlacaVehiculoSinPicoYPlaca()
                .conPrecioCompra(500.000)
                .build();
        // Act
        //Assert
        BasePrueba.assertThrows(() -> servicioCrearPedido.ejecutar(primerPedido),
                ExcepcionValorInvalido.class, "La fecha de un pedido no puede ser menor a la fecha actual");
    }

}






