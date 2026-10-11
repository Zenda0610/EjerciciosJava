package com.taller.academico;

public class GestionEstudiantes {
    private Curso curso = new Curso();
    public void agregar(Estudiante estudiante) { curso.agregarEstudiante(estudiante); }
    public void mostrarEstadisticas() { curso.mostrarEstadisticas(); }
    public void guardarReporte(String archivo) { curso.guardarReporte(archivo); }
    public Curso getCurso() { return curso; }
}
