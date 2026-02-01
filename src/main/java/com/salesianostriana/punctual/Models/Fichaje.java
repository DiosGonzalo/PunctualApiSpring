package com.salesianostriana.punctual.Models;

import com.salesianostriana.punctual.Models.Enums.Tipo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fichaje {
    @Id@GeneratedValue
    private Long id;
    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Workplace workplace;

    private LocalDateTime fechaYHora;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
}
