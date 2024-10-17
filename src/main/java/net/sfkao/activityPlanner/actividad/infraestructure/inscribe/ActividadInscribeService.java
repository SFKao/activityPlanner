package net.sfkao.activityPlanner.actividad.infraestructure.inscribe;

import net.sfkao.activityPlanner.exception.ActividadNotFoundException;
import net.sfkao.activityPlanner.exception.UsuarioNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ActividadInscribeService  {

    void inscribe(String username, String actividadId) throws ActividadNotFoundException, UsuarioNotFoundException;

}
