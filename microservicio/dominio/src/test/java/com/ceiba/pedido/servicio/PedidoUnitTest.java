package com.ceiba.pedido.servicio;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.servicio.testdatabuilder.PedidoTestDataBuilder;

import java.time.LocalDate;

public class PedidoUnitTest {

    @Test
    public void validarFechaNoNula() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conFecha(null);
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorObligatorio.class, () -> pedidoTestDataBuilder.build(),
                "Fecha incorrecta, debe tener el siguiente formato: yyyy-MM-dd.");
    }


    @Test
    public void validarFechaFormatoIncorrecto() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conFecha("20-08-2021");
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorInvalido.class, () -> pedidoTestDataBuilder.build(),
                "Fecha incorrecta, debe tener el siguiente formato: yyyy-MM-dd.");
    }

    @Test//Debe ir en PEdido test
    public void noDeberiaCrearUnPedidoPorqueLaFechaEsMenorALaFechaActual() {
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conFechaTipoLocalDate(LocalDate.now().minusDays(1));
        // Act
        //Assert
        assertThrows(ExcepcionValorInvalido.class, () -> pedidoTestDataBuilder.build(),
                "La fecha de un pedido no puede ser menor a la fecha actual.");
    }

    @Test
    public void validarCodigoClienteNoNulo() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conCodigoCliente(null);
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorObligatorio.class, () ->
                pedidoTestDataBuilder.build(), "El código del cliente es obligatorio.");
    }

    @Test
    public void validarCodigoClienteNoVacio() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conCodigoCliente("");
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorObligatorio.class,
                () -> pedidoTestDataBuilder.build(), "El código del cliente es obligatorio.");
    }


    @Test
    public void validarCodigoProductoNoNulo() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conCodigoProducto(null);
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorObligatorio.class, () ->
                pedidoTestDataBuilder.build(), "El código del producto es obligatorio.");
    }

    @Test
    public void validarCodigoProductoNoVacio() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conCodigoProducto("");
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorObligatorio.class, () -> pedidoTestDataBuilder.build(),
                "El código del producto es obligatorio.");
    }


    @Test
    public void validarDireccionClienteNoNula() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conDirecionDomicilio(null);
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorObligatorio.class, () -> pedidoTestDataBuilder.build(),
                "La direción es obligatoria.");
    }

    @Test
    public void validarDireccionDomicilioNoVacia() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conDirecionDomicilio("");
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorObligatorio.class, () -> pedidoTestDataBuilder.build(),
                "La direción del domicilio es obligatoria.");
    }

    @Test
    public void validarPlacaVehiculoNoNula() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conPlacaVehiculo(null);
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorObligatorio.class, () -> pedidoTestDataBuilder.build(),
                "La placa del vehiculo es obligatoria y debe terminar en un número entero del 0 al 9.");
    }

    @Test
    public void validarPlacaVehiculoNoVacia() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conPlacaVehiculo("");
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorObligatorio.class, () -> pedidoTestDataBuilder.build(),
                "La placa del vehiculo es obligatoria y debe terminar en un número entero del 0 al 9.");
    }

    @Test
    public void validarPlacaVehiculoFormatoIncorrecto() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conPlacaVehiculo("VDF12W");
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorInvalido.class, () -> pedidoTestDataBuilder.build(),
                "La placa del vehiculo es obligatoria y debe terminar en un número entero del 0 al 9.");
    }

    @Test
    public void validarSiLaPlacaVehiculoNoTienePicoYPlaca() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conFechaTipoLocalDate(LocalDate.now())
                .conPlacaVehiculoSinPicoYPlaca();
        // 2. Act
        // 3. Assert
        assertDoesNotThrow(() -> pedidoTestDataBuilder.build(),
                "El conductor no puede realizar el domicilio porque tiene pico y placa.");
    }

    @Test
    public void validarSiLaPlacaVehiculoTienePicoYPlaca() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conFechaTipoLocalDate(LocalDate.now())
                .conPlacaVehiculoConPicoYPlaca();
        // 2. Act
        // 3. Assert
        assertDoesNotThrow(() -> pedidoTestDataBuilder.build(),
                "El conductor no puede realizar el domicilio porque tiene pico y placa.");
    }

    @Test
    public void validarPrecioCompraNoNuloTest() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conPlacaVehiculoSinPicoYPlaca()
                .conPrecioCompra(null);
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorObligatorio.class, () -> pedidoTestDataBuilder.build(),
                "El precio de la compra es obligatorio.");
    }

    @Test
    public void validarPrecioCompraIgualACero() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conPrecioCompra(0.0);
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorInvalido.class, () -> pedidoTestDataBuilder.build(),
                "El precio de la compra es obligatorio.");
    }

    @Test
    public void validarPrecioCompraMenorACero() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conPrecioCompra(-50.000);
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorInvalido.class, () -> pedidoTestDataBuilder.build(),
                "El precio de la compra es obligatorio.");
    }

    @Test
    public void validarPorcentajeDescuentoNoNulo() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conFechaTipoLocalDate(LocalDate.now())
                .conPlacaVehiculoSinPicoYPlaca()
                .conPorcentajeDescuento(null);
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorObligatorio.class, () -> pedidoTestDataBuilder.build(),
                "El porcentaje de descuento debe ser un número mayor a 0.0.");
    }

    @Test
    public void validarPorcentajeDescuentoMenorACero() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conPorcentajeDescuento(-10.0);
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorInvalido.class, () -> pedidoTestDataBuilder.build(),
                "El porcentaje de descuento debe ser un número mayor a 0.0.");
    }

    @Test
    public void validarPedidoSinPorcentajeDeDescuento() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conPorcentajeDescuento(0.0)
                .conPlacaVehiculoSinPicoYPlaca()
                .conPrecioCompra(200.000);
        // 2. Act
        Pedido pedido = pedidoTestDataBuilder.build();
        // 3. Assert
        assertEquals(0.0, pedido.getPorcentajeDescuento().doubleValue());
        assertEquals(200.000, pedido.getPrecioCompra().doubleValue());
    }

    @Test
    public void validarPedidoConDiezPorcientoDeDescuento() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conPorcentajeDescuento(10.0)
                .conPlacaVehiculoSinPicoYPlaca()
                .conPrecioCompra(20.000);
        // 2. Act
        Pedido pedido = pedidoTestDataBuilder.build();
        // 3. Assert
        assertEquals(10.0, pedido.getPorcentajeDescuento().doubleValue());
        assertEquals(20.000, pedido.getPrecioCompra().doubleValue());
        assertEquals(18.000, pedido.getPrecioTotal().doubleValue());
    }

    @Test
    public void validarPorcentajePromocionNoNulo() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conPorcentajePromocion(null);
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorInvalido.class, () -> pedidoTestDataBuilder.build(),
                "El porcentaje de promoción debe ser un número mayor a 0.0.");
    }

    @Test
    public void validarPorcentajePromocionMenorACero() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conPorcentajePromocion(-10.0);
        // 2. Act
        // 3. Assert
        assertThrows(ExcepcionValorInvalido.class, () -> pedidoTestDataBuilder.build(),
                "El porcentaje de promoción debe ser un número mayor a 0.0.");
    }

    @Test
    public void validarPedidoSinPorcentajeDePromocion() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conPorcentajePromocion(0.0)
                .conPlacaVehiculoSinPicoYPlaca()
                .conPrecioCompra(200.000);
        // 2. Act
        Pedido pedido = pedidoTestDataBuilder.build();
        // 3. Assert
        assertEquals(0.0, pedido.getPorcentajeDescuento().doubleValue());
        assertEquals(200.000, pedido.getPrecioCompra().doubleValue());
    }

    @Test
    public void validarPedidoConCincuentaPorcientoDePromocion() {
        // 1. Arrange
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder()
                .conPorcentajePromocion(50.0)
                .conPlacaVehiculoSinPicoYPlaca()
                .conPrecioCompra(200.000);
        // 2. Act
        Pedido pedido = pedidoTestDataBuilder.build();
        // 3. Assert
        assertEquals(50.0, pedido.getPorcentajePromocion().doubleValue());
        assertEquals(200.000, pedido.getPrecioCompra().doubleValue());
        assertEquals(100.000, pedido.getPrecioTotal().doubleValue());
    }

}
