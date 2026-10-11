package com.taller.tienda;

public class Carrito {
    private Inventario inventario;
    private Cliente cliente;
    private int[] cantidades;

    public Carrito(Inventario inventario, Cliente cliente) {
        this.inventario = inventario;
        this.cliente = cliente;
        cantidades = new int[inventario.getProductos().size()];
    }

    public boolean agregarProducto(int posicion, int cantidad) {
        if (posicion < 0 || posicion >= cantidades.length) return false;
        if (!inventario.vender(posicion, cantidad)) return false;
        cantidades[posicion] = cantidades[posicion] + cantidad;
        return true;
    }

    public double calcularSubtotal() {
        double total = 0;
        for (int i = 0; i < cantidades.length; i++) {
            total += inventario.obtener(i).getPrecio() * cantidades[i];
        }
        return total;
    }

    public double calcularDescuento() {
        double subtotal = calcularSubtotal();
        return subtotal > 1000 ? subtotal * 0.10 : 0;
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento();
    }

    public void mostrarFactura() {
        System.out.println("\n--- FACTURA ---");
        System.out.println("Cliente: " + cliente.getNombre() + " (" + cliente.getEmail() + ")");
        boolean hayProductos = false;
        for (int i = 0; i < cantidades.length; i++) {
            if (cantidades[i] > 0) {
                hayProductos = true;
                Producto p = inventario.obtener(i);
                System.out.printf("%s x%d: $%.2f%n", p.getNombre(), cantidades[i], p.getPrecio() * cantidades[i]);
            }
        }
        if (!hayProductos) System.out.println("No agregaste productos.");
        System.out.printf("Subtotal: $%.2f%nDescuento: $%.2f%nTotal: $%.2f%n",
                calcularSubtotal(), calcularDescuento(), calcularTotal());
    }
}
