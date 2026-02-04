package com.salesianostriana.punctual.Dto;
import com.salesianostriana.punctual.Dto.HorarioDto;
import com.salesianostriana.punctual.Dto.ModalidadDto;
import com.salesianostriana.punctual.Models.Horario;
import com.salesianostriana.punctual.Models.Modalidad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfigMapper {
    ModalidadDto toModalidadDTO(Modalidad modalidad);
    Modalidad toModalidad(ModalidadDto dto);

    HorarioDto toHorarioDTO(Horario horario);
    Horario toHorario(HorarioDto dto);
}
