package net.sfkao.activityPlanner.usuario.application.updateDisponibilidad;

import net.sfkao.activityPlanner.exception.UsuarioNotFoundException;
import net.sfkao.activityPlanner.usuario.domain.model.Disponibilidad;
import net.sfkao.activityPlanner.usuario.domain.model.Usuario;
import net.sfkao.activityPlanner.usuario.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioUpdateDisponibilidadServiceImpl implements UsuarioUpdateDisponibilidadService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Disponibilidad> updateDisponibilidades(String username, List<Disponibilidad> disponibilidads) throws UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(UsuarioNotFoundException::new);

        usuario.setHorasDisponibles(disponibilidads);
        usuario = usuarioRepository.save(usuario);

        return usuario.getHorasDisponibles();
    }
}
