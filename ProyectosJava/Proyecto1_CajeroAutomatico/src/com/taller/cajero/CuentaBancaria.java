package com.taller.cajero;

import java.util.ArrayList;

public class CuentaBancaria extends Cuenta {
    private ArrayList<String> movimientos = new ArrayList<>();
    public CuentaBancaria(String titular, double saldoInicial, String pin) { super(titular, saldoInicial, pin); }
    @Override public boolean depositar(double cantidad) {
        if (cantidad <= 0) return false;
        saldo += cantidad; registrarMovimiento("Depósito: +$" + cantidad); return true;
    }
    @Override public boolean retirar(double cantidad) {
        if (cantidad <= 0 || cantidad > saldo) return false;
        saldo -= cantidad; registrarMovimiento("Retiro: -$" + cantidad); return true;
    }
    public boolean transferir(CuentaBancaria destino, double monto) {
        if (destino == null || destino == this || monto <= 0 || monto > saldo) return false;
        saldo -= monto; destino.recibirTransferencia(monto, titular);
        registrarMovimiento("Transferencia enviada: -$" + monto + " a " + destino.getTitular()); return true;
    }
    private void recibirTransferencia(double monto, String origen) {
        saldo += monto; registrarMovimiento("Transferencia recibida: +$" + monto + " de " + origen);
    }
    @Override public void registrarMovimiento(String movimiento) {
        movimientos.add(movimiento); if (movimientos.size() > 5) movimientos.remove(0);
    }
    @Override public void consultarMovimientos() {
        if (movimientos.isEmpty()) System.out.println("Todavía no hay movimientos.");
        for (String movimiento : movimientos) System.out.println(movimiento);
    }
}
