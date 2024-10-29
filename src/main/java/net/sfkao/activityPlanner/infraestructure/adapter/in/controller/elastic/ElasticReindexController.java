package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.elastic;

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
