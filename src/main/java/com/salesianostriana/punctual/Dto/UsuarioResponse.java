package com.salesianostriana.punctual.Dto;

public record UsuarioResponse(
        Long id,
        String nombre,
        String email,
        String telefono,       // <--- Nuevo
        String departamento,
        String rol,
        String modalidad,      // <--- Nuevo (Devolvemos el nombre, no el objeto entero)
        String horario,        // <--- Nuevo (Ej: "Turno Mañana")
        boolean activo
) {}
