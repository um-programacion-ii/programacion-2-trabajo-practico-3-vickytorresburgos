package org.example.models;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@Getter
@Setter

public class Libro {
    private String ISBN;
    private String titulo;
    private String autor;
    private EstadoLibro estado = EstadoLibro.DISPONIBLE;
}
