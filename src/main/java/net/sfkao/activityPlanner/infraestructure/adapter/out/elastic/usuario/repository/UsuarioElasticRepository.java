package net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.usuario.repository;

import net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.usuario.entity.UsuarioElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioElasticRepository extends ElasticsearchRepository<UsuarioElastic, String> {
}
