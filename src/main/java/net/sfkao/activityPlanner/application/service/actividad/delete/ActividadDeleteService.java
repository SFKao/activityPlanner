package net.sfkao.activityPlanner.application.service.actividad.delete;

import org.springframework.stereotype.Service;

@Service
public interface ActividadDeleteService {

    void deleteById(String id);

}
