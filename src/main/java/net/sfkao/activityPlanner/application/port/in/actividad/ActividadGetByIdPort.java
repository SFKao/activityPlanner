package net.sfkao.activityPlanner.application.port.in.actividad;

import net.sfkao.activityPlanner.domain.Actividad;

import java.util.Optional;

public interface ActividadGetByIdPort {

    Optional<Actividad> getById(String id);

}
