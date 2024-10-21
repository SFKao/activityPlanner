package net.sfkao.activityPlanner.elastic.application;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.elastic.domain.ReindexableEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class ElasticReindexServiceImpl implements ElasticReindexService {

    @Autowired
    List<ReindexableEntityService> reindexableEntityServices;

    @Override
    public void reindex() {
        log.info("Starting reindex");
        Date start = new Date();
        for (ReindexableEntityService entityService : reindexableEntityServices) {
            log.info("Reindexing " + entityService.getClass().getName());
            entityService.reindex();
        }
        log.info("Termiando reindex en {} ms", (new Date().getTime() - start.getTime()));
    }

}
