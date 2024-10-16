package net.sfkao.activityPlanner.actividad.infraestructure.save;

import net.sfkao.activityPlanner.actividad.application.save.ActividadSaveService;
import net.sfkao.activityPlanner.actividad.domain.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ActivitySaveController {

    @Autowired
    ActividadSaveService actividadService;


    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Actividad actividad) {
        actividad = actividadService.save(actividad);
        return ResponseEntity.ok(actividad);
    }

}
