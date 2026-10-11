package com.taller.cajero;

import java.util.Scanner;

public class CajeroAutomatico {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ejecutar(teclado);
    }

    public static void ejecutar(Scanner teclado) {
        Banco banco = new Banco();
        System.out.println("Clientes de prueba: Estudiante (PIN 1234), Invitado (PIN 4321)");
        System.out.print("Titular: ");
        String titular = teclado.nextLine();
        CuentaBancaria cuenta = banco.buscarCuenta(titular);
        boolean correcto = false;
        for (int i = 1; i <= 3 && cuenta != null; i++) {
            System.out.print("PIN: ");
            if (banco.validarPin(titular, teclado.nextLine())) { correcto = true; break; }
            System.out.println("PIN incorrecto (" + i + "/3).");
        }
        if (!correcto) { System.out.println("Cuenta no encontrada o bloqueada."); return; }
        ejecutarMenu(teclado, cuenta, banco);
        // El menú general comparte la entrada, por eso no cerramos System.in aquí.
    }

    private static void ejecutarMenu(Scanner teclado, CuentaBancaria cuenta, Banco banco) {
        int opcion = 0;
        while (opcion != 6) {
            System.out.println("\n--- CAJERO ---");
            System.out.println("1. Consultar saldo  2. Retirar  3. Depositar  4. Transferir  5. Movimientos  6. Salir");
            opcion = leerEntero(teclado, "Elige una opción: ");

            if (opcion == 1) {
                System.out.printf("Saldo: $%.2f%n", cuenta.consultarSaldo());
            } else if (opcion == 2) {
                double cantidad = leerDecimal(teclado, "Cantidad a retirar: ");
                if (cuenta.retirar(cantidad)) {
                    System.out.println("Retiro realizado.");
                } else {
                    System.out.println("Cantidad no válida o saldo insuficiente.");
                }
            } else if (opcion == 3) {
                double cantidad = leerDecimal(teclado, "Cantidad a depositar: ");
                if (cuenta.depositar(cantidad)) {
                    System.out.println("Depósito realizado.");
                } else {
                    System.out.println("La cantidad debe ser mayor que cero.");
                }
            } else if (opcion == 4) {
                System.out.print("Titular destino: ");
                CuentaBancaria destino = banco.buscarCuenta(teclado.nextLine());
                double monto = leerDecimal(teclado, "Monto: ");
                if (cuenta.transferir(destino, monto)) System.out.println("Transferencia realizada.");
                else System.out.println("No se pudo transferir. Verifica destino y saldo.");
            } else if (opcion == 5) {
                cuenta.consultarMovimientos();
            } else if (opcion == 6) {
                System.out.println("Gracias por usar el cajero.");
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

    private static int leerEntero(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException error) {
                System.out.println("Escribe un número entero.");
            }
        }
    }

    private static double leerDecimal(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(teclado.nextLine().replace(',', '.'));
            } catch (NumberFormatException error) {
                System.out.println("Escribe una cantidad válida.");
            }
        }
    }

}
