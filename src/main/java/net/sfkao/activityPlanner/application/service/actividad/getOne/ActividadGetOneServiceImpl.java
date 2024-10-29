package net.sfkao.activityPlanner.application.service.actividad.getOne;

import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import net.sfkao.activityPlanner.domain.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActividadGetOneServiceImpl implements ActividadGetOneService {

    @Autowired
    ActividadPersistencePort actividadPersistencePort;

    @Override
    public Optional<Actividad> getById(String id) {
        return actividadPersistencePort.findById(id);
    }

}
