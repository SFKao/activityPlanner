package net.sfkao.activityPlanner.service;

import net.sfkao.activityPlanner.model.Usuario;
import net.sfkao.activityPlanner.model.dto.LoginDTO;
import net.sfkao.activityPlanner.model.dto.LoginTryDTO;
import net.sfkao.activityPlanner.model.dto.UsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UsuarioService {

    Usuario register(UsuarioDTO usuarioDTO);

    Optional<LoginDTO> login(LoginTryDTO usuarioDTO);

    void reindex();

}
