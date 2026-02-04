package com.salesianostriana.punctual.Services;

import com.salesianostriana.punctual.Models.Fichaje;
import com.salesianostriana.punctual.Models.Usuario;
import com.salesianostriana.punctual.Models.Workplace;
import com.salesianostriana.punctual.Models.Enums.Tipo;
import com.salesianostriana.punctual.Repositories.FichajeRepository;
import com.salesianostriana.punctual.Repositories.UsuarioRepository;
import com.salesianostriana.punctual.Repositories.WorkplaceRepository;
import com.salesianostriana.punctual.Utils.GeoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Inyección de dependencias por constructor (Lombok)
public class FichajeService {

    private final FichajeRepository fichajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final WorkplaceRepository workplaceRepository;

    @Transactional // Si algo falla, rollback automático
    public Fichaje registrarFichaje(Long usuarioId, Long workplaceId,
                                    double latUsuario, double lonUsuario,
                                    Tipo tipo, String comentarios) {

        // 1. Validar Usuario
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")); // Usa tu excepción propia aquí

        // 2. Preparar Fichaje
        Fichaje nuevoFichaje = Fichaje.builder()
                .usuario(usuario)
                .fechaYHora(LocalDateTime.now())
                .tipo(tipo)
                .latitudUsuario(latUsuario)
                .longitudUsuario(lonUsuario)
                .comentarios(comentarios)
                .esFraudulento(false) // Por defecto confiamos
                .build();

        // 3. Validar Ubicación (Si ficha asociado a una oficina)
        if (workplaceId != null) {
            Workplace workplace = workplaceRepository.findById(workplaceId)
                    .orElseThrow(() -> new RuntimeException("Workplace no encontrado"));

            nuevoFichaje.setWorkplace(workplace);

            // --- LÓGICA FINTECH: GEOFENCING ---
            double distancia = GeoUtils.calcularDistanciaEnMetros(
                    latUsuario, lonUsuario,
                    workplace.getLatitud(), workplace.getLongitud()
            );

            // Si está más lejos del radio permitido, marcamos fraude pero guardamos el dato
            if (distancia > workplace.getRadioPermitido()) {
                nuevoFichaje.setEsFraudulento(true);
                nuevoFichaje.setComentarios(nuevoFichaje.getComentarios() + " [ALERTA: Fichaje fuera de rango: " + (int)distancia + "m]");
            }
        }

        // 4. Generar Hash de Seguridad (Cadena de bloques simplificada)
        // En un caso real, haríamos hash del fichaje anterior + datos actuales.
        // Aquí usamos UUID para simular un identificador único de transacción.
        nuevoFichaje.setHashSeguridad(UUID.randomUUID().toString());

        return fichajeRepository.save(nuevoFichaje);
    }
}