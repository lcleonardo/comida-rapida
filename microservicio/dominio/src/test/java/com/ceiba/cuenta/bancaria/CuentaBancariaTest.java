package com.ceiba.cuenta.bancaria;

import com.ceiba.core.BasePrueba;
import com.ceiba.cuenta.bancaria.testdatabuilder.CuentaBancariaBuilder;
import com.ceiba.cuentabancaria.modelo.entidad.CuentaBancaria;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import org.junit.Assert;
import org.junit.Test;

public class CuentaBancariaTest {


    @Test
    public void elSaldoInicialDebeSerCero() {
        //Arr
        CuentaBancariaBuilder cuentaBancariaBuilder = new CuentaBancariaBuilder();
        //Act
        CuentaBancaria cuentaBancaria = cuentaBancariaBuilder.build();
        //Assert
        Assert.assertEquals(0.0, cuentaBancaria.getSaldoInicial(), 0.0);
    }


    @Test
    public void elSaldoDeLaCuentaNoPuedeVolverseNegativo() {
        //Arr
        CuentaBancariaBuilder cuentaBancariaBuilder = new CuentaBancariaBuilder();
        //Act
        cuentaBancariaBuilder.conSaldo(-10.0);
        //Assert
        BasePrueba.assertThrows(() -> cuentaBancariaBuilder.build(),
                ExcepcionValorInvalido.class,
                "El saldo de la cuenta no puede ser negativo.");
    }
}



