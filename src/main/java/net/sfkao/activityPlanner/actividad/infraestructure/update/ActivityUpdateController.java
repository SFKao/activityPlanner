package net.sfkao.activityPlanner.actividad.infraestructure.update;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.actividad.application.save.ActividadSaveService;
import net.sfkao.activityPlanner.actividad.domain.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/activity")
public class ActivityUpdateController {

    @Autowired
    ActividadSaveService actividadService;

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Actividad actividad) {
        actividad = actividadService.save(actividad);
        return ResponseEntity.ok(actividad);
    }

}
