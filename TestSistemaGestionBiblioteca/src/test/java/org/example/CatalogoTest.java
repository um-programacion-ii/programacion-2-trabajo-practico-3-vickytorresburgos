package org.example;

import org.example.models.Catalogo;
import org.example.models.EstadoLibro;
import org.example.models.Libro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;

public class TestCatalogo {
    @Test
    void agregarLibro() {
        Catalogo catalogo = new Catalogo();
        Libro libro = new Libro("123-456-789", "El Alquimista","Paulo Coelho", EstadoLibro.DISPONIBLE);
        catalogo.agregarLibro(libro);
        Collection<Libro> librosCatalogo = catalogo.obtenerLibros();
        assertEquals(1, librosCatalogo.size());
        assertTrue(librosCatalogo.contains(libro));
    }

    @Test
    void buscarLibroExistente(){
        Catalogo catalogo = new Catalogo();
        Libro libro = new Libro("123-456-789", "El Alquimista","Paulo Coelho", EstadoLibro.DISPONIBLE);
        catalogo.agregarLibro(libro);
        Libro libroEncontrado = catalogo.buscarLibroPorISBN("123-456-789");
        assertEquals("El Alquimista", libroEncontrado.getTitulo());
    }

    @Test
    void buscarLibroNoExistente() {
        Catalogo catalogo = new Catalogo();
        Libro libro = new Libro("123-456-789", "El Alquimista","Paulo Coelho", EstadoLibro.DISPONIBLE);
        catalogo.agregarLibro(libro);
        Libro libroNoEncontrado = catalogo.buscarLibroPorISBN("789-456-132");
        assertNull(libroNoEncontrado);
    }

    @Test
    void obtenerListaLibros() {
        Catalogo catalogo = new Catalogo();
        Libro libro1 = new Libro("123-456-789", "El Alquimista","Paulo Coelho", EstadoLibro.DISPONIBLE);
        Libro libro2 = new Libro("789-456-132", "La Casa de los Espiritus","Isabel Allende", EstadoLibro.DISPONIBLE);
        Libro libro3 = new Libro("456-789-123", "Las Cosas que Perdimos en Fuego","Mariana Enriquez", EstadoLibro.DISPONIBLE);
        Libro libro4 = new Libro("147-258-369", "Eva Luna","Isabel Allende", EstadoLibro.DISPONIBLE);
        catalogo.agregarLibro(libro1);
        catalogo.agregarLibro(libro2);
        catalogo.agregarLibro(libro3);
        catalogo.agregarLibro(libro4);
        Collection<Libro> librosCatalogo = catalogo.obtenerLibros();
        assertEquals(4, librosCatalogo.size());
        assertTrue(librosCatalogo.contains(libro1));
        assertTrue(librosCatalogo.contains(libro2));
        assertTrue(librosCatalogo.contains(libro3));
        assertTrue(librosCatalogo.contains(libro4));
    }
}
