package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.getAll;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity")
public interface ActivityGetAllController {

    @GetMapping()
    ResponseEntity<?> getAllResponse();

}
