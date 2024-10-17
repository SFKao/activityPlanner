package net.sfkao.activityPlanner.actividad.application.save;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import net.sfkao.activityPlanner.actividad.domain.ActividadElastic;
import net.sfkao.activityPlanner.actividad.domain.ActividadElasticRepository;
import net.sfkao.activityPlanner.actividad.domain.ActividadRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadSaveServiceImpl implements ActividadSaveService {

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    ActividadElasticRepository actividadElasticRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Actividad save(Actividad actividad) {
        Actividad save = actividadRepository.save(actividad);
        actividadElasticRepository.save(modelMapper.map(actividad, ActividadElastic.class));
        return save;
    }

}
