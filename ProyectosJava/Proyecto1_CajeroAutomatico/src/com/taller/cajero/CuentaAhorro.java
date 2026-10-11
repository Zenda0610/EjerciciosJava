package com.taller.cajero;

public class CuentaAhorro extends CuentaBancaria {
    public CuentaAhorro(String titular, double saldo, String pin) { super(titular, saldo, pin); }
    @Override public boolean depositar(double cantidad) {
        if (!super.depositar(cantidad)) return false;
        super.depositar(cantidad * 0.01);
        return true;
    }
}
