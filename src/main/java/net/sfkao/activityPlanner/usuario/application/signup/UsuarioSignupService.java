package net.sfkao.activityPlanner.usuario.application.signup;

import net.sfkao.activityPlanner.usuario.domain.dto.UsuarioDTO;
import net.sfkao.activityPlanner.usuario.domain.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioSignupService {

    Usuario signup(UsuarioDTO usuarioDTO);

}
