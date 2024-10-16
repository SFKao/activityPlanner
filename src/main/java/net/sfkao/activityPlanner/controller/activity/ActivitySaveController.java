package net.sfkao.activityPlanner.controller.activity;

import net.sfkao.activityPlanner.model.Actividad;
import net.sfkao.activityPlanner.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ActivitySaveController {

    @Autowired
    ActividadService actividadService;


    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Actividad actividad){
        actividad = actividadService.save(actividad);
        return ResponseEntity.ok(actividad);
    }

}
