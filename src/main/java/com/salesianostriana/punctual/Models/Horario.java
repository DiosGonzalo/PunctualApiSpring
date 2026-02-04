package com.salesianostriana.punctual.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Entity
@Table(name = "horarios")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Horario {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nombre; // Ej: "Turno de Mañana"

    @Column(name = "hora_entrada", nullable = false)
    private LocalTime horaEntrada; // Ej: 08:00

    @Column(name = "hora_salida", nullable = false)
    private LocalTime horaSalida;  // Ej: 15:00

    // Días laborables (podrías hacerlo simple como un String "L-V" o complejo)
    // Para el TFG y Angular, un String descriptivo vale.
    private String diasLaborables;
}