package net.sfkao.activityPlanner.actividad.application.delete;

import org.springframework.stereotype.Service;

@Service
public interface ActividadDeleteService {

    void deleteById(String id);

}
