package net.sfkao.activityPlanner.actividad.application.search;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.actividad.infraestructure.search.ActividadSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/activity")
public class ActivitySearchController {

    @Autowired
    ActividadSearchService actividadSearchService;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "search") String search) {
        return ResponseEntity.ok(actividadSearchService.search(search));
    }

}
