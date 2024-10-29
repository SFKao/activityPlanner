package net.sfkao.activityPlanner.application.service.actividad.getAll;

import net.sfkao.activityPlanner.domain.Actividad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActividadGetAllService {

    List<Actividad> getAll();

}
