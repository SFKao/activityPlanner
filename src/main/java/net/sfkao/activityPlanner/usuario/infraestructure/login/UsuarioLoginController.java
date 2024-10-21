package net.sfkao.activityPlanner.usuario.infraestructure.login;

import net.sfkao.activityPlanner.usuario.domain.dto.AuthDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
public interface UsuarioLoginController {

    @PostMapping
    ResponseEntity<?> login(@RequestBody AuthDTO authDTO);
}
