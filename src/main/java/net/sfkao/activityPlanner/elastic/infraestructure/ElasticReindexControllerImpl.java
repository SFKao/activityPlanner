package net.sfkao.activityPlanner.elastic.infraestructure;


import net.sfkao.activityPlanner.elastic.application.ElasticReindexService;
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
