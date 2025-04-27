package org.example.sistemas;


import lombok.Data;
import org.example.exceptions.UsuarioNoEncontradoException;
import org.example.models.Prestamo;
import org.example.models.Usuario;

import java.util.*;

@Data
public class SistemaUsuario {
    private List<Usuario> usuarios = new ArrayList<>();


    public void registrarUsuario(String nombre) {
        usuarios.add(new Usuario(nombre));
    }

    public Usuario buscarUsuarioNombre(String nombre) {
        for(Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return usuario;
            }
        }
        throw new UsuarioNoEncontradoException("No se encuentra el usuario ingresado");
    }

    public void registrarPrestamoUsuario(String nombreUsuario, Prestamo prestamo) {
        if (nombreUsuario == null || prestamo == null) {
            throw new IllegalArgumentException("Los datos ingresados no pueden ser nulos");
        }
        Usuario usuario = buscarUsuarioNombre(nombreUsuario);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("No se encuentra el usuario ingresado");
        }
        usuario.pedirPrestamo(prestamo);
    }

    public List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(usuarios);
    }
}
