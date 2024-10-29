package net.sfkao.activityPlanner.application.service.actividad.inscribe;

import net.sfkao.activityPlanner.application.port.in.actividad.ActividadInscribePort;
import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import net.sfkao.activityPlanner.application.port.out.usuario.UsuarioPersistencePort;
import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.exception.ActividadNotFoundException;
import net.sfkao.activityPlanner.domain.exception.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("ActividadInscribeService")
public class ActividadInscriveServiceImpl implements ActividadInscribeService, ActividadInscribePort {

    @Autowired
    ActividadPersistencePort actividadPersistencePort;

    @Autowired
    UsuarioPersistencePort usuarioPersistencePort;

    @Override
    public void inscribe(String username, String actividadId) throws ActividadNotFoundException, UsuarioNotFoundException {
        Optional<Actividad> actividadOptional = actividadPersistencePort.findById(actividadId);
        Actividad actividad = actividadOptional.orElseThrow(ActividadNotFoundException::new);
        Optional<Usuario> usuarioOptional = usuarioPersistencePort.findByUsername(username);
        Usuario usuario = usuarioOptional.orElseThrow(UsuarioNotFoundException::new);

        usuario.getActividadesInscritas().add(actividad);
        usuarioPersistencePort.save(usuario);

    }

}
