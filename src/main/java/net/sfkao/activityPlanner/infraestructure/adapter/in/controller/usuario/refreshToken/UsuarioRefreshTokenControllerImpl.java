package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.usuario.refreshToken;


import io.jsonwebtoken.ExpiredJwtException;
import net.sfkao.activityPlanner.application.port.in.usuario.UsuarioRefreshTokenPort;
import net.sfkao.activityPlanner.domain.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UsuarioRefreshTokenControllerImpl implements UsuarioRefreshTokenController, UsuarioRefreshTokenPort {

    @Autowired
    @Qualifier("UsuarioRefreshTokenService")
    UsuarioRefreshTokenPort usuarioRefreshTokenService;

    @Override
    public ResponseEntity<?> refresh(String refreshToken) {
        try {
            Optional<LoginDTO> loginDTO = refreshToken(refreshToken);
            if (loginDTO.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(loginDTO);
        } catch (ExpiredJwtException expiredJwtException) {
            return ResponseEntity.badRequest().body("El token ha expirado");
        }
    }

    @Override
    public Optional<LoginDTO> refreshToken(String refreshToken) {
        return usuarioRefreshTokenService.refreshToken(refreshToken);
    }
}
