package com.salesianostriana.punctual.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "modalidades")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Modalidad {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre; // Ej: "Teletrabajo 100%"

    private String descripcion;
}