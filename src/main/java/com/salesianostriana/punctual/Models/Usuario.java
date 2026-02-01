package com.salesianostriana.punctual.Models;

import com.salesianostriana.punctual.Models.Enums.Rol;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id@GeneratedValue
    private Long id;
    @UniqueElements
    private String email;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @ManyToOne
    private Departamento departamento;

    @ManyToOne
    private Workplace workplace;
}
