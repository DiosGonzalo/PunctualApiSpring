package com.salesianostriana.punctual.Controllers;

import com.salesianostriana.punctual.Dto.ModalidadDto;
import com.salesianostriana.punctual.Dto.ConfigMapper;
import com.salesianostriana.punctual.Models.Modalidad;
import com.salesianostriana.punctual.Services.ConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/modalidades")
@RequiredArgsConstructor
@Tag(name = "Configuración: Modalidades", description = "Gestión de tipos de trabajo (Remoto, Híbrido...)")
public class ModalidadController {

    private final ConfigService configService;
    private final ConfigMapper configMapper;

    @GetMapping
    @Operation(summary = "Listar modalidades", description = "Devuelve todas las modalidades disponibles")
    public ResponseEntity<List> getAll() {
        List<Modalidad> data = configService.findAllModalidades();
        // Truco Java Stream: Convertir lista de Entidad a lista de DTO
        List<ModalidadDto> dtos = data.stream()
                .map(configMapper::toModalidadDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    @Operation(summary = "Crear modalidad", description = "Crea un nuevo tipo de modalidad")
    public ResponseEntity<ModalidadDto> create(@RequestBody ModalidadDto dto) {
        Modalidad nuevo = configService.saveModalidad(configMapper.toModalidad(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(configMapper.toModalidadDTO(nuevo));
    }
}