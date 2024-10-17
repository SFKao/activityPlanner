package net.sfkao.activityPlanner.usuario.infraestructure.signup;

import net.sfkao.activityPlanner.usuario.application.signup.UsuarioSignupService;
import net.sfkao.activityPlanner.usuario.domain.Usuario;
import net.sfkao.activityPlanner.usuario.domain.UsuarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsuarioSignupController {

    @Autowired
    UsuarioSignupService usuarioSignupService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario register = usuarioSignupService.signup(usuarioDTO);
        return ResponseEntity.ok(modelMapper.map(register, UsuarioDTO.class));

    }

}
