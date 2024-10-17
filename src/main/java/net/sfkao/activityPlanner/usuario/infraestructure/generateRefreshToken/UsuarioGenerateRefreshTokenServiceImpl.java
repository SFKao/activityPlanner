package net.sfkao.activityPlanner.usuario.infraestructure.generateRefreshToken;

import net.sfkao.activityPlanner.security.JwtService;
import net.sfkao.activityPlanner.usuario.domain.Usuario;
import net.sfkao.activityPlanner.usuario.domain.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class UsuarioGenerateRefreshTokenServiceImpl implements UsuarioGenerateRefreshTokenService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    JwtService jwtService;

    @Value("${security.jwt.refresh-expiration-time}")
    private long refreshExpirationTime;

    @Override
    public void generateNewRefreshToken(Usuario usuario) {
        usuario.setRefreshTokenExpiration(Instant.now().plus(refreshExpirationTime, ChronoUnit.MILLIS));
        usuario.setRefreshToken(jwtService.generateRefreshToken(usuario));
        usuarioRepository.save(usuario);
    }

}
