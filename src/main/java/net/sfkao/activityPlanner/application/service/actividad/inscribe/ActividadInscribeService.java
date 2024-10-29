package net.sfkao.activityPlanner.application.service.actividad.inscribe;

import net.sfkao.activityPlanner.domain.exception.ActividadNotFoundException;
import net.sfkao.activityPlanner.domain.exception.UsuarioNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ActividadInscribeService {

    void inscribe(String username, String actividadId) throws ActividadNotFoundException, UsuarioNotFoundException;

}
