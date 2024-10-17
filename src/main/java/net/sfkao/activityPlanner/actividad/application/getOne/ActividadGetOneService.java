package net.sfkao.activityPlanner.actividad.application.getOne;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ActividadGetOneService {

    Optional<Actividad> getById(String id);

}
