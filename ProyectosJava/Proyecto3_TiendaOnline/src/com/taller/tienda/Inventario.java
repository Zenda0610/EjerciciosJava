package com.taller.tienda;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> productos = new ArrayList<>();
    public void agregar(Producto producto) { productos.add(producto); }
    public ArrayList<Producto> getProductos() { return productos; }
    public Producto obtener(int posicion) {
        if (posicion < 0 || posicion >= productos.size()) return null;
        return productos.get(posicion);
    }
    public boolean vender(int posicion, int cantidad) {
        Producto p = obtener(posicion);
        return p != null && p.disminuirStock(cantidad);
    }
}
