package org.example.exceptions;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String msg) {
      super(msg);
    }
}
