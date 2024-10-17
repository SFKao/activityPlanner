package net.sfkao.activityPlanner.actividad.infraestructure.getAll;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActividadGetAllService {

    List<Actividad> getAll();

}
