package net.sfkao.activityPlanner.usuario.infraestructure.generateRefreshToken;

import net.sfkao.activityPlanner.usuario.domain.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioGenerateRefreshTokenService {

    void generateNewRefreshToken(Usuario usuario);

}
