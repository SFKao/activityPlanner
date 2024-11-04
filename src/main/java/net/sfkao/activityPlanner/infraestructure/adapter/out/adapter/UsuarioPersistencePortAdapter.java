package net.sfkao.activityPlanner.infraestructure.adapter.out.adapter;

import lombok.RequiredArgsConstructor;
import net.sfkao.activityPlanner.application.port.out.usuario.UsuarioPersistencePort;
import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.usuario.mapper.UsuarioElasticMapper;
import net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.usuario.repository.UsuarioElasticRepository;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.mapper.UsuarioMapper;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.repository.UsuarioRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioPersistencePortAdapter implements UsuarioPersistencePort {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioElasticRepository usuarioElasticRepository;

    UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    UsuarioElasticMapper usuarioElasticMapper = UsuarioElasticMapper.INSTANCE;

    @Override
    @Cacheable("usuarios")
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email).map(usuarioMapper::fromEntity);
    }

    @Override
    @Cacheable("usuarios")
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username).map(usuarioMapper::fromEntity);
    }

    @Override
    @Cacheable("usuarios")
    public Optional<Usuario> findByUsernameOrEmail(String email, String username) {
        return usuarioRepository.findByEmailOrUsername(email, username).map(usuarioMapper::fromEntity);
    }

    @Override
    @Cacheable("usuarios")
    public Optional<Usuario> findByRefreshToken(String refreshToken) {
        return usuarioRepository.findByRefreshToken(refreshToken).map(usuarioMapper::fromEntity);
    }

    @Override
    @CachePut(value = "usuarios", key = "#usuario.id")
    public Usuario save(Usuario usuario) {
        Usuario response = usuarioMapper.fromEntity(usuarioRepository.save(usuarioMapper.toEntity(usuario)));
        usuarioElasticRepository.save(usuarioElasticMapper.toElasitc(usuario));
        return response;
    }

    @Override
    public void reindex() {
        usuarioElasticRepository.deleteAll();
        usuarioElasticRepository.saveAll(usuarioRepository.findAll().stream().map(usuarioMapper::fromEntity).map(usuarioElasticMapper::toElasitc).toList());
    }

}
