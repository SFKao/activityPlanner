package net.sfkao.activityPlanner.application.service.actividad.getOne;

import net.sfkao.activityPlanner.domain.Actividad;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ActividadGetByIdService {

    Optional<Actividad> getById(String id);

}
