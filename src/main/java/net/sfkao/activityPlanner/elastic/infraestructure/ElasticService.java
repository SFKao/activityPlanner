package net.sfkao.activityPlanner.elastic.infraestructure;

import org.springframework.stereotype.Service;

@Service
public interface ElasticService {

    void reindex();

}
