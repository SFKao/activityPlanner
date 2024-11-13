package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.actividad.getDisponibilidadOfActividadesOfUsuario;

import lombok.extern.log4j.Log4j2;
import net.sfkao.activityPlanner.application.port.in.actividad.ActividadGetDisponibilidadOfActividadesOfUsuarioPort;
import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.dto.ActividadDTO;
import net.sfkao.activityPlanner.domain.exception.UsuarioNotFoundException;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.mapper.ActividadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@Log4j2
public class GetDisponibilidadOfActividadesOfUsuarioControllerImpl implements GetDisponibilidadOfActividadesOfUsuarioController, ActividadGetDisponibilidadOfActividadesOfUsuarioPort {

    @Autowired
    @Qualifier("actividadGetDisponibilidadOfActividadFromUsuarioService")
    ActividadGetDisponibilidadOfActividadesOfUsuarioPort actividadGetDisponibilidadOfActividadesOfUsuarioPort;

    ActividadMapper actividadMapper = ActividadMapper.INSTANCE;

    @Override
    public ResponseEntity<?> getDisponibilidadesOfActividadesOfUsuarioResponse(Principal user) {
        try {
            List<Actividad> disponibilidades = getDisponibilidades(user.getName());
            List<ActividadDTO> list = disponibilidades.stream().map(actividadMapper::toDTO).toList();
            return ResponseEntity.ok(list);
        } catch (UsuarioNotFoundException e) {
            log.error(e);
            return ResponseEntity.internalServerError().build();
        }
    }


    @Override
    public List<Actividad> getDisponibilidades(String username) throws UsuarioNotFoundException {
        return actividadGetDisponibilidadOfActividadesOfUsuarioPort.getDisponibilidades(username);
    }
}
