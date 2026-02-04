package com.salesianostriana.punctual.Dto;

import com.salesianostriana.punctual.Dto.FichajeResponse;
import com.salesianostriana.punctual.Models.Fichaje;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // Vital para poder inyectarlo
public interface FichajeMapper {

    // Mapeo personalizado: Saca el nombre del objeto Usuario
    @Mapping(target = "nombreUsuario", source = "usuario.nombre")

    // Mapeo condicional: Si workplace es null, pon "Remoto" (Esto se puede hacer con expresiones Java)
    @Mapping(target = "nombreWorkplace", source = "workplace.nombre", defaultValue = "Remoto")
    FichajeResponse toFichajeResponse(Fichaje fichaje);
}