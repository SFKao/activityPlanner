package net.sfkao.activityPlanner.actividad.infraestructure.save;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.actividad.application.save.ActividadSaveService;
import net.sfkao.activityPlanner.actividad.domain.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class ActivitySaveControllerImpl implements ActivitySaveController {

    @Autowired
    ActividadSaveService actividadService;


    @Override
    public ResponseEntity<?> save(Actividad actividad) {
        actividad = actividadService.save(actividad);
        return ResponseEntity.ok(actividad);
    }

}
