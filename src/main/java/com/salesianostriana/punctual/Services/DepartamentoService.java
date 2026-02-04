package com.salesianostriana.punctual.Services;

import com.salesianostriana.punctual.Models.Departamento;
import com.salesianostriana.punctual.Models.Usuario;
import com.salesianostriana.punctual.Repositories.DepartamentoRepository;
import com.salesianostriana.punctual.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Departamento crearDepartamento(String nombre) {
        // Creamos el departamento sin jefe inicialmente
        Departamento d = Departamento.builder()
                .nombre(nombre)
                .build();
        return departamentoRepository.save(d);
    }

    @Transactional
    public void asignarJefe(Long departamentoId, Long usuarioId) {
        Departamento depto = departamentoRepository.findById(departamentoId)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        Usuario nuevoJefe = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 1. Asignamos el usuario como jefe del departamento
        depto.setJefe(nuevoJefe);

        // 2. Opcional: Asignamos al usuario a ese departamento también
        nuevoJefe.setDepartamento(depto);

        // Al estar en una transacción y ser entidades gestionadas,
        // Hibernate hace el update solo al final del método.
        usuarioRepository.save(nuevoJefe);
        departamentoRepository.save(depto);
    }
}