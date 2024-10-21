package net.sfkao.activityPlanner.actividad.domain.repository;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends MongoRepository<Actividad, String> {
}
