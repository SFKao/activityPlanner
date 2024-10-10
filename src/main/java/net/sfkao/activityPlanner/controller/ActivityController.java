package net.sfkao.activityPlanner.controller;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.model.Actividad;
import net.sfkao.activityPlanner.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    ActividadService actividadService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(actividadService.getAll());
        } catch (Exception e) {
            log.error("No se ha podido devolver todas las actividades", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Actividad actividad){
        actividad = actividadService.save(actividad);
        return ResponseEntity.ok(actividad);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Actividad actividad){
        actividad = actividadService.save(actividad);
        return ResponseEntity.ok(actividad);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "search") String search) {

        return ResponseEntity.ok(actividadService.search(search));

    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestParam(name = "id") String id){
        actividadService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/test")
    public ResponseEntity<?> save() {
        List<Actividad> actividads = new ArrayList<>();

        actividads.add(new Actividad("Black ops 2", "Jugar unos zombies", 1, 4, false, null));
        actividads.add(new Actividad("Black ops 3", "Jugar aun mas zombies", 1, 4, false, null));
        actividads.add(new Actividad("Clank", "Jugar en el tabletop simulator un Clank", 2, 4, false, null));
        actividads.add(new Actividad("Quiplash", "Jugar unos quiplash", 3, 8, false, null));
        actividads.add(new Actividad("Murder Trivia", "Jugar un murder trivia", 3, 8, false, null));

        actividads.forEach(actividad -> actividadService.save(actividad));

        return ResponseEntity.ok().build();
    }

}
