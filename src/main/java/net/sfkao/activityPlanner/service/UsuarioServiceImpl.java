package net.sfkao.activityPlanner.service;

import net.sfkao.activityPlanner.mapper.ModelMapperSingleton;
import net.sfkao.activityPlanner.model.Usuario;
import net.sfkao.activityPlanner.model.dto.LoginDTO;
import net.sfkao.activityPlanner.model.dto.LoginTryDTO;
import net.sfkao.activityPlanner.model.dto.UsuarioDTO;
import net.sfkao.activityPlanner.model.elastic.UsuarioElastic;
import net.sfkao.activityPlanner.repository.elastic.UsuarioElasticRepository;
import net.sfkao.activityPlanner.repository.mongodb.UsuarioRepository;
import net.sfkao.activityPlanner.security.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioElasticRepository usuarioElasticRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;


    @Override
    public Usuario register(UsuarioDTO usuarioDTO) {

        ModelMapper modelMapper = ModelMapperSingleton.getModelMapper();

        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        UsuarioElastic usuarioElastic = modelMapper.map(usuario, UsuarioElastic.class);

        usuario.setHashedPass(passwordEncoder.encode(usuarioDTO.getPass()));

        usuario = usuarioRepository.save(usuario);
        usuarioElasticRepository.save(usuarioElastic);

        return usuario;
    }

    @Override
    public Optional<LoginDTO> login(LoginTryDTO loginTryDTO) {

        Optional<Usuario> usuario = usuarioRepository.findByUsernameOrEmail(loginTryDTO.getUsernameOrEmail(), loginTryDTO.getUsernameOrEmail());

        if (usuario.isEmpty() || !passwordEncoder.matches(loginTryDTO.getPass(), usuario.get().getHashedPass()))
            return Optional.empty();

        String token = jwtService.generateToken(usuario.get());

        LoginDTO loginDTO = new LoginDTO(token, jwtService.getExpirationTime());

        return Optional.of(loginDTO);

    }
}
