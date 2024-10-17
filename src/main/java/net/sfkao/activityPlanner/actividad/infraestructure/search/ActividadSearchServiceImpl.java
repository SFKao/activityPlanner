package net.sfkao.activityPlanner.actividad.infraestructure.search;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import net.sfkao.activityPlanner.actividad.domain.ActividadElastic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActividadSearchServiceImpl implements ActividadSearchService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

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
        return hits.get().map(hit -> modelMapper.map(hit.getContent(), Actividad.class)).collect(Collectors.toList());
    }


}
