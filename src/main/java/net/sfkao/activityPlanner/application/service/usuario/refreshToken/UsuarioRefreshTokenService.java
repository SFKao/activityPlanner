package net.sfkao.activityPlanner.application.service.usuario.refreshToken;

import net.sfkao.activityPlanner.domain.dto.LoginDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UsuarioRefreshTokenService {


    Optional<LoginDTO> refreshToken(String refreshToken);

}
