package net.sfkao.activityPlanner.application.service.usuario.updateDisponibilidad;

import net.sfkao.activityPlanner.application.port.out.usuario.UsuarioPersistencePort;
import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.exception.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioUpdateDisponibilidadServiceImpl implements UsuarioUpdateDisponibilidadService {

    @Autowired
    UsuarioPersistencePort usuarioPersistencePort;

    @Override
    public List<Disponibilidad> updateDisponibilidades(String username, List<Disponibilidad> disponibilidads) throws UsuarioNotFoundException {
        Usuario usuario = usuarioPersistencePort.findByUsername(username).orElseThrow(UsuarioNotFoundException::new);

        usuario.setHorasDisponibles(disponibilidads);
        usuario = usuarioPersistencePort.save(usuario);

        return usuario.getHorasDisponibles();
    }
}
