package net.sfkao.activityPlanner.elastic.domain;

import org.springframework.stereotype.Service;

@Service
public interface ReindexableEntityService {

    void reindex();

}
