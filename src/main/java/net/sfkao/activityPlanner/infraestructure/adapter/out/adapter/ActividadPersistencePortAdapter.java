package net.sfkao.activityPlanner.infraestructure.adapter.out.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.actividad.entity.ActividadElastic;
import net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.actividad.mapper.ActividadElasticMapper;
import net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.actividad.repository.ActividadElasticRepository;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.entity.ActividadEntity;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.mapper.ActividadMapper;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.repository.ActividadRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActividadPersistencePortAdapter implements ActividadPersistencePort {

    private final ActividadRepository actividadRepository;
    private final ActividadElasticRepository actividadElasticRepository;
    private final RedisTemplate<String, Object> actividadRedisTemplate;

    private final ElasticsearchOperations elasticsearchOperations;

    private final ActividadMapper actividadMapper = ActividadMapper.INSTANCE;
    private final ActividadElasticMapper actividadElasticMapper = ActividadElasticMapper.INSTANCE;

    @Override
    @CacheEvict(value = "actividades", key = "#id")
    public void deleteById(String id) {
        actividadRepository.deleteById(id);
        actividadElasticRepository.deleteById(id);
        actividadRedisTemplate.delete(id);
    }

    @Override
    @Cacheable("actividades")
    public List<Actividad> findAll() {
        return actividadRepository.findAll().stream().map(actividadMapper::fromEntity).toList();
    }

    @Override
    @Cacheable("actividades")
    public Optional<Actividad> findById(String idActividad) {

        Actividad actividadRedis = (Actividad) actividadRedisTemplate.opsForValue().get(idActividad);

        if (actividadRedis != null) {
            log.info("Encontrado cache en redis para actividadRedis {}", idActividad);
            return Optional.of(actividadRedis);
        }
        Optional<Actividad> actividadOptional = actividadRepository.findById(idActividad).map(actividadMapper::fromEntity);
        actividadOptional.ifPresent(actividad -> actividadRedisTemplate.opsForValue().set(idActividad, actividad));
        return actividadOptional;
    }

    @Override
    public void reindex() {
        actividadElasticRepository.deleteAll();
        actividadElasticRepository.saveAll(actividadRepository.findAll().stream().map(actividadMapper::fromEntity).map(actividadElasticMapper::toElastic).toList());
    }

    @Override
    @CachePut(value = "actividades", key = "#actividad.id")
    public Actividad save(Actividad actividad) {
        ActividadEntity save = actividadRepository.save(actividadMapper.toEntity(actividad));
        actividadElasticRepository.save(actividadElasticMapper.toElastic(actividad));
        Actividad actividad1 = actividadMapper.fromEntity(save);
        actividadRedisTemplate.opsForValue().set(actividad1.getId(), actividad1);
        return actividad1;
    }

    @Override
    @Cacheable("actividades")
    public List<Actividad> search(String search) {
        String queryString = """
                {
                        "query_string": {
                            "lenient": true,
                            "fields": [
                                "nombre.keyword^12",
                                "descripcion^3",
                                "jugadores^4"
                            ],
                            "query": "%s",
                            "type":"phrase_prefix"
                        }
                    }
                
                """.formatted(search);
        Query query = new StringQuery(queryString);
        SearchHits<ActividadElastic> hits = elasticsearchOperations.search(query, ActividadElastic.class);

        return hits.get().map(hit -> actividadElasticMapper.fromElastic(hit.getContent())).toList();
    }
}
