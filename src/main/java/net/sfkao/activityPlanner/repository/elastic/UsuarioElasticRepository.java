package net.sfkao.activityPlanner.repository.elastic;

import net.sfkao.activityPlanner.model.Usuario;
import net.sfkao.activityPlanner.model.elastic.UsuarioElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioElasticRepository extends ElasticsearchRepository<UsuarioElastic, String> {
}
