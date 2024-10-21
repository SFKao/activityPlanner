package net.sfkao.activityPlanner.actividad.infraestructure.search;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity/search")
public interface ActivitySearchController {

    @GetMapping()
    ResponseEntity<?> search(@RequestParam(name = "search") String search);

}
