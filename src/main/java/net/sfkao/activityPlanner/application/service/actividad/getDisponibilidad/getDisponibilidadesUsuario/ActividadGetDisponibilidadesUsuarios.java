package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getDisponibilidadesUsuario;

import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActividadGetDisponibilidadesUsuarios {

    List<DisponibilidadesUsuario> getDisponibilidadesUsuarios(Actividad actividad);

}
