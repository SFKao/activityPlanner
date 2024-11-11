package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.filtrarActividadesDeUsuario;

import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores;
import net.sfkao.activityPlanner.domain.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FiltrarActividadesQueNoContenganElUsuarioImpl implements FiltrarActividadesQueNoContenganElUsuario {
    @Override
    public List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> filtarPermutacionesQueNoContenganElUsuario(List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores, Usuario usuario) {
        return permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores.stream().filter(p -> {
            for (DisponibilidadesUsuario disponibilidadesUsuario : p.getDisponibilidadesUsuarios()) {
                if (disponibilidadesUsuario.getUsuarios().contains(usuario)) {
                    return true;
                }
            }
            return false;
        }).toList();
    }
}
