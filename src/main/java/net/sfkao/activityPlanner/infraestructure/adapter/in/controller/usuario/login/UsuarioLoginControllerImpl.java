package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.usuario.login;


import net.sfkao.activityPlanner.application.port.in.usuario.UsuarioLoginPort;
import net.sfkao.activityPlanner.domain.dto.AuthDTO;
import net.sfkao.activityPlanner.domain.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UsuarioLoginControllerImpl implements UsuarioLoginController, UsuarioLoginPort {

    @Autowired
    @Qualifier("UsuarioLoginService")
    UsuarioLoginPort usuarioLoginPort;

    @Override
    public ResponseEntity<?> loginResponse(AuthDTO authDTO) {
        Optional<LoginDTO> login = login(authDTO);
        if (login.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(login);
    }

    @Override
    public Optional<LoginDTO> login(AuthDTO authDTO) {
        return usuarioLoginPort.login(authDTO);
    }
}
