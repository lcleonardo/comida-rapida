package com.ceiba.pedido.modelo.entidad;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import lombok.Getter;

@Getter
public class Pedido {

    private static final String MAXIMO_25_CARACTERES = "Máximo 25 caracteres.";
    private static final String MAXIMO_250_CARACTERES = "Máximo 250 caracteres.";
    private static final String FECHA_INCORRECTA = "Fecha incorrecta, debe tener el siguiente formato: yyyy-MM-dd.";
    private static final String LA_FECHA_NO_PUEDE_SER_MENOR_A_LA_FECHA_ACTUAL = "La fecha de un pedido no puede ser menor a la fecha actual.";
    private static final String CODIGO_PRODUCTO_OBLIGATORIO = "El código del producto es obligatorio.";
    private static final String CODIGO_CLIENTE_OBLIGATORIO = "El código del cliente es obligatorio.";
    private static final String DIRECCION_DOMICILIO_OBLIGATORIA = "La direción del domicilio es obligatoria.";
    private static final String PLACA_VEHICULO_OBLIGATORIA = "La placa del vehiculo es obligatoria y debe terminar en un número entero del 0 al 9.";
    private static final String PRECIO_COMPRA_OBLIGATORIO = "El precio de la compra es obligatorio, y debe ser mayor a 0.0.";
    private static final String PORCENTAJE_DE_DESCUENTO_NO_VALIDO = "El porcentaje de descuento debe ser un número mayor a 0.0.";
    private static final String PORCENTAJE_DE_PROMOCION_NO_VALIDO = "El porcentaje de promoción debe ser un número mayor a 0.0.";
    private static final String NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA = "El conductor no puede realizar el domicilio porque tiene pico y placa.";
    private static final String APLICA_PROMOCION_OBLIGATORIO = "Aplica promoción es obligatorio.";
    private static final Double PORCENTAJE_NORMAL_DE_GANANCIA_EN_DOMICILIO = 5.0;
    private static final Double CINCO_PORCIENTO_MAS_DE_GANACIA_EN_DOMICILIO = 5.0;
    private static final Double NO_SE_CALCULA_PRECIO_DE_DESCUENTO = 0.0;
    private static final Double CINCUENTA_PORCIENTO_DE_DESCUENTO = 2.0;
    private static final Integer SI_APLICA_PROMOCION = 1;

    private Long id;
    private LocalDate fecha;
    private String codigoCliente;
    private String codigoProducto;
    private String direccionDomicilio;
    private String placaVehiculo;
    private Double precioCompra;
    private Double porcentajeDescuento;
    private Double porcentajePromocion;
    private Double precioTotal;

    public static Pedido crear(String fecha,
                               String codigoCliente,
                               String codigoProducto,
                               String direccionDomicilio,
                               String placaVehiculo,
                               Double precioCompra,
                               Double porcentajeDescuento,
                               Double porcentajePromocion
    ) {

        ValidadorArgumento.validarObligatorio(fecha, FECHA_INCORRECTA);
        ValidadorArgumento.validarFechaFormatoYYYMMDD(fecha, FECHA_INCORRECTA);
        LocalDate fechaValida = LocalDate.parse(fecha, DateTimeFormatter.ISO_DATE);
        ValidadorArgumento.validarFechaMenorAFechaActual(fechaValida, LA_FECHA_NO_PUEDE_SER_MENOR_A_LA_FECHA_ACTUAL);

        ValidadorArgumento.validarObligatorio(codigoCliente, CODIGO_CLIENTE_OBLIGATORIO);
        ValidadorArgumento.validarNoVacio(codigoCliente, CODIGO_CLIENTE_OBLIGATORIO);
        ValidadorArgumento.validarLongitudMaxima(codigoCliente, 250, MAXIMO_250_CARACTERES);

        ValidadorArgumento.validarObligatorio(codigoProducto, CODIGO_PRODUCTO_OBLIGATORIO);
        ValidadorArgumento.validarNoVacio(codigoProducto, CODIGO_PRODUCTO_OBLIGATORIO);
        ValidadorArgumento.validarLongitudMaxima(codigoProducto, 250, MAXIMO_250_CARACTERES);

        ValidadorArgumento.validarObligatorio(direccionDomicilio, DIRECCION_DOMICILIO_OBLIGATORIA);
        ValidadorArgumento.validarNoVacio(direccionDomicilio, DIRECCION_DOMICILIO_OBLIGATORIA);
        ValidadorArgumento.validarLongitudMaxima(direccionDomicilio, 250, MAXIMO_250_CARACTERES);

        ValidadorArgumento.validarObligatorio(placaVehiculo, PLACA_VEHICULO_OBLIGATORIA);
        ValidadorArgumento.validarNoVacio(placaVehiculo, PLACA_VEHICULO_OBLIGATORIA);
        ValidadorArgumento.validarLongitudMaxima(placaVehiculo, 25, MAXIMO_25_CARACTERES);
        validarFormatoPlacaVehiculo(placaVehiculo, PLACA_VEHICULO_OBLIGATORIA);
        validarSiLaPlacaDelVehiculoTienePicoYPlaca(fechaValida, placaVehiculo);

        ValidadorArgumento.validarObligatorio(precioCompra, PRECIO_COMPRA_OBLIGATORIO);
        ValidadorArgumento.validarPositivo(precioCompra, PRECIO_COMPRA_OBLIGATORIO);

        ValidadorArgumento.validarObligatorio(porcentajeDescuento, PORCENTAJE_DE_DESCUENTO_NO_VALIDO);
        ValidadorArgumento.validarMenorACero(porcentajeDescuento, PORCENTAJE_DE_DESCUENTO_NO_VALIDO);

        ValidadorArgumento.validarObligatorio(porcentajePromocion, PORCENTAJE_DE_PROMOCION_NO_VALIDO);
        ValidadorArgumento.validarMenorACero(porcentajePromocion, PORCENTAJE_DE_DESCUENTO_NO_VALIDO);

        Double precioDescuento = calcularPrecioDescuento(porcentajeDescuento, precioCompra);
        Double precioPromocion = calcularPrecioPromocion(porcentajePromocion, precioCompra);
        Double precioTotal = calcularPrecioTotal(precioCompra, precioDescuento, precioPromocion);

        return new Pedido(fechaValida,
                codigoCliente,
                codigoProducto,
                direccionDomicilio,
                placaVehiculo,
                precioCompra,
                porcentajeDescuento,
                porcentajePromocion,
                precioTotal);
    }

