package net.sfkao.activityPlanner.application.service.elastic;

import org.springframework.stereotype.Service;

@Service
public interface ElasticReindexService {

    void reindex();

}
