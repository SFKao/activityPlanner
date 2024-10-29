package net.sfkao.activityPlanner.application.port.in.usuario;

import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.dto.UsuarioDTO;

public interface UsuarioSignupPort {

    Usuario signup(UsuarioDTO usuarioDTO);

}
