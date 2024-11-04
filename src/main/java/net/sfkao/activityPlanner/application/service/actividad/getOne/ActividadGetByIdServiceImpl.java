package net.sfkao.activityPlanner.application.service.actividad.getOne;

import net.sfkao.activityPlanner.application.port.in.actividad.ActividadGetByIdPort;
import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import net.sfkao.activityPlanner.domain.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("ActividadGetByIdService")
public class ActividadGetByIdServiceImpl implements ActividadGetByIdService, ActividadGetByIdPort {

    @Autowired
    ActividadPersistencePort actividadPersistencePort;

    @Override
    public Optional<Actividad> getById(String id) {
        return actividadPersistencePort.findById(id);
    }

}
