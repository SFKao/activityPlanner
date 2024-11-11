package net.sfkao.activityPlanner.application.port.in.actividad;

import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.exception.ActividadNotFoundException;

import java.util.List;

public interface ActividadGetDisponibilidadOfActividadPort {

    List<DisponibilidadesUsuario> getDisponibilidadActividad(String idActividad) throws ActividadNotFoundException;

}
