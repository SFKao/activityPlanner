package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.usuario.signup;

import net.sfkao.activityPlanner.domain.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/signup")
public interface UsuarioSignupController {

    @PostMapping
    ResponseEntity<?> signupResponse(@RequestBody UsuarioDTO usuarioDTO);

}
