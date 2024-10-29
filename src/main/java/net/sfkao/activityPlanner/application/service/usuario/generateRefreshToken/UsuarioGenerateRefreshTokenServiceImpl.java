package net.sfkao.activityPlanner.application.service.usuario.generateRefreshToken;

import net.sfkao.activityPlanner.application.port.out.usuario.UsuarioPersistencePort;
import net.sfkao.activityPlanner.application.service.jwt.JwtService;
import net.sfkao.activityPlanner.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class UsuarioGenerateRefreshTokenServiceImpl implements UsuarioGenerateRefreshTokenService {

    @Autowired
    UsuarioPersistencePort usuarioPersistencePort;

    @Autowired
    JwtService jwtService;

    @Value("${security.jwt.refresh-expiration-time}")
    private long refreshExpirationTime;

    @Override
    public void generateNewRefreshToken(Usuario usuario) {
        usuario.setRefreshTokenExpiration(Instant.now().plus(refreshExpirationTime, ChronoUnit.MILLIS));
        usuario.setRefreshToken(jwtService.generateRefreshToken(usuario));
        usuarioPersistencePort.save(usuario);
    }

}
