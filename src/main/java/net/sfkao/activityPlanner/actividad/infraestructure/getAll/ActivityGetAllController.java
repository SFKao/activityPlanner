package net.sfkao.activityPlanner.actividad.infraestructure.getAll;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.actividad.application.getAll.ActividadGetAllService;
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
    ActividadGetAllService actividadGetAllService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(actividadGetAllService.getAll());
        } catch (Exception e) {
            log.error("No se ha podido devolver todas las actividades", e);
            return ResponseEntity.internalServerError().build();
        }
    }

}