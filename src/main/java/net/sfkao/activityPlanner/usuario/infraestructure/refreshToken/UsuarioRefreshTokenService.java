package net.sfkao.activityPlanner.usuario.infraestructure.refreshToken;

import net.sfkao.activityPlanner.usuario.domain.LoginDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UsuarioRefreshTokenService {


    Optional<LoginDTO> refreshToken(String refreshToken);

}
