package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.getDisponibilidadOfActividadesOfUsuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/activity")
public interface GetDisponibilidadOfActividadesOfUsuarioController {

    @GetMapping("/disponibilidadesUsuario")
    public ResponseEntity<?> getDisponibilidadesOfActividadesOfUsuarioResponse(Principal user);

}
