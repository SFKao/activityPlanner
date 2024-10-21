package net.sfkao.activityPlanner.usuario.infraestructure.signup;

import net.sfkao.activityPlanner.usuario.application.signup.UsuarioSignupService;
import net.sfkao.activityPlanner.usuario.domain.Usuario;
import net.sfkao.activityPlanner.usuario.domain.UsuarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioSignupControllerImpl {

    @Autowired
    UsuarioSignupService usuarioSignupService;

    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<?> signup(UsuarioDTO usuarioDTO) {
        Usuario register = usuarioSignupService.signup(usuarioDTO);
        return ResponseEntity.ok(modelMapper.map(register, UsuarioDTO.class));
    }

}
