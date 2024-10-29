package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.usuario.updateDisponibilidad;

import net.sfkao.activityPlanner.application.service.usuario.updateDisponibilidad.UsuarioUpdateDisponibilidadService;
import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.dto.DisponibilidadDTO;
import net.sfkao.activityPlanner.domain.exception.UsuarioNotFoundException;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.mapper.DisponibilidadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class UsuarioUpdateDisponibilidadControllerImpl implements UsuarioUpdateDisponibilidadController {

    @Autowired
    UsuarioUpdateDisponibilidadService usuarioUpdateDisponibilidadService;

    DisponibilidadMapper disponibilidadMapper = DisponibilidadMapper.INSTANCE;

    @Override
    public ResponseEntity<?> updateDisponibilidad(List<DisponibilidadDTO> disponibilidadDTOS, Principal user) {

        try {
            List<Disponibilidad> actividads = usuarioUpdateDisponibilidadService.updateDisponibilidades(user.getName(), disponibilidadDTOS.stream().map(disponibilidadMapper::fromDTO)
                    .toList());
            return ResponseEntity.ok(actividads.stream().map(disponibilidadMapper::toDTO).toList());
        } catch (UsuarioNotFoundException e) {
            return ResponseEntity.status(403).body("No se encuentra tu usuario");
        }
    }
}
