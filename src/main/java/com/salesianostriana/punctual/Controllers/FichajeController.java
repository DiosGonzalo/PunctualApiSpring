package com.salesianostriana.punctual.Controllers;

import com.salesianostriana.punctual.Dto.CreateFichajeRequest;
import com.salesianostriana.punctual.Dto.FichajeResponse;
import com.salesianostriana.punctual.Dto.CreateFichajeRequest;
import com.salesianostriana.punctual.Dto.FichajeMapper;
import com.salesianostriana.punctual.Dto.FichajeResponse;
import com.salesianostriana.punctual.Dto.FichajeMapper;
import com.salesianostriana.punctual.Models.Fichaje;
import com.salesianostriana.punctual.Services.FichajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fichajes")
@RequiredArgsConstructor
@Tag(name = "Fichajes", description = "Operaciones de registro de tiempo y control de fraude")
public class FichajeController {

    private final FichajeService fichajeService;
    private final FichajeMapper fichajeMapper;

    @PostMapping
    @Operation(summary = "Registrar entrada/salida", description = "Registra un fichaje validando la geolocalización.")
    public ResponseEntity<FichajeResponse> fichar(@Valid @RequestBody CreateFichajeRequest request) {

        // 1. Llamamos al servicio con los datos "desempaquetados" del DTO
        Fichaje nuevoFichaje = fichajeService.registrarFichaje(
                request.usuarioId(),
                request.workplaceId(),
                request.latitud(),
                request.longitud(),
                request.tipo(),
                request.comentarios()
        );

        // 2. Convertimos la Entidad resultante a DTO de respuesta
        FichajeResponse response = fichajeMapper.toFichajeResponse(nuevoFichaje);

        // 3. Devolvemos 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}