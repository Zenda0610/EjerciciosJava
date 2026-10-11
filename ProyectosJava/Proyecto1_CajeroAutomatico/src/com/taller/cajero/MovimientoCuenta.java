package com.taller.cajero;

// Esta interfaz describe las operaciones básicas que puede hacer una cuenta.
public interface MovimientoCuenta {
    boolean depositar(double cantidad);
    boolean retirar(double cantidad);
    double consultarSaldo();
}
