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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SistemaPrestamoTest {
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
        Libro libro = new Libro("123-456-797", "Demian", "Herman Hesse", EstadoLibro.DISPONIBLE);
        when(catalogo.buscarLibroPorISBN("123-456-797")).thenReturn(libro);
        Prestamo prestamo = sistemaPrestamo.registrarPrestamo("123-456-797");
        assertNotNull(prestamo);
        verify(catalogo).buscarLibroPorISBN("123-456-797");
        assertEquals(EstadoLibro.PRESTADO, libro.getEstado());
    }

    @Test
    void testRegistrarPrestamoLibroNoEncontrado() {
        Catalogo catalogoMock = mock(Catalogo.class);
        when(catalogoMock.buscarLibroPorISBN("999")).thenReturn(null);
        SistemaPrestamo sistema = new SistemaPrestamo(catalogoMock, new ArrayList<>());
        Exception ex = assertThrows(LibroNoEncontradoException.class, () -> sistema.registrarPrestamo("999"));
        assertEquals("El libro no fue encontrado", ex.getMessage());
    }

    @Test
    void testRegistrarPrestamoLibroNoDisponible() {
        Catalogo catalogoMock = mock(Catalogo.class);
        Libro libroMock = mock(Libro.class);
        when(catalogoMock.buscarLibroPorISBN("123")).thenReturn(libroMock);
        when(libroMock.getEstado()).thenReturn(EstadoLibro.PRESTADO);
        SistemaPrestamo sistema = new SistemaPrestamo(catalogoMock, new ArrayList<>());
        Exception ex = assertThrows(LibroNoDisponibleException.class, () -> sistema.registrarPrestamo("123"));
        assertEquals("El libro no esta disponible en este momento", ex.getMessage());
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

    @Test
    void testBuscarPrestamoPorISBNExiste() {
        Libro libroMock = mock(Libro.class);
        when(libroMock.getISBN()).thenReturn("321");
        Prestamo prestamoMock = mock(Prestamo.class);
        when(prestamoMock.getLibro()).thenReturn(libroMock);
        List<Prestamo> lista = List.of(prestamoMock);
        SistemaPrestamo sistema = new SistemaPrestamo(null, lista);
        Prestamo resultado = sistema.buscarPrestamoPorISBN("321");
        assertNotNull(resultado);
        assertEquals(prestamoMock, resultado);
    }

    @Test
    void testBuscarPrestamoPorISBNNoExiste() {
        Libro libroMock = mock(Libro.class);
        when(libroMock.getISBN()).thenReturn("000");
        Prestamo prestamoMock = mock(Prestamo.class);
        when(prestamoMock.getLibro()).thenReturn(libroMock);
        List<Prestamo> lista = List.of(prestamoMock);
        SistemaPrestamo sistema = new SistemaPrestamo(null, lista);
        Prestamo resultado = sistema.buscarPrestamoPorISBN("999");
        assertNull(resultado);
    }

    @Test
    void testGetPrestamosRetornaCopia() {
        Prestamo prestamoMock = mock(Prestamo.class);
        List<Prestamo> lista = new ArrayList<>();
        lista.add(prestamoMock);
        SistemaPrestamo sistema = new SistemaPrestamo(null, lista);
        List<Prestamo> copia1 = sistema.getPrestamos();
        List<Prestamo> copia2 = sistema.getPrestamos();
        assertEquals(copia1, copia2);
        assertNotSame(copia1, copia2);
    }
}
