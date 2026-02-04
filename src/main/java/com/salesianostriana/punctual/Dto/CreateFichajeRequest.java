package com.salesianostriana.punctual.Dto;

import com.salesianostriana.punctual.Models.Enums.Tipo;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record CreateFichajeRequest(
        @NotNull(message = "El ID de usuario es obligatorio")
        Long usuarioId,

        Long workplaceId,

        @NotNull(message = "La latitud es obligatoria")
        @Range(min = -90, max = 90)
        Double latitud,

        @NotNull(message = "La longitud es obligatoria")
        @Range(min = -180, max = 180)
        Double longitud,

        @NotNull(message = "El tipo de fichaje (ENTRADA/SALIDA) es obligatorio")
        Tipo tipo,

        String comentarios
) {}