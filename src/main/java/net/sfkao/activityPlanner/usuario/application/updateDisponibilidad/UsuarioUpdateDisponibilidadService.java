package net.sfkao.activityPlanner.usuario.application.updateDisponibilidad;

import net.sfkao.activityPlanner.exception.UsuarioNotFoundException;
import net.sfkao.activityPlanner.usuario.domain.model.Disponibilidad;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioUpdateDisponibilidadService {

    List<Disponibilidad> updateDisponibilidades(@NonNull String username, @NonNull List<Disponibilidad> disponibilidads) throws UsuarioNotFoundException;

}
