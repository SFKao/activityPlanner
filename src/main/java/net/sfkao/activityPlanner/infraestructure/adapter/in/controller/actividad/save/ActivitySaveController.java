package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.save;

import net.sfkao.activityPlanner.domain.Actividad;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity")
public interface ActivitySaveController {

    @PostMapping()
    ResponseEntity<?> saveResponse(@RequestBody Actividad actividad);

}
