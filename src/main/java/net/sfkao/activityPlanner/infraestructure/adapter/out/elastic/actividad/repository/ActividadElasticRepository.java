package net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.actividad.repository;

import net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.actividad.entity.ActividadElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadElasticRepository extends ElasticsearchRepository<ActividadElastic, String> {

}

