package net.sfkao.activityPlanner.actividad.domain.repository;

import net.sfkao.activityPlanner.actividad.domain.ActividadElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadElasticRepository extends ElasticsearchRepository<ActividadElastic, String> {

}

