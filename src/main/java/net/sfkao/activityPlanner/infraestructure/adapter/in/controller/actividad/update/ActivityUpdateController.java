package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.update;

import net.sfkao.activityPlanner.domain.Actividad;
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
