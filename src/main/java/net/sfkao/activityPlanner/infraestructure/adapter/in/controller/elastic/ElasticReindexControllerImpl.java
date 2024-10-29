package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.elastic;


import net.sfkao.activityPlanner.application.service.elastic.ElasticReindexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElasticReindexControllerImpl {

    @Autowired
    ElasticReindexService elasticService;

    public ResponseEntity<?> reindex() {
        elasticService.reindex();
        return ResponseEntity.ok().build();
    }

}
