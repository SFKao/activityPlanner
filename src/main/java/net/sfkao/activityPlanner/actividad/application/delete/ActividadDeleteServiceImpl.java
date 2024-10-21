package net.sfkao.activityPlanner.actividad.application.delete;

import net.sfkao.activityPlanner.actividad.domain.repository.ActividadElasticRepository;
import net.sfkao.activityPlanner.actividad.domain.repository.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadDeleteServiceImpl implements ActividadDeleteService {

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    ActividadElasticRepository actividadElasticRepository;

    @Override
    public void deleteById(String id) {
        actividadRepository.deleteById(id);
        actividadElasticRepository.deleteById(id);
    }

}
