package net.sfkao.activityPlanner.actividad.application.getAll;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import net.sfkao.activityPlanner.actividad.domain.repository.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadGetAllServiceImpl implements ActividadGetAllService {

    @Autowired
    ActividadRepository actividadRepository;

    @Override
    public List<Actividad> getAll() {
        return actividadRepository.findAll();
    }

}
