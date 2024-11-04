package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.getOne;

import net.sfkao.activityPlanner.application.port.in.actividad.ActividadGetByIdPort;
import net.sfkao.activityPlanner.domain.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ActividadGetByIdControllerImpl implements ActividadGetByIdController, ActividadGetByIdPort {

    @Autowired
    @Qualifier("ActividadGetByIdService")
    private ActividadGetByIdPort actividadGetByIdPort;

    @Override
    public ResponseEntity<?> getByIdResponse(String activityId) {
        if (activityId == null || activityId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Actividad> byId = getById(activityId);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }

    @Override
    public Optional<Actividad> getById(String id) {
        return actividadGetByIdPort.getById(id);
    }
}
