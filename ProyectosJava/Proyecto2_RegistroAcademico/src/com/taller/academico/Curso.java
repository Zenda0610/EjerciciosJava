package com.taller.academico;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Curso {
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    public void agregarEstudiante(Estudiante estudiante) { estudiantes.add(estudiante); }
    public Estudiante buscarEstudiante(String nombre) {
        for (Estudiante e : estudiantes) if (e.getNombre().equalsIgnoreCase(nombre)) return e;
        return null;
    }
    public boolean modificarNota(String nombreEstudiante, String nombreAsignatura, double nota) {
        Estudiante e = buscarEstudiante(nombreEstudiante);
        return e != null && e.modificarNota(nombreAsignatura, nota);
    }
    public double calcularPromedioGeneral() {
        if (estudiantes.isEmpty()) return 0;
        double suma = 0; for (Estudiante e : estudiantes) suma += e.calcularPromedio();
        return suma / estudiantes.size();
    }
    public int contarAprobados() { int n = 0; for (Estudiante e : estudiantes) if (e.aprobo()) n++; return n; }
    public Estudiante mejorEstudiante() {
        if (estudiantes.isEmpty()) return null;
        Estudiante mejor = estudiantes.get(0);
        for (Estudiante e : estudiantes) if (e.calcularPromedio() > mejor.calcularPromedio()) mejor = e;
        return mejor;
    }
    public Estudiante peorEstudiante() {
        if (estudiantes.isEmpty()) return null;
        Estudiante peor = estudiantes.get(0);
        for (Estudiante e : estudiantes) if (e.calcularPromedio() < peor.calcularPromedio()) peor = e;
        return peor;
    }
    public void mostrarEstadisticas() {
        System.out.printf("Promedio general: %.2f%n", calcularPromedioGeneral());
        System.out.println("Aprobados: " + contarAprobados());
        System.out.println("Reprobados: " + (estudiantes.size() - contarAprobados()));
        if (!estudiantes.isEmpty()) {
            System.out.printf("Mejor: %s (%.2f)%n", mejorEstudiante().getNombre(), mejorEstudiante().calcularPromedio());
            System.out.printf("Menor: %s (%.2f)%n", peorEstudiante().getNombre(), peorEstudiante().calcularPromedio());
        }
    }
    public void guardarReporte(String nombreArchivo) {
        try (FileWriter archivo = new FileWriter(nombreArchivo)) {
            archivo.write("Reporte del curso\n");
            for (Estudiante e : estudiantes) archivo.write(e.getNombre() + " | promedio: " + e.calcularPromedio() + " | faltas: " + e.getFaltas() + "\n");
            archivo.write("Promedio general: " + calcularPromedioGeneral() + "\nAprobados: " + contarAprobados() + "\nReprobados: " + (estudiantes.size() - contarAprobados()));
        } catch (IOException error) { System.out.println("No se pudo guardar el reporte."); }
    }
}
