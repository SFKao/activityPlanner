package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getDisponibilidadesUsuario;

import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActividadGetDisponibilidadesUsuariosImpl implements ActividadGetDisponibilidadesUsuarios {


    @Override
    public List<DisponibilidadesUsuario> getDisponibilidadesUsuarios(Actividad actividad) {
        List<DisponibilidadesUsuario> disponibilidadesUsuarios = new ArrayList<>();
        actividad.getUsuariosInscritos().forEach(u -> {
            DisponibilidadesUsuario disponibilidadUsuarioPorDia = new DisponibilidadesUsuario(u.getHorasDisponibles(), List.of(u));
            disponibilidadesUsuarios.add(disponibilidadUsuarioPorDia);
        });
        return disponibilidadesUsuarios;
    }
}
