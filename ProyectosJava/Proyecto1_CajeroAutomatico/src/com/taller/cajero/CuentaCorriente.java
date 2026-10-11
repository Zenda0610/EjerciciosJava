package com.taller.cajero;

public class CuentaCorriente extends CuentaBancaria {
    private double sobregiro;
    public CuentaCorriente(String titular, double saldo, String pin, double sobregiro) { super(titular, saldo, pin); this.sobregiro = sobregiro; }
    @Override public boolean retirar(double cantidad) {
        double saldoActual = consultarSaldo();
        if (cantidad <= 0 || cantidad > saldoActual + sobregiro) return false;
        saldo -= cantidad;
        registrarMovimiento("Retiro: -$" + cantidad + " (incluye sobregiro)");
        return true;
    }
}
