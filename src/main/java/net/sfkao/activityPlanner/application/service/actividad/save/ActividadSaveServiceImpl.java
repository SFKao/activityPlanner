package net.sfkao.activityPlanner.application.service.actividad.save;

import net.sfkao.activityPlanner.application.port.in.actividad.ActividadSavePort;
import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import net.sfkao.activityPlanner.domain.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ActividadSaveService")
public class ActividadSaveServiceImpl implements ActividadSaveService, ActividadSavePort {

    @Autowired
    ActividadPersistencePort actividadPersistencePort;


    @Override
    public Actividad save(Actividad actividad) {
        return actividadPersistencePort.save(actividad);
    }

}
