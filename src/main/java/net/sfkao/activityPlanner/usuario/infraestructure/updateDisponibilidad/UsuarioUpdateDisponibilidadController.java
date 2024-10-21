package net.sfkao.activityPlanner.usuario.infraestructure.updateDisponibilidad;

import net.sfkao.activityPlanner.usuario.domain.dto.DisponibilidadDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public interface UsuarioUpdateDisponibilidadController {

    @PutMapping("/updateDisponibilidad")
    ResponseEntity<?> updateDisponibilidad(@RequestBody List<DisponibilidadDTO> disponibilidadDTOS, Principal user);

}

