package net.sfkao.activityPlanner.elastic.application;


import net.sfkao.activityPlanner.elastic.infraestructure.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/elastic")
public class ElasticController {

    @Autowired
    ElasticService elasticService;

    @PostMapping("/reindex")
    public ResponseEntity<?> reindex() {
        elasticService.reindex();
        return ResponseEntity.ok().build();
    }

}
