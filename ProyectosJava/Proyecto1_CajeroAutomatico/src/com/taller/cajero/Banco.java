package com.taller.cajero;

import java.util.ArrayList;

public class Banco {
    private ArrayList<CuentaBancaria> cuentas = new ArrayList<>();
    public Banco() {
        cuentas.add(new CuentaAhorro("Estudiante", 500000, "1234"));
        cuentas.add(new CuentaCorriente("Invitado", 300000, "4321", 100000));
    }
    public CuentaBancaria buscarCuenta(String titular) {
        for (CuentaBancaria cuenta : cuentas) if (cuenta.getTitular().equalsIgnoreCase(titular)) return cuenta;
        return null;
    }
    public boolean validarPin(String titular, String pin) {
        CuentaBancaria cuenta = buscarCuenta(titular);
        return cuenta != null && cuenta.validarPin(pin);
    }
}
