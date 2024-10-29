package net.sfkao.activityPlanner.application.port.out.usuario;

import net.sfkao.activityPlanner.domain.Usuario;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UsuarioPersistencePort {

    Optional<Usuario> findByEmail(@NonNull String email);

    Optional<Usuario> findByUsername(@NonNull String username);

    Optional<Usuario> findByUsernameOrEmail(String email, String username);

    Optional<Usuario> findByRefreshToken(@NonNull String refreshToken);

    Usuario save(Usuario usuario);

    void reindex();

}
