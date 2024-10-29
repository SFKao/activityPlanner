package net.sfkao.activityPlanner.application.service.usuario.login;

import net.sfkao.activityPlanner.application.port.in.usuario.UsuarioLoginPort;
import net.sfkao.activityPlanner.application.port.out.usuario.UsuarioPersistencePort;
import net.sfkao.activityPlanner.application.service.jwt.JwtService;
import net.sfkao.activityPlanner.application.service.usuario.generateRefreshToken.UsuarioGenerateRefreshTokenService;
import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.dto.AuthDTO;
import net.sfkao.activityPlanner.domain.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("UsuarioLoginService")
public class UsuarioLoginServiceImpl implements UsuarioLoginService, UsuarioLoginPort {

    @Autowired
    UsuarioPersistencePort usuarioPersistencePort;

    @Autowired
    UsuarioGenerateRefreshTokenService generateNewRefreshTokenService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Value("${security.jwt.refresh-expiration-time}")
    private long refreshExpirationTime;


    @Override
    public Optional<LoginDTO> login(AuthDTO authDTO) {
        Optional<Usuario> usuario = usuarioPersistencePort.findByUsernameOrEmail(authDTO.getUsernameOrEmail(), authDTO.getUsernameOrEmail());
        if (usuario.isEmpty() || !passwordEncoder.matches(authDTO.getPass(), usuario.get().getHashedPass())) {
            return Optional.empty();
        }
        String token = jwtService.generateToken(usuario.get());
        generateNewRefreshTokenService.generateNewRefreshToken(usuario.get());
        LoginDTO loginDTO = new LoginDTO(token, jwtService.getExpirationTime(), usuario.get().getRefreshToken(), refreshExpirationTime);
        return Optional.of(loginDTO);
    }

}
