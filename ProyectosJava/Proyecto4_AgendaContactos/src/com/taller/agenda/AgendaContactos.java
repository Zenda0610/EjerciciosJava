package com.taller.agenda;

import java.util.Scanner;

public class AgendaContactos {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ejecutar(teclado);
    }

    public static void ejecutar(Scanner teclado) {
        Agenda agenda = new Agenda();
        agenda.cargarDesdeArchivo();
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n--- AGENDA ---");
            System.out.println("1. Registrar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Listar contactos");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Ver contactos por grupo");
            System.out.println("6. Salir");
            opcion = leerEntero(teclado, "Elige una opción: ");

            if (opcion == 1) {
                System.out.print("Nombre: ");
                String nombre = teclado.nextLine();
                System.out.print("Teléfono: ");
                String telefono = teclado.nextLine();
                System.out.print("Email: ");
                String email = teclado.nextLine();
                System.out.print("Grupo (Familia, Trabajo, Amigos...): ");
                String grupo = teclado.nextLine();
                if (agenda.agregarContacto(new Contacto(nombre, telefono, email, grupo))) {
                    System.out.println("Contacto guardado.");
                } else {
                    System.out.println("Datos no válidos, o el nombre/teléfono ya está registrado. Usa un teléfono de 9 dígitos.");
                }
            } else if (opcion == 2) {
                System.out.print("Texto del nombre a buscar: ");
                java.util.ArrayList<Contacto> encontrados = agenda.buscarContacto(teclado.nextLine());
                if (encontrados.isEmpty()) System.out.println("No se encontraron contactos.");
                else for (Contacto contacto : encontrados) contacto.mostrarInfo();
            } else if (opcion == 3) {
                agenda.mostrarTodos();
            } else if (opcion == 4) {
                System.out.print("Nombre a eliminar: ");
                if (agenda.eliminarContacto(teclado.nextLine())) System.out.println("Contacto eliminado.");
                else System.out.println("No se encontró el contacto.");
            } else if (opcion == 5) {
                System.out.print("Grupo a mostrar: ");
                agenda.mostrarContactosPorGrupo(teclado.nextLine());
            } else if (opcion == 6) {
                System.out.println("Agenda cerrada.");
            } else {
                System.out.println("Opción no válida.");
            }
        }
        // El menú general comparte la entrada, por eso no cerramos System.in aquí.
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
}
