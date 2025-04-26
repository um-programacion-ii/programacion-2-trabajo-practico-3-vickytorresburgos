package org.example.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private String ISBN;
    private String titulo;
    private String autor;
    private EstadoLibro estado = EstadoLibro.DISPONIBLE;
}
