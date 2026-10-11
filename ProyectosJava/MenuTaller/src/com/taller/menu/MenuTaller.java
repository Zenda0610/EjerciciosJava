package com.taller.menu;

import java.util.Scanner;
import com.taller.cajero.CajeroAutomatico;
import com.taller.academico.RegistroAcademico;
import com.taller.tienda.TiendaOnline;
import com.taller.agenda.AgendaContactos;
import com.taller.notas.SistemaNotas;

public class MenuTaller {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n¿A qué ejercicio quieres ir?");
            System.out.println("1. Cajero automático");
            System.out.println("2. Registro académico");
            System.out.println("3. Tienda online");
            System.out.println("4. Agenda de contactos");
            System.out.println("5. Sistema de notas con curva");
            System.out.println("0. Salir");
            opcion = leerOpcion(teclado);

            if (opcion == 1) {
                CajeroAutomatico.ejecutar(teclado);
            } else if (opcion == 2) {
                RegistroAcademico.ejecutar(teclado);
            } else if (opcion == 3) {
                TiendaOnline.ejecutar(teclado);
            } else if (opcion == 4) {
                AgendaContactos.ejecutar(teclado);
            } else if (opcion == 5) {
                SistemaNotas.ejecutar(teclado);
            } else if (opcion == 0) {
                System.out.println("Taller finalizado.");
            } else {
                System.out.println("Opción no válida. Elige un número del 0 al 5.");
            }
        }

        // El menú es el último programa en usar la entrada.
        teclado.close();
    }

    private static int leerOpcion(Scanner teclado) {
        while (true) {
            System.out.print("Selecciona una opción (0 a 5): ");
            try {
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException error) {
                System.out.println("Escribe un número entero.");
            }
        }
    }
}
