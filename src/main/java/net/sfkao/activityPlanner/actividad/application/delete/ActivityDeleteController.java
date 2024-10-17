package net.sfkao.activityPlanner.actividad.application.delete;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.actividad.infraestructure.delete.ActividadDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/activity")
public class ActivityDeleteController {

    @Autowired
    ActividadDeleteService actividadDeleteService;

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestParam(name = "id") String id) {
        actividadDeleteService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
