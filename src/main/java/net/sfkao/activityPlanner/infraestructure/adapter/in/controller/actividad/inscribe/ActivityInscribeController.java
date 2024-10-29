package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.inscribe;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/activity/inscribe")
public interface ActivityInscribeController {

    @PostMapping
    ResponseEntity<?> inscribe(@RequestParam(name = "id") String activityId, Principal user);


}
