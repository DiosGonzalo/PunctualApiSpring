package com.salesianostriana.punctual.Dto;

import com.salesianostriana.punctual.Models.Enums.Tipo;
import java.time.LocalDateTime;

public record FichajeResponse(
        Long id,
        String nombreUsuario,    // No devolvemos el objeto Usuario entero
        String nombreWorkplace,  // Solo el nombre, o "Remoto"
        LocalDateTime fechaYHora,
        Tipo tipo,
        boolean esFraudulento,   // El frontend pintará esto en rojo si es true
        String comentarios
) {}