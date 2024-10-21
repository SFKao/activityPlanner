package net.sfkao.activityPlanner.usuario.application.refreshToken;

import net.sfkao.activityPlanner.usuario.domain.dto.LoginDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UsuarioRefreshTokenService {


    Optional<LoginDTO> refreshToken(String refreshToken);

}
