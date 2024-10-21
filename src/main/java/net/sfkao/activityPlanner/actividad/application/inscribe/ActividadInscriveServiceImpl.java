package net.sfkao.activityPlanner.actividad.application.inscribe;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import net.sfkao.activityPlanner.actividad.domain.repository.ActividadRepository;
import net.sfkao.activityPlanner.exception.ActividadNotFoundException;
import net.sfkao.activityPlanner.exception.UsuarioNotFoundException;
import net.sfkao.activityPlanner.usuario.domain.model.Usuario;
import net.sfkao.activityPlanner.usuario.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActividadInscriveServiceImpl implements ActividadInscribeService {

    @Autowired
    ActividadRepository actividadRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void inscribe(String username, String actividadId) throws ActividadNotFoundException, UsuarioNotFoundException {
        Optional<Actividad> actividadOptional = actividadRepository.findById(actividadId);
        Actividad actividad = actividadOptional.orElseThrow(ActividadNotFoundException::new);
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
        Usuario usuario = usuarioOptional.orElseThrow(UsuarioNotFoundException::new);

        usuario.getActividadesInscritas().add(actividad);
        usuarioRepository.save(usuario);

    }

}
