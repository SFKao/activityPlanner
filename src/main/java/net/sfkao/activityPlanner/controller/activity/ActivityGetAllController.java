package net.sfkao.activityPlanner.controller.activity;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/activity")
public class ActivityGetAllController {

    @Autowired
    ActividadService actividadService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(actividadService.getAll());
        } catch (Exception e) {
            log.error("No se ha podido devolver todas las actividades", e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
