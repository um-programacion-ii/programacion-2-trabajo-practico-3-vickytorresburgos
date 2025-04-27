package org.example.sistemas;

import lombok.AllArgsConstructor;
import org.example.exceptions.LibroNoDisponibleException;
import org.example.exceptions.LibroNoEncontradoException;

import org.example.models.Catalogo;
import org.example.models.EstadoLibro;
import org.example.models.Libro;
import org.example.models.Prestamo;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SistemaPrestamo {
    private Catalogo catalogo;
    private List<Prestamo> prestamos;

    public SistemaPrestamo(Catalogo catalogo) {
        this.catalogo = catalogo;
        this.prestamos = new ArrayList<>();
    }

    public Prestamo registrarPrestamo(String ISBN) {
        Libro libro = catalogo.buscarLibroPorISBN(ISBN);
        if (libro == null) {
            throw new LibroNoEncontradoException("El libro no fue encontrado");
        }
        if (libro.getEstado() != EstadoLibro.DISPONIBLE) {
            throw new LibroNoDisponibleException("El libro no esta disponible en este momento");
        }

        libro.setEstado(EstadoLibro.PRESTADO);
        Prestamo prestamo = new Prestamo(libro);

        if (prestamos == null) {
            prestamos = new ArrayList<>();
        }
        prestamos.add(prestamo);
        return prestamo;
    }

    public Prestamo buscarPrestamoPorISBN(String ISBN) {
        for (Prestamo prestamo : prestamos){
            if (prestamo.getLibro().getISBN().equals(ISBN)) {
                    return prestamo;
            }
        }
        return null;
    }

    public List<Prestamo> getPrestamos() {
        return new ArrayList<>(prestamos);
    }
}


