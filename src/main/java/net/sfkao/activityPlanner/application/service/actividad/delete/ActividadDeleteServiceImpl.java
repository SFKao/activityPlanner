package net.sfkao.activityPlanner.application.service.actividad.delete;

import net.sfkao.activityPlanner.application.port.in.actividad.ActividadDeleteByIdPort;
import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ActividadDeleteService")
public class ActividadDeleteServiceImpl implements ActividadDeleteService, ActividadDeleteByIdPort {

    @Autowired
    ActividadPersistencePort actividadPersistencePort;

    @Override
    public void deleteById(String id) {
        actividadPersistencePort.deleteById(id);
    }

}
