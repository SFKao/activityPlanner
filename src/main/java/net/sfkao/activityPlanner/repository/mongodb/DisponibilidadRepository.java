package net.sfkao.activityPlanner.repository.mongodb;

import net.sfkao.activityPlanner.model.Disponibilidad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilidadRepository extends MongoRepository<Disponibilidad, String> {
}
