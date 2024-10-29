package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.usuario.refreshToken;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/refreshToken")
public interface UsuarioRefreshTokenController {

    @PostMapping
    ResponseEntity<?> refresh(@RequestBody String refreshToken);

}
