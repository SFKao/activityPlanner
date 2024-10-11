package net.sfkao.activityPlanner.service;

import io.jsonwebtoken.ExpiredJwtException;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
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

    @Value("${security.jwt.refresh-expiration-time}")
    private long refreshExpirationTime;

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
        generateNewRefreshToken(usuario.get());
        LoginDTO loginDTO = new LoginDTO(token, jwtService.getExpirationTime(), usuario.get().getRefreshToken(), refreshExpirationTime);
        return Optional.of(loginDTO);
    }

    @Override
    public Optional<LoginDTO> refreshToken(String refreshToken) throws ExpiredJwtException {
        Optional<Usuario> usuario = usuarioRepository.findByRefreshToken(refreshToken);
        if (usuario.isEmpty())
            return Optional.empty();
        if(usuario.get().getRefreshTokenExpiration().isBefore(Instant.now()))
            throw new ExpiredJwtException(null, null, "El token expiro");
        String token = jwtService.generateToken(usuario.get());
        generateNewRefreshToken(usuario.get());
        LoginDTO loginDTO = new LoginDTO(token, jwtService.getExpirationTime(), usuario.get().getRefreshToken(), refreshExpirationTime);
        return Optional.of(loginDTO);
    }

    @Override
    public void generateNewRefreshToken(Usuario usuario) {
        usuario.setRefreshTokenExpiration(Instant.now().plus(refreshExpirationTime, ChronoUnit.MILLIS));
        usuario.setRefreshToken(jwtService.generateRefreshToken(usuario));
        usuarioRepository.save(usuario);
    }

    @Override
    public void reindex() {
        usuarioElasticRepository.deleteAll();
        usuarioRepository.findAll().forEach(usuario -> ModelMapperSingleton.getModelMapper().map(usuario, UsuarioElastic.class));
    }


}
