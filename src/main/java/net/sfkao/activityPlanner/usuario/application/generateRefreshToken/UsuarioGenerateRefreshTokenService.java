package net.sfkao.activityPlanner.usuario.application.generateRefreshToken;

import net.sfkao.activityPlanner.usuario.domain.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioGenerateRefreshTokenService {

    void generateNewRefreshToken(Usuario usuario);

}
