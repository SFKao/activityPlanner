package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad;

import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.exception.ActividadNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActividadGetDisponibilidadService {

    List<DisponibilidadesUsuario> getDisponibilidadActividad(String idActividad) throws ActividadNotFoundException;

}
