package net.sfkao.activityPlanner.actividad.infraestructure.save;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import org.springframework.stereotype.Service;

@Service
public interface ActividadSaveService {

    Actividad save(Actividad actividad);

}
