package org.example;

import org.example.models.EstadoLibro;
import org.example.models.Libro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestLibro {
    @Test
    void testCrearLibroValido() {
        Libro libro = new Libro("123-456-789", "La Metamorfosis", "Franz Kafka", EstadoLibro.DISPONIBLE);
        assertEquals("123-456-789", libro.getISBN());
        assertEquals("La Metamorfosis", libro.getTitulo());
        assertEquals("Franz Kafka", libro.getAutor());
        assertEquals(EstadoLibro.DISPONIBLE, libro.getEstado());
    }

    @Test
    void testSetISBN() {
        Libro libro = new Libro("456-789-132", "Eva Luna", "Isabel Allende", EstadoLibro.DISPONIBLE);
        libro.setISBN("123-456-789");
        assertEquals("123-456-789", libro.getISBN());
    }

    @Test
    void testSetTitulo() {
        Libro libro = new Libro("456-789-132", "Eva Luna", "Isabel Allende", EstadoLibro.DISPONIBLE);
        libro.setTitulo("Mi Planta de Naranja Lima");
        assertEquals("Mi Planta de Naranja Lima", libro.getTitulo());
    }


    @Test
    void testSetAutor() {
        Libro libro = new Libro("456-789-132", "Eva Luna", "Isabel Allende", EstadoLibro.DISPONIBLE);
        libro.setAutor("Mariana Enriquez");
        assertEquals("Mariana Enriquez", libro.getAutor());
    }

    @Test
    void testCambiarEstadoLibro() {
        Libro libro = new Libro("789-456-123", "La Casa de los Espiritus", "Isabel Allende", EstadoLibro.DISPONIBLE);
        libro.setEstado(EstadoLibro.PRESTADO);
        assertEquals(EstadoLibro.PRESTADO, libro.getEstado());
    }
}
