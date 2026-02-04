package com.salesianostriana.punctual.Controllers;

import com.salesianostriana.punctual.Dto.HorarioDto;
import com.salesianostriana.punctual.Dto.ConfigMapper;
import com.salesianostriana.punctual.Models.Horario;
import com.salesianostriana.punctual.Services.ConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/horarios")
@RequiredArgsConstructor
@Tag(name = "Configuración: Horarios", description = "Gestión de plantillas de horarios")
public class HorarioController {

    private final ConfigService configService;
    private final ConfigMapper configMapper;

    @GetMapping
    @Operation(summary = "Listar plantillas de horario")
    public ResponseEntity<List<HorarioDto>> getAll() {
        List<Horario> data = configService.findAllHorarios();
        List<HorarioDto> dtos = data.stream()
                .map(configMapper::toHorarioDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo horario")
    public ResponseEntity<HorarioDto> create(@RequestBody HorarioDto dto) {
        Horario nuevo = configService.saveHorario(configMapper.toHorario(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(configMapper.toHorarioDTO(nuevo));
    }
}