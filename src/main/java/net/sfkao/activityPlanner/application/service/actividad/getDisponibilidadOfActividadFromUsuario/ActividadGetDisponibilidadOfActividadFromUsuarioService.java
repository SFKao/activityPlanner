package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidadOfActividadFromUsuario;

import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.exception.UsuarioNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActividadGetDisponibilidadOfActividadFromUsuarioService {

    List<Actividad> getDisponibilidades(String username) throws UsuarioNotFoundException;

}
