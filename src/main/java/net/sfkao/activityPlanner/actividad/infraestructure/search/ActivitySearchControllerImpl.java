package net.sfkao.activityPlanner.actividad.infraestructure.search;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.actividad.application.search.ActividadSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class ActivitySearchControllerImpl implements ActivitySearchController {

    @Autowired
    ActividadSearchService actividadSearchService;

    @Override
    public ResponseEntity<?> search(String search) {
        return ResponseEntity.ok(actividadSearchService.search(search));
    }

}
