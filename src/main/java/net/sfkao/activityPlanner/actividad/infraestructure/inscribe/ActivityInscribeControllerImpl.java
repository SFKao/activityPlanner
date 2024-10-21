package net.sfkao.activityPlanner.actividad.infraestructure.inscribe;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.actividad.application.inscribe.ActividadInscribeService;
import net.sfkao.activityPlanner.exception.ActividadNotFoundException;
import net.sfkao.activityPlanner.exception.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Log4j2
@RestController
public class ActivityInscribeControllerImpl implements ActivityInscribeController {

    @Autowired
    ActividadInscribeService actividadInscribeService;

    @Override
    public ResponseEntity<?> inscribe(String activityId, Principal user) {
        String username = user.getName();
        try {
            actividadInscribeService.inscribe(username, activityId);
        } catch (ActividadNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UsuarioNotFoundException e) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok().build();
    }

}
