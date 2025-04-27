package org.example.exceptions;

public class LibroNoDisponibleException extends RuntimeException {
    public LibroNoDisponibleException(String msg) {
        super(msg);
    }
}
