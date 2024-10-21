package net.sfkao.activityPlanner.usuario.application.refreshToken;

import io.jsonwebtoken.ExpiredJwtException;
import net.sfkao.activityPlanner.security.JwtService;
import net.sfkao.activityPlanner.usuario.application.generateRefreshToken.UsuarioGenerateRefreshTokenService;
import net.sfkao.activityPlanner.usuario.domain.dto.LoginDTO;
import net.sfkao.activityPlanner.usuario.domain.model.Usuario;
import net.sfkao.activityPlanner.usuario.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class UsuarioRefreshTokenServiceImpl implements UsuarioRefreshTokenService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    UsuarioGenerateRefreshTokenService usuarioGenerateRefreshTokenService;

    @Value("${security.jwt.refresh-expiration-time}")
    private long refreshExpirationTime;

    @Override
    public Optional<LoginDTO> refreshToken(String refreshToken) throws ExpiredJwtException {
        Optional<Usuario> usuario = usuarioRepository.findByRefreshToken(refreshToken);
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
