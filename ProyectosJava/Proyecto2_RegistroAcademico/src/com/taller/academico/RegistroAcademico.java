package com.taller.academico;

import java.util.Scanner;

public class RegistroAcademico {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ejecutar(teclado);
    }

    public static void ejecutar(Scanner teclado) {
        int cantidad = leerEnteroPositivo(teclado, "¿Cuántos estudiantes vas a registrar? ");
        GestionEstudiantes gestion = new GestionEstudiantes();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nEstudiante " + (i + 1));
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine();
            int faltas = leerEnteroPositivoOZero(teclado, "Número de faltas: ");
            int cantidadAsignaturas = leerEnteroPositivo(teclado, "¿Cuántas asignaturas? ");
            Estudiante estudiante = new Estudiante(nombre, faltas);
            for (int j = 0; j < cantidadAsignaturas; j++) {
                System.out.print("Nombre de asignatura " + (j + 1) + ": ");
                String asignatura = teclado.nextLine();
                estudiante.agregarAsignatura(new Asignatura(asignatura, leerNota(teclado, "Nota (0 a 10): ")));
            }
            gestion.agregar(estudiante);
        }

        System.out.println("\n--- ESTADÍSTICAS DEL GRUPO ---");
        gestion.mostrarEstadisticas();
        System.out.print("¿Guardar reporte? (s/n): ");
        if (teclado.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Nombre del archivo (por ejemplo reporte.txt): ");
            gestion.guardarReporte(teclado.nextLine());
            System.out.println("Reporte guardado.");
        }
        // El menú general comparte la entrada, por eso no cerramos System.in aquí.
    }

    private static int leerEnteroPositivo(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(teclado.nextLine());
                if (valor > 0) return valor;
            } catch (NumberFormatException error) {
                // Se muestra el mismo mensaje de validación de abajo.
            }
            System.out.println("Escribe un entero mayor que cero.");
        }
    }

    private static double leerNota(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double nota = Double.parseDouble(teclado.nextLine().replace(',', '.'));
                if (nota >= 0 && nota <= 10) return nota;
            } catch (NumberFormatException error) {
                // Se muestra el mismo mensaje de validación de abajo.
            }
            System.out.println("La nota debe ser un número entre 0 y 10.");
        }
    }

    private static int leerEnteroPositivoOZero(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try { int valor = Integer.parseInt(teclado.nextLine()); if (valor >= 0) return valor; }
            catch (NumberFormatException error) { }
            System.out.println("Escribe un entero igual o mayor que cero.");
        }
    }
}
