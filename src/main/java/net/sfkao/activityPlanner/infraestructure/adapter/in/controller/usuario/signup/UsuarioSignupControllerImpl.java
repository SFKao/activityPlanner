package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.usuario.signup;

import net.sfkao.activityPlanner.application.port.in.usuario.UsuarioSignupPort;
import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.dto.UsuarioDTO;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioSignupControllerImpl implements UsuarioSignupController, UsuarioSignupPort {

    @Autowired
    @Qualifier("UsuarioSignupService")
    UsuarioSignupPort usuarioSignupPort;

    UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    @Override
    public ResponseEntity<?> signupResponse(UsuarioDTO usuarioDTO) {
        Usuario register = signup(usuarioDTO);
        return ResponseEntity.ok(usuarioMapper.toDTO(register));
    }

    @Override
    public Usuario signup(UsuarioDTO usuarioDTO) {
        return usuarioSignupPort.signup(usuarioDTO);
    }
}
