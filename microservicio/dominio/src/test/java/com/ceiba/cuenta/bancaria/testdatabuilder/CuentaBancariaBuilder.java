package com.ceiba.cuenta.bancaria.testdatabuilder;

import com.ceiba.cuentabancaria.modelo.entidad.CuentaBancaria;

public class CuentaBancariaBuilder {

    private Double saldo;

    public CuentaBancariaBuilder() {
        this.saldo = 0.0;
    }

    public CuentaBancariaBuilder conSaldo(double valor) {
        this.saldo = valor;
        return this;
    }

    public CuentaBancaria build() {
        return new CuentaBancaria(this.saldo);
    }


}
