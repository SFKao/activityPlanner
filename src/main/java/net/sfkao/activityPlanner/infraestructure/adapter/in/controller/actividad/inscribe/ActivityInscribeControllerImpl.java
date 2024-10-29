package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.inscribe;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.application.port.in.actividad.ActividadInscribePort;
import net.sfkao.activityPlanner.domain.exception.ActividadNotFoundException;
import net.sfkao.activityPlanner.domain.exception.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Log4j2
@RestController
public class ActivityInscribeControllerImpl implements ActivityInscribeController, ActividadInscribePort {

    @Autowired
    @Qualifier("ActividadInscribeService")
    ActividadInscribePort actividadInscribePort;

    @Override
    public ResponseEntity<?> inscribe(String activityId, Principal user) {
        String username = user.getName();
        try {
            inscribe(username, activityId);
        } catch (ActividadNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UsuarioNotFoundException e) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public void inscribe(String username, String actividadId) throws ActividadNotFoundException, UsuarioNotFoundException {
        actividadInscribePort.inscribe(username, actividadId);
    }
}
