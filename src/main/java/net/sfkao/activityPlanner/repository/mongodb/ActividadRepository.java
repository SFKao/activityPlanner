package net.sfkao.activityPlanner.repository.mongodb;

import net.sfkao.activityPlanner.model.Actividad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends MongoRepository<Actividad, String> {
}
