package net.sfkao.activityPlanner.application.port.in.actividad;

import net.sfkao.activityPlanner.domain.exception.ActividadNotFoundException;
import net.sfkao.activityPlanner.domain.exception.UsuarioNotFoundException;

public interface ActividadInscribePort {

    void inscribe(String username, String actividadId) throws ActividadNotFoundException, UsuarioNotFoundException;

}
