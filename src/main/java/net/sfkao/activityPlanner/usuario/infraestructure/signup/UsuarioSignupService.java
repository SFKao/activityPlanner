package net.sfkao.activityPlanner.usuario.infraestructure.signup;

import net.sfkao.activityPlanner.usuario.domain.Usuario;
import net.sfkao.activityPlanner.usuario.domain.UsuarioDTO;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioSignupService {

    Usuario signup(UsuarioDTO usuarioDTO);

}
