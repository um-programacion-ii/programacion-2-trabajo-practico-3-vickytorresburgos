package org.example.exceptions;

public class LibroNoEncontradoException extends RuntimeException {
    public LibroNoEncontradoException(String msg) {
        super(msg);
    }
}
