package net.sfkao.activityPlanner.usuario.infraestructure.login;


import net.sfkao.activityPlanner.usuario.application.login.UsuarioLoginService;
import net.sfkao.activityPlanner.usuario.domain.AuthDTO;
import net.sfkao.activityPlanner.usuario.domain.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UsuarioLoginController {

    @Autowired
    UsuarioLoginService usuarioLoginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO) {
        Optional<LoginDTO> login = usuarioLoginService.login(authDTO);
        if (login.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(login);
    }

}
