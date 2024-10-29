package net.sfkao.activityPlanner.application.service.usuario.signup;

import net.sfkao.activityPlanner.application.port.in.usuario.UsuarioSignupPort;
import net.sfkao.activityPlanner.application.port.out.usuario.UsuarioPersistencePort;
import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.dto.UsuarioDTO;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Qualifier("UsuarioSignupService")
public class UsuarioSignupServiceImpl implements UsuarioSignupService, UsuarioSignupPort {

    @Autowired
    UsuarioPersistencePort usuarioPersistencePort;

    @Autowired
    PasswordEncoder passwordEncoder;

    UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    @Override
    public Usuario signup(UsuarioDTO usuarioDTO) {

        Usuario usuario = usuarioMapper.fromDTO(usuarioDTO);
        usuario.setHashedPass(passwordEncoder.encode(usuarioDTO.getPass()));
        usuario = usuarioPersistencePort.save(usuario);

        return usuario;
    }

}
