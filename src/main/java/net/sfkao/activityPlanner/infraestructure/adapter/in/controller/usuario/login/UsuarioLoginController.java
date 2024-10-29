package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.usuario.login;

import net.sfkao.activityPlanner.domain.dto.AuthDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
public interface UsuarioLoginController {

    @PostMapping
    ResponseEntity<?> loginResponse(@RequestBody AuthDTO authDTO);
}
