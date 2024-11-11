package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.calcularHoras;

import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CalcularHorasParaLasPermutaciones {

    List<DisponibilidadesUsuario> calcularHorasParaLasIntersecciones(List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> permutacionesDeDisponibilidadesDeUsuarioDivididoEnNumeroDeJugadores);

}
