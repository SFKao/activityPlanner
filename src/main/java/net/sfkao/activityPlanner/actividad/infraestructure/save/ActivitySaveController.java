package net.sfkao.activityPlanner.actividad.infraestructure.save;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity")
public interface ActivitySaveController {

    @PostMapping()
    ResponseEntity<?> save(@RequestBody Actividad actividad);

}
