package net.sfkao.activityPlanner.usuario.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioElasticRepository extends ElasticsearchRepository<UsuarioElastic, String> {
}
