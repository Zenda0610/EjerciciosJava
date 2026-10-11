package com.taller.tienda;

import java.util.Scanner;
import java.util.ArrayList;

public class TiendaOnline {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ejecutar(teclado);
    }

    public static void ejecutar(Scanner teclado) {
        Inventario inventario = new Inventario();
        inventario.agregar(new Producto("Cuaderno", 8000, 10, "Papelería"));
        inventario.agregar(new Producto("Lapiz", 1500, 20, "Papelería"));
        inventario.agregar(new ProductoAlimenticio("Galletas", 2500, 12, "2027-05-01"));
        inventario.agregar(new ProductoElectronico("Audífonos", 45000, 6, 12));
        System.out.print("Correo del cliente: ");
        Cliente cliente = new Cliente("Cliente", teclado.nextLine());
        Carrito carrito = new Carrito(inventario, cliente);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n--- TIENDA ---");
            System.out.println("Categorías: Papelería, Alimentos, Electrónica");
            System.out.print("Escribe una categoría o TODAS para ver todo: ");
            String categoria = teclado.nextLine();
            ArrayList<Integer> visibles = new ArrayList<>();
            for (int i = 0; i < inventario.getProductos().size(); i++) {
                Producto p = inventario.obtener(i);
                if (categoria.equalsIgnoreCase("TODAS") || p.getCategoria().equalsIgnoreCase(categoria)) {
                    visibles.add(i);
                }
            }
            if (visibles.isEmpty()) System.out.println("No hay productos en esa categoría.");
            for (int i = 0; i < visibles.size(); i++) {
                Producto p = inventario.obtener(visibles.get(i));
                System.out.printf("%d. %s (%s) - $%.2f, stock: %d%n", i + 1,
                        p.getNombre(), p.getCategoria(), p.getPrecio(), p.getStock());
            }
            System.out.println("0. Terminar compra");
            opcion = leerEntero(teclado, "Elige un producto: ");
            if (opcion >= 1 && opcion <= visibles.size()) {
                int cantidad = leerEntero(teclado, "Cantidad: ");
                if (carrito.agregarProducto(visibles.get(opcion - 1), cantidad)) {
                    System.out.println("Producto agregado.");
                } else {
                    System.out.println("Cantidad no válida o stock insuficiente.");
                }
            } else if (opcion != 0) {
                System.out.println("Opción no válida.");
            }
        }
        carrito.mostrarFactura();
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
