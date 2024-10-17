package net.sfkao.activityPlanner.actividad.application.search;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActividadSearchService {

    List<Actividad> search(String search);

}
