package net.sfkao.activityPlanner.usuario.application.login;

import net.sfkao.activityPlanner.usuario.domain.dto.AuthDTO;
import net.sfkao.activityPlanner.usuario.domain.dto.LoginDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UsuarioLoginService {

    Optional<LoginDTO> login(AuthDTO authDTO);

}
