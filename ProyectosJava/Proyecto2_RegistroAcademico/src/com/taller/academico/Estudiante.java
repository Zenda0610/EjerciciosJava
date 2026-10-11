package com.taller.academico;

import java.util.ArrayList;

public class Estudiante extends Persona {
    private ArrayList<Asignatura> asignaturas = new ArrayList<>();
    private int faltas;
    public Estudiante(String nombre, int faltas) { super(nombre); this.faltas = faltas; }
    public void agregarAsignatura(Asignatura asignatura) { asignaturas.add(asignatura); }
    public ArrayList<Asignatura> getAsignaturas() { return asignaturas; }
    public int getFaltas() { return faltas; }
    public double calcularPromedio() {
        if (asignaturas.isEmpty()) return 0;
        double suma = 0;
        for (Asignatura a : asignaturas) suma += a.getNotaFinal();
        return suma / asignaturas.size();
    }
    public boolean aprobo() { return calcularPromedio() >= 6 && faltas < 10; }
    public boolean modificarNota(String nombreAsignatura, double nota) {
        for (Asignatura a : asignaturas) if (a.getNombre().equalsIgnoreCase(nombreAsignatura)) { a.setNotaFinal(nota); return true; }
        return false;
    }
}
