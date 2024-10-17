package net.sfkao.activityPlanner.usuario.infraestructure.login;

import net.sfkao.activityPlanner.security.JwtService;
import net.sfkao.activityPlanner.usuario.domain.AuthDTO;
import net.sfkao.activityPlanner.usuario.domain.LoginDTO;
import net.sfkao.activityPlanner.usuario.domain.Usuario;
import net.sfkao.activityPlanner.usuario.domain.UsuarioRepository;
import net.sfkao.activityPlanner.usuario.infraestructure.generateRefreshToken.UsuarioGenerateRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioLoginServiceImpl implements UsuarioLoginService {

    @Autowired
    UsuarioRepository usuarioRepository;

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
        Optional<Usuario> usuario = usuarioRepository.findByUsernameOrEmail(authDTO.getUsernameOrEmail(), authDTO.getUsernameOrEmail());
        if (usuario.isEmpty() || !passwordEncoder.matches(authDTO.getPass(), usuario.get().getHashedPass())) {
            return Optional.empty();
        }
        String token = jwtService.generateToken(usuario.get());
        generateNewRefreshTokenService.generateNewRefreshToken(usuario.get());
        LoginDTO loginDTO = new LoginDTO(token, jwtService.getExpirationTime(), usuario.get().getRefreshToken(), refreshExpirationTime);
        return Optional.of(loginDTO);
    }

}
