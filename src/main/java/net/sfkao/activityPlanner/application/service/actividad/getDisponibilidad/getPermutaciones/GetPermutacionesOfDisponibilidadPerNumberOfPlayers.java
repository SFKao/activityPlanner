package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutaciones;

import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetPermutacionesOfDisponibilidadPerNumberOfPlayers {

    List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> getCombinacionesOfDisponibilidadesPerNumberOfPlayers(List<DisponibilidadesUsuario> disponibilidadesUsuarios, int minJugadores, int maxJugadores);

}
