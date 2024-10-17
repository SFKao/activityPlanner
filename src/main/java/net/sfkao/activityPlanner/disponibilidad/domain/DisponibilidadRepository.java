package net.sfkao.activityPlanner.disponibilidad.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilidadRepository extends MongoRepository<Disponibilidad, String> {
}
