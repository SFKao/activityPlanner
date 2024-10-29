package net.sfkao.activityPlanner.application.service.usuario.signup;

import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.dto.UsuarioDTO;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioSignupService {

    Usuario signup(UsuarioDTO usuarioDTO);

}
