package net.sfkao.activityPlanner.application.port.in.actividad;

import net.sfkao.activityPlanner.domain.Actividad;

import java.util.List;

public interface ActividadSearchPort {

    List<Actividad> search(String search);

}
