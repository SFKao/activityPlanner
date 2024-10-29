package net.sfkao.activityPlanner.application.service.actividad.search;

import net.sfkao.activityPlanner.domain.Actividad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActividadSearchService {

    List<Actividad> search(String search);

}
