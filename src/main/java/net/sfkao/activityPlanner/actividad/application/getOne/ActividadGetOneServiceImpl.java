package net.sfkao.activityPlanner.actividad.application.getOne;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import net.sfkao.activityPlanner.actividad.domain.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActividadGetOneServiceImpl implements ActividadGetOneService {

    @Autowired
    ActividadRepository actividadRepository;

    @Override
    public Optional<Actividad> getById(String id) {
        return actividadRepository.findById(id);
    }

}
