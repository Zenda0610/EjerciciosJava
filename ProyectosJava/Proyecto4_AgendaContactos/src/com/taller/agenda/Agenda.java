package com.taller.agenda;

import java.io.*;
import java.util.ArrayList;

public class Agenda {
    private ArrayList<Contacto> contactos = new ArrayList<>();
    private final String archivo = "agenda.txt";

    public boolean agregarContacto(Contacto c) {
        if (c.getNombre().trim().isEmpty() || !c.getTelefono().matches("[0-9]{9}")
                || !c.getEmail().contains("@") || !c.getEmail().contains(".")) return false;
        for (Contacto actual : contactos)
            if (actual.getNombre().equalsIgnoreCase(c.getNombre()) || actual.getTelefono().equals(c.getTelefono())) return false;
        contactos.add(c); guardarEnArchivo(); return true;
    }
    public ArrayList<Contacto> buscarContacto(String texto) {
        ArrayList<Contacto> encontrados = new ArrayList<>();
        for (Contacto c : contactos) if (c.getNombre().toLowerCase().contains(texto.toLowerCase())) encontrados.add(c);
        return encontrados;
    }
    public void mostrarTodos() {
        if (contactos.isEmpty()) { System.out.println("La agenda está vacía."); return; }
        for (Contacto c : contactos) c.mostrarInfo();
    }
    public void mostrarContactosPorGrupo(String grupo) {
        boolean encontrado = false;
        for (Contacto c : contactos) if (c.getGrupo().equalsIgnoreCase(grupo)) { c.mostrarInfo(); encontrado = true; }
        if (!encontrado) System.out.println("No hay contactos en ese grupo.");
    }
    public boolean eliminarContacto(String nombre) {
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                contactos.remove(i); guardarEnArchivo(); return true;
            }
        }
        return false;
    }
    public void guardarEnArchivo() {
        try (PrintWriter salida = new PrintWriter(new FileWriter(archivo))) {
            for (Contacto c : contactos) salida.println(c.getNombre() + "\t" + c.getTelefono() + "\t" + c.getEmail() + "\t" + c.getGrupo());
        } catch (IOException e) { System.out.println("No se pudo guardar la agenda."); }
    }
    public void cargarDesdeArchivo() {
        File f = new File(archivo);
        if (!f.exists()) return;
        try (BufferedReader entrada = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                String[] datos = linea.split("\\t", -1);
                if (datos.length == 4) contactos.add(new Contacto(datos[0], datos[1], datos[2], datos[3]));
            }
        } catch (IOException e) { System.out.println("No se pudo cargar la agenda."); }
    }
}
