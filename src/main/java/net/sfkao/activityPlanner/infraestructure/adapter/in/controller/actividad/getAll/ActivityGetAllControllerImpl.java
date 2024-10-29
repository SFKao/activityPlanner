package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.getAll;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.application.port.in.actividad.ActividadGetAllPort;
import net.sfkao.activityPlanner.domain.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class ActivityGetAllControllerImpl implements ActivityGetAllController, ActividadGetAllPort {

    @Autowired
    @Qualifier("ActividadGetAllService")
    ActividadGetAllPort actividadGetAllPort;

    @Override
    public ResponseEntity<?> getAllResponse() {
        try {
            return ResponseEntity.ok(getAll());
        } catch (Exception e) {
            log.error("No se ha podido devolver todas las actividades", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public List<Actividad> getAll() {
        return actividadGetAllPort.getAll();
    }
}
