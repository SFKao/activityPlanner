package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutacionesUsuario;

import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetPermutacionesDisponibilidadUsuariosActividad {

    List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> getPermutacionesDisponibiliades(Actividad actividad);

}
