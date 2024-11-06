package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad;

import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.exception.ActividadNotFoundException;
import org.paukov.combinatorics3.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActividadGetDisponibilidadServiceImpl implements ActividadGetDisponibilidadService {


    @Autowired
    ActividadPersistencePort actividadPersistencePort;

    @Override
    public Map<DayOfWeek, List<List<Disponibilidad>>> getDisponibilidadActividad(String idActividad) throws ActividadNotFoundException {
        Map<DayOfWeek, List<List<Disponibilidad>>> disponibilidadesDeUsuarioPorDia = new HashMap<>();

        //Obtengo listas de las distintas disponibilidades de los usuarios por dia, cada usuario tiene una lista de sus disponibilidades ese dia
        Actividad actividad = actividadPersistencePort.findById(idActividad).orElseThrow(ActividadNotFoundException::new);
        actividad.getUsuariosInscritos().forEach(u -> {
            Map<DayOfWeek, List<Disponibilidad>> disponibilidadUsuarioPorDia = getDisponibilidadByDayOfWeek(u.getHorasDisponibles());
            disponibilidadUsuarioPorDia.forEach((dia, lista) -> disponibilidadesDeUsuarioPorDia.putIfAbsent(dia, new ArrayList<>()).add(lista));
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

        Map<DayOfWeek, List<List<List<Disponibilidad>>>> combinacionesOfDisponibilidadesPerNumberOfPlayers = getCombinacionesOfDisponibilidadesPerNumberOfPlayers(disponibilidadesDeUsuarioPorDia, minJugadores, maxJugadores);

        Map<DayOfWeek, List<List<Disponibilidad>>> disponibilidadesPorGrupoDeUsuariosPorDia = new HashMap<>();

        combinacionesOfDisponibilidadesPerNumberOfPlayers.forEach((dia, combinacion) -> {
            for (List<List<Disponibilidad>> disponibilidadesDeEsosUsuarioEseDia : combinacion) {
                List<Disponibilidad> disponibilidadesDeEseDiaDeEsosUsuarios = null;
                for (List<Disponibilidad> disponibilidadUsuario : disponibilidadesDeEsosUsuarioEseDia) {
                    if (disponibilidadesDeEseDiaDeEsosUsuarios == null) {
                        disponibilidadesDeEseDiaDeEsosUsuarios = disponibilidadUsuario;
                    } else {
                        disponibilidadesDeEseDiaDeEsosUsuarios = getInterseccionDisponibilidades(disponibilidadesDeEseDiaDeEsosUsuarios, disponibilidadUsuario);
                    }
                }
                disponibilidadesPorGrupoDeUsuariosPorDia.putIfAbsent(dia, new ArrayList<>()).add(disponibilidadesDeEseDiaDeEsosUsuarios);
            }
        });

        return disponibilidadesPorGrupoDeUsuariosPorDia;
    }


    private Map<DayOfWeek, List<List<List<Disponibilidad>>>> getCombinacionesOfDisponibilidadesPerNumberOfPlayers(Map<DayOfWeek, List<List<Disponibilidad>>> dias, int minJugadores, int maxJugadores) {

        //Lista -> disponibilidades de una persona
        //Lista<Lista> disponibilidades de todas las personas en un dia
        //Lista<Lista<Lista>> lista de todas las posibles disponibilidades de todas las personas en un dia dependiendo el numero de jugadores
        /*
        Imagina son 4 jugadores y hay 5 usuarios inscritos
        Lista<Lista<Lista>> serian todas las combinaciones de 4 jugadores de todas las disponibilidades de ese dia.
         */

        Map<DayOfWeek, List<List<List<Disponibilidad>>>> combinacionesOfDisponibilidadesPerNumberOfPlayers = new HashMap<>();

        dias.forEach((dia, disponibilidadesDia) -> {
            for (int numJugadores = minJugadores; numJugadores <= maxJugadores; numJugadores++) {
                List<List<List<Disponibilidad>>> permutacionesDeDisponibilidadesDeLosJugadoresEnUnDiaEspecifico = Generator.combination(disponibilidadesDia)
                        .simple(numJugadores)
                        .stream().toList();
                combinacionesOfDisponibilidadesPerNumberOfPlayers.putIfAbsent(dia, new ArrayList<>()).addAll(permutacionesDeDisponibilidadesDeLosJugadoresEnUnDiaEspecifico);

            }
        });

        return combinacionesOfDisponibilidadesPerNumberOfPlayers;

    }

    @Cacheable("interseccionDisponibilidades")
    private List<Disponibilidad> getInterseccionDisponibilidades(List<Disponibilidad> disponibilidads1, List<Disponibilidad> disponibilidads2) {

        List<Disponibilidad> result = new ArrayList<>();

        for (Disponibilidad d1 : disponibilidads1) {
            for (Disponibilidad d2 : disponibilidads2) {
                //Si hay una interseccion
                OffsetTime d1Inicio = d1.getHoraInicioAsTime();
                OffsetTime d1Final = d1.getHoraFinalAsTime();
                OffsetTime d2Inicio = d2.getHoraInicioAsTime();
                OffsetTime d2Final = d2.getHoraFinalAsTime();
                if (!(d1Final.isBefore(d2Inicio) || d1Inicio.isAfter(d2Final))) {
                    Disponibilidad d = new Disponibilidad();
                    d.setDia(d1.getDia());
                    //Si hay una interseccion me quedo con la hora de inicio mas tardia y la de final mas temprana
                    d.setHoraInicioAsTime(d1Inicio.isAfter(d2Inicio) ? d1Inicio : d2Inicio);
                    d.setHoraFinalAsTime(d1Final.isBefore(d2Final) ? d1Final : d2Final);
                    result.add(d);
                }
            }
        }

        return result;
    }


    private Map<DayOfWeek, List<Disponibilidad>> getDisponibilidadByDayOfWeek(List<Disponibilidad> disponibilidads) {

        Map<DayOfWeek, List<Disponibilidad>> week = new HashMap<>();
        disponibilidads.forEach(d -> {
            week.putIfAbsent(d.getDia(), new ArrayList<>()).add(d);
        });
        return week;
    }

}
