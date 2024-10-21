package net.sfkao.activityPlanner.usuario.infraestructure.signup;

import net.sfkao.activityPlanner.usuario.domain.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public interface UsuarioSignupController {

    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody UsuarioDTO usuarioDTO);

}
