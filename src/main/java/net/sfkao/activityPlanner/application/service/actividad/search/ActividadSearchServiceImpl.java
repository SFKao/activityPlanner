package net.sfkao.activityPlanner.application.service.actividad.search;

import net.sfkao.activityPlanner.application.port.in.actividad.ActividadSearchPort;
import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import net.sfkao.activityPlanner.domain.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("ActividadSearchService")
public class ActividadSearchServiceImpl implements ActividadSearchService, ActividadSearchPort {

    @Autowired
    ActividadPersistencePort actividadPersistencePort;


    @Override
    public List<Actividad> search(String search) {
        return actividadPersistencePort.search(search);
    }


}
