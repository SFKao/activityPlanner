package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.getDisponibilidadOfActividad;

import net.sfkao.activityPlanner.application.port.in.actividad.ActividadGetDisponibilidadOfActividadPort;
import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.dto.DisponibilidadesUsuarioDTO;
import net.sfkao.activityPlanner.domain.exception.ActividadNotFoundException;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.mapper.DisponibilidadesUsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetDisponibilidadOfActividadControllerImpl implements GetDisponibilidadOfActividadController, ActividadGetDisponibilidadOfActividadPort {

    @Autowired
    @Qualifier("actividadGetDisponibilidadService")
    ActividadGetDisponibilidadOfActividadPort actividadGetDisponibilidadOfActividadPort;

    DisponibilidadesUsuarioMapper disponibilidadesUsuarioMapper = DisponibilidadesUsuarioMapper.INSTANCE;

    @Override
    public ResponseEntity<?> getDisponibilidadOfActividadResponse(String activityId) {
        try {
            List<DisponibilidadesUsuario> disponibilidadActividad = getDisponibilidadActividad(activityId);
            List<DisponibilidadesUsuarioDTO> list = disponibilidadActividad.stream().map(disponibilidadesUsuarioMapper::toDTO).toList();
            return ResponseEntity.ok(list);
        } catch (ActividadNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public List<DisponibilidadesUsuario> getDisponibilidadActividad(String idActividad) throws ActividadNotFoundException {
        return actividadGetDisponibilidadOfActividadPort.getDisponibilidadActividad(idActividad);
    }
}
