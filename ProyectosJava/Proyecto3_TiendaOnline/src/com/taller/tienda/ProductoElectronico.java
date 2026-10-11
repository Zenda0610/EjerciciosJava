package com.taller.tienda;

public class ProductoElectronico extends Producto {
    private int mesesGarantia;
    public ProductoElectronico(String nombre, double precio, int stock, int mesesGarantia) {
        super(nombre, precio, stock, "Electrónica"); this.mesesGarantia = mesesGarantia;
    }
    public int getMesesGarantia() { return mesesGarantia; }
}
