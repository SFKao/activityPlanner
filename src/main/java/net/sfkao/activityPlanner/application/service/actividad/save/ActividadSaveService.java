package net.sfkao.activityPlanner.application.service.actividad.save;

import net.sfkao.activityPlanner.domain.Actividad;
import org.springframework.stereotype.Service;

@Service
public interface ActividadSaveService {

    Actividad save(Actividad actividad);

}