    private static Double calcularPrecioPromocion(Double porcentajePromocion, Double precioCompra) {
        return (precioCompra / 100) * porcentajePromocion;
    }

    private static Double calcularPrecioDescuento(Double porcentajeDescuento, Double precioCompra) {
        return (precioCompra / 100) * porcentajeDescuento;
    }

    private static Double calcularPrecioTotal(Double precioCompra, Double precioDescuento, Double precioPromocion) {
        return (precioCompra - (precioDescuento + precioPromocion));
    }

    private static void validarFormatoPlacaVehiculo(String placaVehiculo, String mensaje) {
        try {
            String ultimoCaracter = placaVehiculo.substring(placaVehiculo.length() - 1);
            Integer.parseInt(ultimoCaracter);
        } catch (NumberFormatException e) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    private static void validarSiLaPlacaDelVehiculoTienePicoYPlaca(LocalDate fechaPedido, String placa) {
        Enum<DayOfWeek> diaDeLaSemana = fechaPedido.getDayOfWeek();
        if (placaTerminadaEnNumeroPar(placa)) {
            if (diaDeLaSemana == DayOfWeek.TUESDAY || diaDeLaSemana == DayOfWeek.THURSDAY) {
                throw new ExcepcionValorInvalido(NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA);
            }
        } else {
            if (diaDeLaSemana == DayOfWeek.WEDNESDAY || diaDeLaSemana == DayOfWeek.FRIDAY) {
                throw new ExcepcionValorInvalido(NO_PUEDE_REALIZAR_EL_DOMICILIO_PORQUE_TIENE_PICO_Y_PLACA);
            }
        }
    }

    private static boolean placaTerminadaEnNumeroPar(String placa) {
        return ultimoDigitoPlaca(placa) % 2 == 0;
    }

    private static int ultimoDigitoPlaca(String placa) {
        return Integer.parseInt(placa.substring(placa.length() - 1));
    }

    public Pedido(LocalDate fecha,
                  String codigoCliente,
                  String codigoProducto,
                  String direccionDomicilio,
                  String placaVehiculo,
                  Double precioCompra,
                  Double porcentajeDescuento,
                  Double porcentajePromocion,
                  Double precioTotal) {
        this.fecha = fecha;
        this.codigoCliente = codigoCliente;
        this.codigoProducto = codigoProducto;
        this.direccionDomicilio = direccionDomicilio;
        this.placaVehiculo = placaVehiculo;
        this.precioCompra = precioCompra;
        this.porcentajeDescuento = porcentajeDescuento;
        this.porcentajePromocion = porcentajePromocion;
        this.precioTotal = precioTotal;
    }
}
