package net.sfkao.activityPlanner.service;

import net.sfkao.activityPlanner.mapper.ActividadMapper;
import net.sfkao.activityPlanner.model.Actividad;
import net.sfkao.activityPlanner.model.elastic.ActividadElastic;
import net.sfkao.activityPlanner.repository.elastic.ActividadElasticRepository;
import net.sfkao.activityPlanner.repository.mongodb.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActividadServiceImpl implements ActividadService {

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    ActividadElasticRepository actividadElasticRepository;
    
    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @Autowired
    ActividadMapper actividadMapper;

    @Override
    public List<Actividad> getAll() {
        return actividadRepository.findAll();
    }

    @Override
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
        return hits.get().map(hit -> actividadMapper.fromElastic(hit.getContent())).collect(Collectors.toList());
    }

    @Override
    public Optional<Actividad> getById(String id) {
        return actividadRepository.findById(id);
    }

    @Override
    public Actividad save(Actividad actividad) {
        Actividad save = actividadRepository.save(actividad);
        actividadElasticRepository.save(actividadMapper.toElastic(actividad));
        return save;
    }

    @Override
    public void deleteById(String id) {
        actividadRepository.deleteById(id);
        actividadElasticRepository.deleteById(id);
    }

    @Override
    public void reindex() {
        actividadElasticRepository.deleteAll();
        actividadRepository.findAll().forEach(actividad -> actividadElasticRepository.save(actividadMapper.toElastic(actividad)));
    }
}
