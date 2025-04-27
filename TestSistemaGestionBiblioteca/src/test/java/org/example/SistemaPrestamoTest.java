package org.example;


import org.example.exceptions.LibroNoDisponibleException;
import org.example.exceptions.LibroNoEncontradoException;
import org.example.models.Catalogo;
import org.example.models.EstadoLibro;
import org.example.models.Libro;
import org.example.models.Prestamo;
import org.example.sistemas.SistemaPrestamo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestSistemaPrestamo {
    @Mock
    private Catalogo catalogo;

    @InjectMocks
    private SistemaPrestamo sistemaPrestamo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registrarPrestamoCorrecto() {
        Libro libro = new Libro("123-456-797","Demian", "Herman Hesse", EstadoLibro.DISPONIBLE);
        when(catalogo.buscarLibroPorISBN("123-456-797")).thenReturn(libro);
        Prestamo prestamo = sistemaPrestamo.registrarPrestamo("123-456-797");
        assertNotNull(prestamo);
        verify(catalogo).buscarLibroPorISBN("123-456-797");
        assertEquals(EstadoLibro.PRESTADO, libro.getEstado());
    }

    @Test
    void testPrestarLibroNoDisponible() {
        Libro libro = new Libro("789-456-123","La Casa de los Espiritus", "Isabel Allende", EstadoLibro.PRESTADO);
        when(catalogo.buscarLibroPorISBN("789-456-123")).thenReturn(libro);
        LibroNoDisponibleException thrown = assertThrows(
                LibroNoDisponibleException.class,
                () -> sistemaPrestamo.registrarPrestamo("789-456-123")
        );
        assertEquals("El libro no esta disponible en este momento", thrown.getMessage());
        verify(catalogo, times(1)).buscarLibroPorISBN("789-456-123");
    }

    @Test
    void testPrestarLibroNoExiste() {
        when(catalogo.buscarLibroPorISBN("123")).thenReturn(null);
        LibroNoEncontradoException thrown = assertThrows(
                LibroNoEncontradoException.class,
                () -> sistemaPrestamo.registrarPrestamo("123")
        );
        assertEquals("El libro no fue encontrado", thrown.getMessage());
        verify(catalogo, times(1)).buscarLibroPorISBN("123");
    }
}
