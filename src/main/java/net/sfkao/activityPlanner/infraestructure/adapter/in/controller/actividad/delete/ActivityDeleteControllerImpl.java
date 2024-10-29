package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.delete;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.application.port.in.actividad.ActividadDeleteByIdPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class ActivityDeleteControllerImpl implements ActivityDeleteController, ActividadDeleteByIdPort {

    @Autowired
    @Qualifier("ActividadDeleteService")
    ActividadDeleteByIdPort
            actividadDeleteByIdPort;

    @Override
    public ResponseEntity<?> delete(String id) {
        deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public void deleteById(String id) {
        actividadDeleteByIdPort.deleteById(id);
    }
}
