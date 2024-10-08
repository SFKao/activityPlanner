package net.sfkao.activityPlanner.repository.mongodb;

import net.sfkao.activityPlanner.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmail(@NonNull String email);
}
