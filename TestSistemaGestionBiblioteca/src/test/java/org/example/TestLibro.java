package org.example;

import org.example.models.EstadoLibro;
import org.example.models.Libro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLibro {
    @Test
    void testCrearLibro() {
        Libro libro = new Libro("123-456-789", "Los 4 Acuerdos", "Miguel Angel Ruiz Macías", EstadoLibro.DISPONIBLE);
        assertEquals("123-456-789", libro.getISBN());
        assertEquals("Los 4 Acuerdos", libro.getTitulo());
        assertEquals("Miguel Angel Ruiz Macías", libro.getAutor());
        assertEquals(EstadoLibro.DISPONIBLE, libro.getEstado());
    }

    @Test
    void testSetISBN() {
        Libro libro = new Libro("123-456-789", "El Amante Japones", "Isabel Allende", EstadoLibro.DISPONIBLE);
        libro.setISBN("789-456-123");
        assertEquals("789-456-123", libro.getISBN());
    }

    @Test
    void testSetTitulo() {
        Libro libro = new Libro("123-456-789", "El Amante Japones", "Isabel Allende", EstadoLibro.DISPONIBLE);
        libro.setTitulo("La Casa de los Espiritus");
        assertEquals("La Casa de los Espiritus", libro.getTitulo());
    }

    @Test
    void testSetAutor() {
        Libro libro = new Libro("123-456-789", "El Amante Japones", "Isabel Allende", EstadoLibro.DISPONIBLE);
        libro.setAutor("Jane Austen");
        assertEquals("Jane Austen", libro.getAutor());
    }

    @Test
    void cambiarEstadoLibro() {
        Libro libro = new Libro("789-456-123", "Eva Luna", "Isabel Allende", EstadoLibro.DISPONIBLE);
        assertEquals(EstadoLibro.DISPONIBLE, libro.getEstado());
        libro.setEstado(EstadoLibro.PRESTADO);
        assertEquals(EstadoLibro.PRESTADO, libro.getEstado());
    }
}
