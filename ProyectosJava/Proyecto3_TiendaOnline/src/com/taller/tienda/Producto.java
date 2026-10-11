package com.taller.tienda;

public class Producto implements Vendible {
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;

    public Producto(String nombre, double precio, int stock) {
        this(nombre, precio, stock, "General");
    }

    public Producto(String nombre, double precio, int stock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public double getPrecio() { return precio; }

    public int getStock() { return stock; }
    public String getCategoria() { return categoria; }

    public boolean hayStock(int cantidad) {
        return cantidad > 0 && cantidad <= stock;
    }

    public boolean disminuirStock(int cantidad) {
        if (!hayStock(cantidad)) return false;
        stock = stock - cantidad;
        return true;
    }
}
