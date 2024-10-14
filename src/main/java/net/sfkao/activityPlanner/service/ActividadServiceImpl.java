package net.sfkao.activityPlanner.service;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.exception.ActividadNotFoundException;
import net.sfkao.activityPlanner.exception.UsuarioNotFoundException;
import net.sfkao.activityPlanner.mapper.ActividadMapper;
import net.sfkao.activityPlanner.model.Actividad;
import net.sfkao.activityPlanner.model.Usuario;
import net.sfkao.activityPlanner.model.elastic.ActividadElastic;
import net.sfkao.activityPlanner.repository.elastic.ActividadElasticRepository;
import net.sfkao.activityPlanner.repository.mongodb.ActividadRepository;
import net.sfkao.activityPlanner.repository.mongodb.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
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

    @Autowired
    UsuarioRepository usuarioRepository;

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
    public void inscribe(String username, String actividadId) throws ActividadNotFoundException, UsuarioNotFoundException {
        Optional<Actividad> actividadOptional = actividadRepository.findById(actividadId);
        Actividad actividad = actividadOptional.orElseThrow(ActividadNotFoundException::new);
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
        Usuario usuario = usuarioOptional.orElseThrow(UsuarioNotFoundException::new);

        usuario.getActividadesInscritas().add(actividad);
        usuarioRepository.save(usuario);

//        actividad.getUsuariosInscritos().add(usuario);
//        actividadRepository.save(actividad);

    }

    @Override
    public void reindex() {
        actividadElasticRepository.deleteAll();
        actividadRepository.findAll().forEach(actividad -> actividadElasticRepository.save(actividadMapper.toElastic(actividad)));
    }
}
