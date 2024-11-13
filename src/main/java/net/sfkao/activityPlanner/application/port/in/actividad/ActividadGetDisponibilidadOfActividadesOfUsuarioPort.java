package net.sfkao.activityPlanner.application.port.in.actividad;

import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.exception.UsuarioNotFoundException;

import java.util.List;

public interface ActividadGetDisponibilidadOfActividadesOfUsuarioPort {

    List<Actividad> getDisponibilidades(String username) throws UsuarioNotFoundException;

}
