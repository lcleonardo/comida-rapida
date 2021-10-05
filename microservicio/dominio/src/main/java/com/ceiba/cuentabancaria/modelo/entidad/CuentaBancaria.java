package com.ceiba.cuentabancaria.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import lombok.Getter;

@Getter
public class CuentaBancaria {

    private static final String EL_SALDO_DE_LA_CUENTA_NO_PUEDE_SER_NEGATIVO = "El saldo de la cuenta no puede ser negativo.";
    private final Double saldo;

    private Double saldoInicial;

    public CuentaBancaria(Double saldo) {

        ValidadorArgumento.validarMenorACero(saldo, EL_SALDO_DE_LA_CUENTA_NO_PUEDE_SER_NEGATIVO);

        this.saldoInicial = 0.0;
        this.saldo = saldo;
    }
}
