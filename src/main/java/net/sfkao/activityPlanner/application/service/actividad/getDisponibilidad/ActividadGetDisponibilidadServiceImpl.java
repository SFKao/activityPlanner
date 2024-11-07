package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad;

import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores;
import net.sfkao.activityPlanner.domain.exception.ActividadNotFoundException;
import org.paukov.combinatorics3.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActividadGetDisponibilidadServiceImpl implements ActividadGetDisponibilidadService {


    @Autowired
    ActividadPersistencePort actividadPersistencePort;

    @Override
    public List<DisponibilidadesUsuario> getDisponibilidadActividad(String idActividad) throws ActividadNotFoundException {
        List<DisponibilidadesUsuario> disponibilidadesUsuarios = new ArrayList<>();

        //Obtengo listas de las distintas disponibilidades de los usuarios por dia, cada usuario tiene una lista de sus disponibilidades ese dia
        Actividad actividad = actividadPersistencePort.findById(idActividad).orElseThrow(ActividadNotFoundException::new);
        actividad.getUsuariosInscritos().forEach(u -> {
            DisponibilidadesUsuario disponibilidadUsuarioPorDia = new DisponibilidadesUsuario(u.getHorasDisponibles(), List.of(u));
            disponibilidadesUsuarios.add(disponibilidadUsuarioPorDia);
        });

        //Obtengo las disponibilidades a calcular por el numero de jugadores

        int minJugadores;
        int maxJugadores;
        //Si se requieren a todos los participantes
        if (actividad.getRequierenTodos()) {
            minJugadores = actividad.getUsuariosInscritos().size();
            maxJugadores = actividad.getUsuariosInscritos().size();
        } else {
            minJugadores = actividad.getMinJugadores();
            //Si no tiene limite de jugadores, el maximo es el numero de inscritos
            maxJugadores = actividad.getMaxJugadores() != -1 ? actividad.getMaxJugadores() : actividad.getUsuariosInscritos().size();
        }

        List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> permutacionesDeDisponibilidadesDeUsuarioDivididoEnNumeroDeJugadores
                = getCombinacionesOfDisponibilidadesPerNumberOfPlayers(disponibilidadesUsuarios, minJugadores, maxJugadores);

        List<DisponibilidadesUsuario> opcionesParaRealizarActividad = new ArrayList<>();
        permutacionesDeDisponibilidadesDeUsuarioDivididoEnNumeroDeJugadores.forEach((combinacion) -> {

            DisponibilidadesUsuario disponibilidadesDeEseDiaDeEsosUsuarios = null;
            for (DisponibilidadesUsuario disponibilidadUsuario : combinacion.getDisponibilidadesUsuarios()) {
                if (disponibilidadesDeEseDiaDeEsosUsuarios == null) {
                    disponibilidadesDeEseDiaDeEsosUsuarios = disponibilidadUsuario;
                } else {
                    disponibilidadesDeEseDiaDeEsosUsuarios = getInterseccionDisponibilidades(disponibilidadesDeEseDiaDeEsosUsuarios, disponibilidadUsuario);
                }
            }
            //Si no hay disponibilidades entre varios usuarios que no las guarde
            if (!disponibilidadesDeEseDiaDeEsosUsuarios.getDisponibilidadesUsuario().isEmpty()) {
                opcionesParaRealizarActividad.add(disponibilidadesDeEseDiaDeEsosUsuarios);
            }
        });

        return opcionesParaRealizarActividad;
    }


    private List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> getCombinacionesOfDisponibilidadesPerNumberOfPlayers(List<DisponibilidadesUsuario> disponibilidadesUsuarios, int minJugadores, int maxJugadores) {
        if (disponibilidadesUsuarios.size() < minJugadores) {
            return List.of();
        }

        List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> combinacionesOfDisponibilidadesPerNumberOfPlayers = new ArrayList<>();

        for (int numJugadores = minJugadores; numJugadores <= maxJugadores; numJugadores++) {
            List<List<DisponibilidadesUsuario>> permutacionesPorElNumeroDeUsuarios = Generator.combination(disponibilidadesUsuarios)
                    .simple(numJugadores)
                    .stream().toList();
            permutacionesPorElNumeroDeUsuarios.forEach(p -> {
                PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores
                        permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores
                        = new PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores(p);
                combinacionesOfDisponibilidadesPerNumberOfPlayers.add(permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores);
            });
        }


        return combinacionesOfDisponibilidadesPerNumberOfPlayers;

    }

    @Cacheable("interseccionDisponibilidades")
    private DisponibilidadesUsuario getInterseccionDisponibilidades(DisponibilidadesUsuario disponibilidads1, DisponibilidadesUsuario disponibilidads2) {

        DisponibilidadesUsuario result = new DisponibilidadesUsuario();

        result.getUsuarios().addAll(disponibilidads1.getUsuarios());
        result.getUsuarios().addAll(disponibilidads2.getUsuarios());

        for (Disponibilidad d1 : disponibilidads1.getDisponibilidadesUsuario()) {
            for (Disponibilidad d2 : disponibilidads2.getDisponibilidadesUsuario()) {
                if (d1.getDia().getValue() == d2.getDia().getValue()) {
                    OffsetTime d1Inicio = d1.getHoraInicioAsTime();
                    OffsetTime d1Final = d1.getHoraFinalAsTime();
                    OffsetTime d2Inicio = d2.getHoraInicioAsTime();
                    OffsetTime d2Final = d2.getHoraFinalAsTime();
                    //Si hay una interseccion
                    if (!(d1Final.isBefore(d2Inicio) || d1Inicio.isAfter(d2Final))) {
                        Disponibilidad d = new Disponibilidad();
                        d.setDia(d1.getDia());
                        //Si hay una interseccion me quedo con la hora de inicio mas tardia y la de final mas temprana
                        d.setHoraInicio(d1Inicio.isAfter(d2Inicio) ? d1.getHoraInicio() : d2.getHoraInicio());
                        d.setHoraFinal(d1Final.isBefore(d2Final) ? d1.getHoraFinal() : d2.getHoraFinal());
                        //El dia ha de ser igual, se chekea antes
                        d.setDia(d1.getDia());
                        result.getDisponibilidadesUsuario().add(d);
                    }
                }
            }
        }

        return result;
    }

}
