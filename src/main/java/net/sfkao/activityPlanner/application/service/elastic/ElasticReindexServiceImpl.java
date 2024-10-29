package net.sfkao.activityPlanner.application.service.elastic;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.SearcheableEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class ElasticReindexServiceImpl implements ElasticReindexService {

    @Autowired
    List<SearcheableEntityService> reindexableEntityServices;

    @Override
    public void reindex() {
        log.info("Starting reindex");
        Date start = new Date();
        for (SearcheableEntityService entityService : reindexableEntityServices) {
            log.info("Reindexing " + entityService.getClass().getName());
            entityService.reindex();
        }
        log.info("Termiando reindex en {} ms", (new Date().getTime() - start.getTime()));
    }

}
