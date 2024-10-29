package net.sfkao.activityPlanner.application.service.usuario.refreshToken;

import io.jsonwebtoken.ExpiredJwtException;
import net.sfkao.activityPlanner.application.port.in.usuario.UsuarioRefreshTokenPort;
import net.sfkao.activityPlanner.application.port.out.usuario.UsuarioPersistencePort;
import net.sfkao.activityPlanner.application.service.jwt.JwtService;
import net.sfkao.activityPlanner.application.service.usuario.generateRefreshToken.UsuarioGenerateRefreshTokenService;
import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@Qualifier("UsuarioRefreshTokenService")
public class UsuarioRefreshTokenServiceImpl implements UsuarioRefreshTokenService, UsuarioRefreshTokenPort {

    @Autowired
    UsuarioPersistencePort usuarioPersistencePort;

    @Autowired
    JwtService jwtService;

    @Autowired
    UsuarioGenerateRefreshTokenService usuarioGenerateRefreshTokenService;

    @Value("${security.jwt.refresh-expiration-time}")
    private long refreshExpirationTime;

    @Override
    public Optional<LoginDTO> refreshToken(String refreshToken) throws ExpiredJwtException {
        Optional<Usuario> usuario = usuarioPersistencePort.findByRefreshToken(refreshToken);
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        if (usuario.get().getRefreshTokenExpiration().isBefore(Instant.now())) {
            throw new ExpiredJwtException(null, null, "El token expiro");
        }
        String token = jwtService.generateToken(usuario.get());
        usuarioGenerateRefreshTokenService.generateNewRefreshToken(usuario.get());
        LoginDTO loginDTO = new LoginDTO(token, jwtService.getExpirationTime(), usuario.get().getRefreshToken(), refreshExpirationTime);
        return Optional.of(loginDTO);
    }

}
