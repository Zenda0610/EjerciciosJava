package com.taller.notas;

import java.util.Scanner;
import java.util.InputMismatchException;

public class SistemaNotas {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ejecutar(teclado);
    }

    public static void ejecutar(Scanner teclado) {
        int cantidad = leerEnteroPositivo(teclado, "¿Cuántos alumnos vas a registrar? ");
        GestionNotas gestion = new GestionNotas(cantidad);

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nAlumno " + (i + 1));
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine();
            double parcial1 = leerNota(teclado, "Nota del parcial 1 (30%): ");
            double parcial2 = leerNota(teclado, "Nota del parcial 2 (30%): ");
            double examen = leerNota(teclado, "Nota del examen final (40%): ");
            gestion.agregar(i, new Alumno(nombre, parcial1, parcial2, examen));
        }

        double promedio = gestion.calcularPromedioOriginal();
        System.out.printf("\nPromedio original del curso: %.2f%n", promedio);
        if (promedio < 6) {
            System.out.printf("El promedio es menor que 6. Se aplicará una curva de %.2f puntos, calculada con la mejor nota.%n",
                    10 - gestion.obtenerNotaMasAlta());
            gestion.aplicarCurvaSegunMejorAlumno();
        } else {
            System.out.println("No es necesario aplicar curva.");
        }
        gestion.mostrarResultados();
        // El menú general comparte la entrada, por eso no cerramos System.in aquí.
    }

    private static int leerEnteroPositivo(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(teclado.nextLine());
                if (valor > 0) return valor;
            } catch (NumberFormatException error) {
                // Se informa el error con el mensaje de abajo.
            }
            System.out.println("Escribe un entero mayor que cero.");
        }
    }

    private static double leerNota(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double nota = teclado.nextDouble();
                teclado.nextLine();
                if (nota >= 0 && nota <= 10) return nota;
                System.out.println("La nota debe estar entre 0 y 10.");
            } catch (InputMismatchException error) {
                System.out.println("Escribe un número válido.");
                teclado.nextLine();
            }
        }
    }
}
