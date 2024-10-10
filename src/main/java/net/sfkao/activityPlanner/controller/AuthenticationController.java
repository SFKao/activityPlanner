package net.sfkao.activityPlanner.controller;


import net.sfkao.activityPlanner.mapper.ModelMapperSingleton;
import net.sfkao.activityPlanner.model.Usuario;
import net.sfkao.activityPlanner.model.dto.LoginDTO;
import net.sfkao.activityPlanner.model.dto.LoginTryDTO;
import net.sfkao.activityPlanner.model.dto.UsuarioDTO;
import net.sfkao.activityPlanner.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UsuarioDTO usuarioDTO){
        Usuario register = usuarioService.register(usuarioDTO);
        return ResponseEntity.ok(ModelMapperSingleton.getModelMapper().map(register, UsuarioDTO.class));

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginTryDTO loginTryDTO){
        Optional<LoginDTO> login = usuarioService.login(loginTryDTO);
        if(login.isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(login);
    }



}
