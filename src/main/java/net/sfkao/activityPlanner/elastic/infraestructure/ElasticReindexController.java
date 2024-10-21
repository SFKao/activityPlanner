package net.sfkao.activityPlanner.elastic.infraestructure;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/elastic")
public interface ElasticReindexController {

    @PostMapping("/reindex")
    ResponseEntity<?> reindex();

}
