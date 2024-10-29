package net.sfkao.activityPlanner.application.service.usuario.updateDisponibilidad;

import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.exception.UsuarioNotFoundException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioUpdateDisponibilidadService {

    List<Disponibilidad> updateDisponibilidades(@NonNull String username, @NonNull List<Disponibilidad> disponibilidads) throws UsuarioNotFoundException;

}
