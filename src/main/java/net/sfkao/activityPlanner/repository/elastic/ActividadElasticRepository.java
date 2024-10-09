package net.sfkao.activityPlanner.repository.elastic;

import net.sfkao.activityPlanner.model.Actividad;
import net.sfkao.activityPlanner.model.elastic.ActividadElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadElasticRepository extends ElasticsearchRepository<ActividadElastic, String> {

}

