package net.sfkao.activityPlanner.actividad.application.getDisponibilidad;

import net.sfkao.activityPlanner.exception.ActividadNotFoundException;
import net.sfkao.activityPlanner.usuario.domain.model.Disponibilidad;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@Service
public interface ActividadGetDisponibilidadService {

    Map<DayOfWeek, List<List<Disponibilidad>>> getDisponibilidadActividad(String idActividad) throws ActividadNotFoundException;

}
