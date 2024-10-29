package net.sfkao.activityPlanner.application.service.actividad.getAll;

import net.sfkao.activityPlanner.application.port.in.actividad.ActividadGetAllPort;
import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import net.sfkao.activityPlanner.domain.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("ActividadGetAllService")
public class ActividadGetAllServiceImpl implements ActividadGetAllService, ActividadGetAllPort {

    @Autowired
    ActividadPersistencePort actividadPersistencePort;

    @Override
    public List<Actividad> getAll() {
        return actividadPersistencePort.findAll();
    }

}
