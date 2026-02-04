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
    @Column(name = "telefono")
    private String telefono; // <--- LO QUE FALTABA EN PANTALLA USUARIOS

    // Relación con Modalidad (Muchos usuarios tienen una modalidad)
    @ManyToOne
    @JoinColumn(name = "modalidad_id")
    private Modalidad modalidad;

    // Relación con Horario (El turno que tiene asignado)
    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;
}
