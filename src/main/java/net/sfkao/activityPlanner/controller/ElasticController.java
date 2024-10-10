package net.sfkao.activityPlanner.controller;


import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.service.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/elastic")
public class ElasticController {

    @Autowired
    ElasticService elasticService;

    @PostMapping("/reindex")
    public ResponseEntity<?> reindex(){
        log.info("Starting reindex");
        elasticService.reindex();
        log.info("Finished reindex");
        return ResponseEntity.ok().build();
    }

}
