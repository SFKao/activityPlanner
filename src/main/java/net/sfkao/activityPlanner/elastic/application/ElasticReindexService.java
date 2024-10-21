package net.sfkao.activityPlanner.elastic.application;

import org.springframework.stereotype.Service;

@Service
public interface ElasticReindexService {

    void reindex();

}
