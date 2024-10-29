package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.repository;

import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.entity.UsuarioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioEntity, String> {
    Optional<UsuarioEntity> findByEmail(@NonNull String email);

    Optional<UsuarioEntity> findByUsername(@NonNull String username);

    @Query("{'$or':[ {'email':?0}, {'username':?1} ] }")
    Optional<UsuarioEntity> findByEmailOrUsername(String email, String username);

    Optional<UsuarioEntity> findByRefreshToken(@NonNull String refreshToken);


}
