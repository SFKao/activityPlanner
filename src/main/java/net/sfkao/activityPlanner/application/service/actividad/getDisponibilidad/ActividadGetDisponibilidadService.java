package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad;

import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.exception.ActividadNotFoundException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@Service
public interface ActividadGetDisponibilidadService {

    Map<DayOfWeek, List<List<Disponibilidad>>> getDisponibilidadActividad(String idActividad) throws ActividadNotFoundException;

}
