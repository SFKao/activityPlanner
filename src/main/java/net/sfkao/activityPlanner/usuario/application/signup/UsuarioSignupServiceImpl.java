package net.sfkao.activityPlanner.usuario.application.signup;

import net.sfkao.activityPlanner.usuario.domain.dto.UsuarioDTO;
import net.sfkao.activityPlanner.usuario.domain.model.Usuario;
import net.sfkao.activityPlanner.usuario.domain.model.UsuarioElastic;
import net.sfkao.activityPlanner.usuario.domain.repository.UsuarioElasticRepository;
import net.sfkao.activityPlanner.usuario.domain.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSignupServiceImpl implements UsuarioSignupService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioElasticRepository usuarioElasticRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public Usuario signup(UsuarioDTO usuarioDTO) {

        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        UsuarioElastic usuarioElastic = modelMapper.map(usuario, UsuarioElastic.class);

        usuario.setHashedPass(passwordEncoder.encode(usuarioDTO.getPass()));

        usuario = usuarioRepository.save(usuario);
        usuarioElasticRepository.save(usuarioElastic);

        return usuario;
    }

}
