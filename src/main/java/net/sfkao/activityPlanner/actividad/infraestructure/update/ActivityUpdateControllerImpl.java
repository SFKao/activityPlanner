package net.sfkao.activityPlanner.actividad.infraestructure.update;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.actividad.application.save.ActividadSaveService;
import net.sfkao.activityPlanner.actividad.domain.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class ActivityUpdateControllerImpl {

    @Autowired
    ActividadSaveService actividadService;


    public ResponseEntity<?> update(Actividad actividad) {
        actividad = actividadService.save(actividad);
        return ResponseEntity.ok(actividad);
    }

}
