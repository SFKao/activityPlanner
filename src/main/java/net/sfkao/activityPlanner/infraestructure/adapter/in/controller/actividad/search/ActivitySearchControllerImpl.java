package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.search;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.application.port.in.actividad.ActividadSearchPort;
import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.mapper.ActividadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class ActivitySearchControllerImpl implements ActivitySearchController, ActividadSearchPort {

    @Autowired
    @Qualifier("ActividadSearchService")
    ActividadSearchPort actividadSearchPort;

    ActividadMapper actividadMapper = ActividadMapper.INSTANCE;


    @Override
    public ResponseEntity<?> searchResponse(String search) {
        return ResponseEntity.ok(search(search).stream().map(actividadMapper::toDTO).toList());
    }

    @Override
    public List<Actividad> search(String search) {
        return actividadSearchPort.search(search);
    }
}
