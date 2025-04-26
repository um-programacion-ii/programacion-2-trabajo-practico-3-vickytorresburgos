package org.example.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Catalogo {
    private Map<String, Libro> libros;

    public Catalogo() {
        this.libros = new HashMap<>();
    }

    public void agregarLibro(Libro libro) {
        this.libros.put(libro.getISBN(), libro);
    }

    public Libro buscarLibroPorISBN (String ISBN) {
        return this.libros.get(ISBN);
    }

    public Collection<Libro> obtenerLibros() {
        return this.libros.values();
    }
}
