package net.sfkao.activityPlanner.usuario.infraestructure.signup;

import net.sfkao.activityPlanner.usuario.domain.Usuario;
import net.sfkao.activityPlanner.usuario.domain.UsuarioDTO;
import net.sfkao.activityPlanner.usuario.domain.UsuarioElastic;
import net.sfkao.activityPlanner.usuario.domain.UsuarioElasticRepository;
import net.sfkao.activityPlanner.usuario.domain.UsuarioRepository;
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
