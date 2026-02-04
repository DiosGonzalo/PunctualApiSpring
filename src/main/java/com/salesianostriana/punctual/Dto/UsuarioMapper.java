package com.salesianostriana.punctual.Dto;

import com.salesianostriana.punctual.Models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "departamento", source = "departamento.nombre")
    @Mapping(target = "modalidad", source = "modalidad.nombre")
    @Mapping(target = "horario", source = "horario.nombre")
    UsuarioResponse toUsuarioResponse(Usuario usuario);
}