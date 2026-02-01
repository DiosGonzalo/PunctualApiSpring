package com.salesianostriana.punctual.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Departamento {
    @Id@GeneratedValue
    private Long id;

    private String nombre;
}
