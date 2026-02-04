package com.salesianostriana.punctual.Services;

import com.salesianostriana.punctual.Models.Horario;
import com.salesianostriana.punctual.Models.Modalidad;
import com.salesianostriana.punctual.Repositories.HorarioRepository;
import com.salesianostriana.punctual.Repositories.ModalidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigService {

    private final ModalidadRepository modalidadRepository;
    private final HorarioRepository horarioRepository;

    // --- MODALIDADES ---
    public List<Modalidad> findAllModalidades() {
        return modalidadRepository.findAll();
    }

    public Modalidad saveModalidad(Modalidad modalidad) {
        return modalidadRepository.save(modalidad);
    }

    // --- HORARIOS ---
    public List<Horario> findAllHorarios() {
        return horarioRepository.findAll();
    }

    public Horario saveHorario(Horario horario) {
        return horarioRepository.save(horario);
    }
}