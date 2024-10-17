package net.sfkao.activityPlanner.usuario.infraestructure.refreshToken;


import io.jsonwebtoken.ExpiredJwtException;
import net.sfkao.activityPlanner.usuario.application.refreshToken.UsuarioRefreshTokenService;
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
public class UsuarioRefreshTokenController {

    @Autowired
    UsuarioRefreshTokenService usuarioRefreshTokenService;

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refresh(@RequestBody String refreshToken) {
        try {
            Optional<LoginDTO> loginDTO = usuarioRefreshTokenService.refreshToken(refreshToken);
            if (loginDTO.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(loginDTO);
        } catch (ExpiredJwtException expiredJwtException) {
            return ResponseEntity.badRequest().body("El token ha expirado");
        }
    }

}
