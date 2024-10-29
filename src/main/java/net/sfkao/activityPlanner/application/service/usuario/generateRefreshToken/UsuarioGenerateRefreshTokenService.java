package net.sfkao.activityPlanner.application.service.usuario.generateRefreshToken;

import net.sfkao.activityPlanner.domain.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioGenerateRefreshTokenService {

    void generateNewRefreshToken(Usuario usuario);

}
