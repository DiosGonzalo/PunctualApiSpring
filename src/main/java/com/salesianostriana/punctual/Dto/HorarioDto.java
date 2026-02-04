package com.salesianostriana.punctual.Dto;
import java.time.LocalTime;
public record HorarioDto(
        Long id,
        String nombre,
        LocalTime horaEntrada,
        LocalTime horaSalida,
        String diasLaborables
) {
}
