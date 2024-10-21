package net.sfkao.activityPlanner.actividad.infraestructure.update;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity")
public interface ActivityUpdateController {

    @PutMapping()
    ResponseEntity<?> update(@RequestBody Actividad actividad);

}
