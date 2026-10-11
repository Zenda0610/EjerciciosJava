package com.taller.notas;

public class Alumno extends Persona {
    private double notaParcial1;
    private double notaParcial2;
    private double examenFinal;
    private double notaFinal;
    public Alumno(String nombre, double parcial1, double parcial2, double examen) {
        super(nombre); notaParcial1 = parcial1; notaParcial2 = parcial2; examenFinal = examen;
        notaFinal = calcularNotaOriginal();
    }
    public double calcularNotaOriginal() { return notaParcial1 * .30 + notaParcial2 * .30 + examenFinal * .40; }
    public void aplicarCurvaPorcentual(double porcentaje) {
        double original = calcularNotaOriginal();
        double aumento = original == 0 ? porcentaje / 10.0 : original * porcentaje / 100.0;
        notaFinal = Math.min(10, notaFinal + aumento);
    }
    public void aplicarCurvaFija(double puntos) { notaFinal = Math.min(10, notaFinal + puntos); }
    public boolean aproboOriginalmente() { return calcularNotaOriginal() >= 6; }
    public boolean estaAprobado() { return notaFinal >= 6; }
    public double getNotaOriginal() { return calcularNotaOriginal(); }
    public double getNotaFinal() { return notaFinal; }
}
