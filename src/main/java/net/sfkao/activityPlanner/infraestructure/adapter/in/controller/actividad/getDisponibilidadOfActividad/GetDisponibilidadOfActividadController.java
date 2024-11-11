package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.getDisponibilidadOfActividad;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity")
public interface GetDisponibilidadOfActividadController {

    @GetMapping("/disponibilidad")
    ResponseEntity<?> getDisponibilidadOfActividadResponse(@RequestParam("id") String activityId);

}
