package net.sfkao.activityPlanner.application.port.out.actividad;

import net.sfkao.activityPlanner.domain.Actividad;

import java.util.List;
import java.util.Optional;

public interface ActividadPersistencePort {
    void deleteById(String id);

    List<Actividad> findAll();

    Optional<Actividad> findById(String idActividad);

    void reindex();

    Actividad save(Actividad actividad);

    List<Actividad> search(String search);

}
