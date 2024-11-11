package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidadOfActividad;

import net.sfkao.activityPlanner.application.port.in.actividad.ActividadGetDisponibilidadOfActividadPort;
import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.calcularHoras.CalcularHorasParaLasPermutaciones;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutacionesUsuario.GetPermutacionesDisponibilidadUsuariosActividad;
import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores;
import net.sfkao.activityPlanner.domain.exception.ActividadNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("actividadGetDisponibilidadService")
public class ActividadGetDisponibilidadServiceImpl implements ActividadGetDisponibilidadService, ActividadGetDisponibilidadOfActividadPort {

    @Autowired
    ActividadPersistencePort actividadPersistencePort;

    @Autowired
    GetPermutacionesDisponibilidadUsuariosActividad getPermutacionesDisponibilidadUsuariosActividad;

    @Autowired
    CalcularHorasParaLasPermutaciones calcularHorasParaLasIntersecciones;

    @Override
    public List<DisponibilidadesUsuario> getDisponibilidadActividad(String idActividad) throws ActividadNotFoundException {
        Actividad actividad = actividadPersistencePort.findById(idActividad).orElseThrow(ActividadNotFoundException::new);
        //Obtengo las disponibilidades a calcular por el numero de jugadores
        List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> permutacionesDisponibiliades = getPermutacionesDisponibilidadUsuariosActividad.getPermutacionesDisponibiliades(actividad);
        //Calculo las horas posibles de cada combinacion
        return calcularHorasParaLasIntersecciones.calcularHorasParaLasIntersecciones(permutacionesDisponibiliades);
    }

}
