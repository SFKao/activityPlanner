package net.sfkao.activityPlanner.application.port.in.actividad;

import net.sfkao.activityPlanner.domain.Actividad;

public interface ActividadSavePort {

    Actividad save(Actividad actividad);
    
}
