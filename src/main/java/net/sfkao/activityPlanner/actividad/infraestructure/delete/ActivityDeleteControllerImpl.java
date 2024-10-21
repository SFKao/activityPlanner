package net.sfkao.activityPlanner.actividad.infraestructure.delete;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.actividad.application.delete.ActividadDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class ActivityDeleteControllerImpl implements ActivityDeleteController {

    @Autowired
    ActividadDeleteService actividadDeleteService;

    @Override
    public ResponseEntity<?> delete(String id) {
        actividadDeleteService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
