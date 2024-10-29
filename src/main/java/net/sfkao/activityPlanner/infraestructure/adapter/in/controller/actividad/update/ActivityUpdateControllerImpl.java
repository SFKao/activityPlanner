package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.update;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.application.port.in.actividad.ActividadSavePort;
import net.sfkao.activityPlanner.domain.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class ActivityUpdateControllerImpl implements ActivityUpdateController, ActividadSavePort {

    @Autowired
    @Qualifier("ActividadSaveService")
    ActividadSavePort actividadSavePort;


    @Override
    public ResponseEntity<?> update(Actividad actividad) {
        return ResponseEntity.ok(save(actividad));
    }

    @Override
    public Actividad save(Actividad actividad) {
        return actividadSavePort.save(actividad);
    }
}
