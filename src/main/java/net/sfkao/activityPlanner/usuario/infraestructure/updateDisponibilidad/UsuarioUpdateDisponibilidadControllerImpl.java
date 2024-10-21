package net.sfkao.activityPlanner.usuario.infraestructure.updateDisponibilidad;

import net.sfkao.activityPlanner.exception.UsuarioNotFoundException;
import net.sfkao.activityPlanner.usuario.application.updateDisponibilidad.UsuarioUpdateDisponibilidadService;
import net.sfkao.activityPlanner.usuario.domain.dto.DisponibilidadDTO;
import net.sfkao.activityPlanner.usuario.domain.model.Disponibilidad;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class UsuarioUpdateDisponibilidadControllerImpl implements UsuarioUpdateDisponibilidadController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UsuarioUpdateDisponibilidadService usuarioUpdateDisponibilidadService;

    @Override
    public ResponseEntity<?> updateDisponibilidad(List<DisponibilidadDTO> disponibilidadDTOS, Principal user) {

        try {
            List<Disponibilidad> actividads = usuarioUpdateDisponibilidadService.updateDisponibilidades(user.getName(), disponibilidadDTOS.stream().map(dis -> modelMapper.map(dis, Disponibilidad.class))
                    .toList());
            return ResponseEntity.ok(actividads.stream().map(dis -> modelMapper.map(dis, DisponibilidadDTO.class)).toList());
        } catch (UsuarioNotFoundException e) {
            return ResponseEntity.status(403).body("No se encuentra tu usuario");
        }
    }
}
