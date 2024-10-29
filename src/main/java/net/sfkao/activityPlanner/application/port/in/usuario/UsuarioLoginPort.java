package net.sfkao.activityPlanner.application.port.in.usuario;

import net.sfkao.activityPlanner.domain.dto.AuthDTO;
import net.sfkao.activityPlanner.domain.dto.LoginDTO;

import java.util.Optional;

public interface UsuarioLoginPort {

    Optional<LoginDTO> login(AuthDTO authDTO);

}
