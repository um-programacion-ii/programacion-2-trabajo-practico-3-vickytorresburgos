package org.example.models;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Prestamo {
    private Libro libro;
    private LocalDate fechaPrestamo;

    public Prestamo(Libro libro) {
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
    }
}
