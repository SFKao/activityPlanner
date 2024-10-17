package net.sfkao.activityPlanner.actividad.application.reindex;

import net.sfkao.activityPlanner.actividad.domain.ActividadElastic;
import net.sfkao.activityPlanner.actividad.domain.ActividadElasticRepository;
import net.sfkao.activityPlanner.actividad.domain.ActividadRepository;
import net.sfkao.activityPlanner.elastic.domain.ReindexableEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadReindexEntityServiceImpl implements ReindexableEntityService {

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    ActividadElasticRepository actividadElasticRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void reindex() {
        actividadElasticRepository.deleteAll();
        actividadRepository.findAll().forEach(actividad -> actividadElasticRepository.save(modelMapper.map(actividad, ActividadElastic.class)));
    }

}
