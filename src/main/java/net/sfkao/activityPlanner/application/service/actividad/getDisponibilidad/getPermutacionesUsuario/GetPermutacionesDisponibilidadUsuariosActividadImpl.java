package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutacionesUsuario;

import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getDisponibilidadesUsuario.ActividadGetDisponibilidadesUsuarios;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutaciones.GetPermutacionesOfDisponibilidadPerNumberOfPlayers;
import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPermutacionesDisponibilidadUsuariosActividadImpl implements GetPermutacionesDisponibilidadUsuariosActividad {


    @Autowired
    ActividadGetDisponibilidadesUsuarios actividadGetDisponibilidadesUsuarios;

    @Autowired
    GetPermutacionesOfDisponibilidadPerNumberOfPlayers getCombinacionesOfDisponibilidadesPerNumberOfPlayers;

    public GetPermutacionesDisponibilidadUsuariosActividadImpl() {
    }

    public GetPermutacionesDisponibilidadUsuariosActividadImpl(ActividadGetDisponibilidadesUsuarios actividadGetDisponibilidadesUsuarios, GetPermutacionesOfDisponibilidadPerNumberOfPlayers getCombinacionesOfDisponibilidadesPerNumberOfPlayers) {
        this.actividadGetDisponibilidadesUsuarios = actividadGetDisponibilidadesUsuarios;
        this.getCombinacionesOfDisponibilidadesPerNumberOfPlayers = getCombinacionesOfDisponibilidadesPerNumberOfPlayers;
    }

    @Override
    public List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> getPermutacionesDisponibiliades(Actividad actividad) {

        List<DisponibilidadesUsuario> disponibilidadesUsuarios = actividadGetDisponibilidadesUsuarios.getDisponibilidadesUsuarios(actividad);

        if (actividad.getRequierenTodos()) {
            PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores p = new PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores(disponibilidadesUsuarios);
            return List.of(p);
        }

        //Si no tiene limite de jugadores, el maximo es el numero de inscritos
        int minJugadores = actividad.getMinJugadores();
        int maxJugadores = actividad.getMaxJugadores() != -1 ? actividad.getMaxJugadores() : actividad.getUsuariosInscritos().size();

        //Obtengo las combinaciones de los usuarios, depende del numero de jugadores
        return getCombinacionesOfDisponibilidadesPerNumberOfPlayers.getCombinacionesOfDisponibilidadesPerNumberOfPlayers(disponibilidadesUsuarios, minJugadores, maxJugadores);
    }
}
