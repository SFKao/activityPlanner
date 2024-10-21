package net.sfkao.activityPlanner.usuario.domain.repository;

import net.sfkao.activityPlanner.usuario.domain.model.UsuarioElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioElasticRepository extends ElasticsearchRepository<UsuarioElastic, String> {
}
