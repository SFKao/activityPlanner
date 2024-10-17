package net.sfkao.activityPlanner.usuario.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmail(@NonNull String email);

    Optional<Usuario> findByUsername(@NonNull String username);

    @Query("{'$or':[ {'email':?0}, {'username':?1} ] }")
    Optional<Usuario> findByUsernameOrEmail(String email, String username);

    Optional<Usuario> findByRefreshToken(@NonNull String refreshToken);


}
