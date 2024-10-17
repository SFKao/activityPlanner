package net.sfkao.activityPlanner.actividad.infraestructure.delete;

import net.sfkao.activityPlanner.actividad.domain.ActividadElasticRepository;
import net.sfkao.activityPlanner.actividad.domain.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadDeleteServiceImpl implements ActividadDeleteService{

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
