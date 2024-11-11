package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidadOfActividadFromUsuario;

import net.sfkao.activityPlanner.application.port.out.usuario.UsuarioPersistencePort;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.calcularHoras.CalcularHorasParaLasPermutaciones;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.filtrarActividadesDeUsuario.FiltrarActividadesQueNoContenganElUsuario;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutacionesUsuario.GetPermutacionesDisponibilidadUsuariosActividad;
import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores;
import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.exception.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActividadGetDisponibilidadOfActividadFromUsuarioServiceImpl implements ActividadGetDisponibilidadOfActividadFromUsuarioService {

    @Autowired
    UsuarioPersistencePort usuarioPersistencePort;

    @Autowired
    GetPermutacionesDisponibilidadUsuariosActividad getPermutacionesDisponibilidadUsuariosActividad;

    @Autowired
    CalcularHorasParaLasPermutaciones calcularHorasParaLasIntersecciones;

    @Autowired
    FiltrarActividadesQueNoContenganElUsuario filtrarActividadesQueNoContenganElUsuario;

    @Override
    public List<Actividad> getDisponibilidades(String username) throws UsuarioNotFoundException {
        Usuario usuario = usuarioPersistencePort.findByUsername(username).orElseThrow(UsuarioNotFoundException::new);

        List<Actividad> actividades = new ArrayList<>();
        for (Actividad actividad : usuario.getActividadesInscritas()) {
            //Obtengo las disponibilidades a calcular por el numero de jugadores
            List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> permutacionesDisponibiliades = getPermutacionesDisponibilidadUsuariosActividad.getPermutacionesDisponibiliades(actividad);
            //Filtro para que todas tengan el usuario
            permutacionesDisponibiliades = filtrarActividadesQueNoContenganElUsuario.filtarPermutacionesQueNoContenganElUsuario(permutacionesDisponibiliades, usuario);
            //Calculo las horas posibles de cada combinacion
            List<DisponibilidadesUsuario> disponibilidadesUsuarios = calcularHorasParaLasIntersecciones.calcularHorasParaLasIntersecciones(permutacionesDisponibiliades);
            actividad.setDisponibilidades(disponibilidadesUsuarios);
            actividades.add(actividad);
        }
        return actividades;
    }

}
