package com.taller.cajero;

public abstract class Cuenta implements MovimientoCuenta {
    protected String titular;
    protected double saldo;
    private String pin;
    public Cuenta(String titular, double saldoInicial, String pin) { this.titular = titular; this.saldo = saldoInicial; this.pin = pin; }
    public String getTitular() { return titular; }
    public boolean validarPin(String pinIngresado) { return pin.equals(pinIngresado); }
    @Override public double consultarSaldo() { return saldo; }
    public abstract void registrarMovimiento(String movimiento);
    public abstract void consultarMovimientos();
}
