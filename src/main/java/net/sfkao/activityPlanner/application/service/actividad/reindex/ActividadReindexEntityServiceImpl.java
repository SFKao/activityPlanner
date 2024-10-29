package net.sfkao.activityPlanner.application.service.actividad.reindex;

import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.SearcheableEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadReindexEntityServiceImpl implements SearcheableEntityService {

    @Autowired
    ActividadPersistencePort actividadPersistencePort;


    @Override
    public void reindex() {
        actividadPersistencePort.reindex();
    }

}
