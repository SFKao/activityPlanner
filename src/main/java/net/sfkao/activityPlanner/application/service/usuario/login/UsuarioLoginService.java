package net.sfkao.activityPlanner.application.service.usuario.login;

import net.sfkao.activityPlanner.domain.dto.AuthDTO;
import net.sfkao.activityPlanner.domain.dto.LoginDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UsuarioLoginService {

    Optional<LoginDTO> login(AuthDTO authDTO);

}
