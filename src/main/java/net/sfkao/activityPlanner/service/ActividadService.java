package net.sfkao.activityPlanner.service;

import net.sfkao.activityPlanner.model.Actividad;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ActividadService {

    List<Actividad> getAll();
    List<Actividad> search(String search);
    Optional<Actividad> getById(String id);

    Actividad save(Actividad actividad);

    void deleteById(String id);



}
