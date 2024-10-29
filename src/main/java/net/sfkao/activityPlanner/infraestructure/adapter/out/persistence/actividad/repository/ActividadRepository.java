package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.repository;

import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.entity.ActividadEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends MongoRepository<ActividadEntity, String> {
}
