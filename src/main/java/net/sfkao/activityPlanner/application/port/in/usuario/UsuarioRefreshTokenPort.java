package net.sfkao.activityPlanner.application.port.in.usuario;

import net.sfkao.activityPlanner.domain.dto.LoginDTO;

import java.util.Optional;

public interface UsuarioRefreshTokenPort {

    Optional<LoginDTO> refreshToken(String refreshToken);

}
