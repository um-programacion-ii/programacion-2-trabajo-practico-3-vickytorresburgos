package org.example;

import org.example.exceptions.UsuarioNoEncontradoException;
import org.example.models.*;
import org.example.sistemas.SistemaPrestamo;
import org.example.sistemas.SistemaUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SistemaUsuarioTest {
    @Mock
    private SistemaPrestamo sistemaPrestamos;

    @InjectMocks
    private SistemaUsuario sistemaUsuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sistemaUsuario = new SistemaUsuario();
    }

    @Test
    void testRegistroUsuario() {
        SistemaUsuario sistemaSpy = spy(new SistemaUsuario());
        sistemaSpy.registrarUsuario("Victoria");
        List<Usuario> usuarios = sistemaSpy.obtenerUsuarios();
        assertEquals(1, usuarios.size());
        assertEquals("Victoria", usuarios.get(0).getNombre());
    }

    @Test
    void testBuscarUsuarioNombreExiste() {
        SistemaUsuario sistemaSpy = spy(new SistemaUsuario());
        sistemaSpy.registrarUsuario("Victoria");
        Usuario usuario = sistemaSpy.buscarUsuarioNombre("Victoria");
        assertNotNull(usuario);
        assertEquals("Victoria", usuario.getNombre());
    }

    @Test
    void testBuscarUsuarioNombreNoExiste() {
        SistemaUsuario sistemaSpy = spy(new SistemaUsuario());
        UsuarioNoEncontradoException ex = assertThrows(
                UsuarioNoEncontradoException.class,
                () -> sistemaSpy.buscarUsuarioNombre("Tadeo")
        );
        assertEquals("No se encuentra el usuario ingresado", ex.getMessage());
    }

    @Test
    void testRegistrarPrestamoUsuarioDatosInvalidos() {
        Prestamo prestamoMock = mock(Prestamo.class);
        IllegalArgumentException thrown1 = assertThrows(IllegalArgumentException.class, () ->
                sistemaUsuario.registrarPrestamoUsuario(null, prestamoMock));
        assertEquals("Los datos ingresados no pueden ser nulos", thrown1.getMessage());
        IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class, () ->
                sistemaUsuario.registrarPrestamoUsuario("Victoria", null));
        assertEquals("Los datos ingresados no pueden ser nulos", thrown2.getMessage());
    }

    @Test
    void testRegistrarPrestamoUsuarioUsuarioNoExiste() {
        SistemaUsuario sistemaSpy = spy(new SistemaUsuario());
        doThrow(new UsuarioNoEncontradoException("No se encuentra el usuario ingresado"))
                .when(sistemaSpy).buscarUsuarioNombre("Victoria");
        UsuarioNoEncontradoException thrown = assertThrows(
                UsuarioNoEncontradoException.class,
                () -> sistemaSpy.registrarPrestamoUsuario("Victoria", new Prestamo())
        );
        assertEquals("No se encuentra el usuario ingresado", thrown.getMessage());
    }

    @Test
    void testRegistrarPrestamoUsuarioExitoso() {
        Usuario usuarioMock = mock(Usuario.class);
        Prestamo prestamo = new Prestamo(mock(Libro.class));
        SistemaUsuario sistemaSpy = spy(new SistemaUsuario());
        doReturn(usuarioMock).when(sistemaSpy).buscarUsuarioNombre("Victoria");
        sistemaSpy.registrarPrestamoUsuario("Victoria", prestamo);
        verify(usuarioMock, times(1)).pedirPrestamo(prestamo);
    }

    @Test
    void testRegistrarPrestamoUsuarioLlamaPedirPrestamo_conMock() {
        Usuario usuarioMock = mock(Usuario.class);
        Prestamo prestamo = new Prestamo(mock(Libro.class));
        SistemaUsuario sistemaSpy = spy(new SistemaUsuario());
        doReturn(usuarioMock).when(sistemaSpy).buscarUsuarioNombre("Victoria");
        sistemaSpy.registrarPrestamoUsuario("Victoria", prestamo);
        verify(usuarioMock, times(1)).pedirPrestamo(prestamo);
    }
}
