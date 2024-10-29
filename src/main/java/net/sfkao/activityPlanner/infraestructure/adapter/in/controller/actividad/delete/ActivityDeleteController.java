package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.delete;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity")
public interface ActivityDeleteController {

    @DeleteMapping()
    ResponseEntity<?> delete(@RequestParam(name = "id") String id);

}
