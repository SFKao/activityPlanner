package net.sfkao.activityPlanner.infraestructure.adapter.out.elastic;

import org.springframework.stereotype.Service;

@Service
public interface SearcheableEntityService {

    void reindex();

}
