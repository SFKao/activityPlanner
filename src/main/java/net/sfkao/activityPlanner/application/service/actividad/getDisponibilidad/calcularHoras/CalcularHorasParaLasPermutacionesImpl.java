package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.calcularHoras;

import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalcularHorasParaLasPermutacionesImpl implements CalcularHorasParaLasPermutaciones {

    @Override
    public List<DisponibilidadesUsuario> calcularHorasParaLasIntersecciones(List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> permutacionesDeDisponibilidadesDeUsuarioDivididoEnNumeroDeJugadores) {
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
            if (!disponibilidadesDeEseDiaDeEsosUsuarios.getHoras().isEmpty()) {
                opcionesParaRealizarActividad.add(disponibilidadesDeEseDiaDeEsosUsuarios);
            }
        });
        return opcionesParaRealizarActividad;
    }

    @Cacheable("interseccionDisponibilidades")
    private DisponibilidadesUsuario getInterseccionDisponibilidades(DisponibilidadesUsuario disponibilidads1, DisponibilidadesUsuario disponibilidads2) {

        DisponibilidadesUsuario result = new DisponibilidadesUsuario();

        result.getUsuarios().addAll(disponibilidads1.getUsuarios());
        result.getUsuarios().addAll(disponibilidads2.getUsuarios());

        for (Disponibilidad d1 : disponibilidads1.getHoras()) {
            for (Disponibilidad d2 : disponibilidads2.getHoras()) {
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
                        result.getHoras().add(d);
                    }
                }
            }
        }

        return result;
    }
}
