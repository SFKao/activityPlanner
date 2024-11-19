package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidadOfActividadFromUsuario;


import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import net.sfkao.activityPlanner.application.port.out.usuario.UsuarioPersistencePort;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.calcularHoras.CalcularHorasParaLasPermutaciones;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.calcularHoras.CalcularHorasParaLasPermutacionesImpl;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.filtrarActividadesDeUsuario.FiltrarActividadesQueNoContenganElUsuario;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.filtrarActividadesDeUsuario.FiltrarActividadesQueNoContenganElUsuarioImpl;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getDisponibilidadesUsuario.ActividadGetDisponibilidadesUsuariosImpl;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutaciones.GetPermutacionesOfDisponibilidadPerNumberOfPlayersImpl;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutacionesUsuario.GetPermutacionesDisponibilidadUsuariosActividadImpl;
import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.exception.UsuarioNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActividadGetDisponibilidadOfActividadFromUsuarioServiceImplTest {

    @InjectMocks
    ActividadGetDisponibilidadOfActividadFromUsuarioServiceImpl actividadGetDisponibilidadOfActividadFromUsuarioService;

    @Mock
    ActividadPersistencePort actividadPersistencePort;

    @Mock
    UsuarioPersistencePort usuarioPersistencePort;

    @Spy
    GetPermutacionesDisponibilidadUsuariosActividadImpl getPermutacionesDisponibilidadUsuariosActividad = new GetPermutacionesDisponibilidadUsuariosActividadImpl(new ActividadGetDisponibilidadesUsuariosImpl(), new GetPermutacionesOfDisponibilidadPerNumberOfPlayersImpl());

    @Spy
    CalcularHorasParaLasPermutaciones calcularHorasParaLasPermutaciones = new CalcularHorasParaLasPermutacionesImpl();

    @Spy
    FiltrarActividadesQueNoContenganElUsuario filtrarActividadesQueNoContenganElUsuario = new FiltrarActividadesQueNoContenganElUsuarioImpl();

    @Test
    void obtenerDisponibilidadesDeLasActividadesDeUnUsuario() throws UsuarioNotFoundException {

        //GIVEN
        when(usuarioPersistencePort.findByUsername(any())).thenReturn(usuario1WithActividad());
        when(actividadPersistencePort.findById("1")).thenReturn(Optional.of(actividad1()));

        //WHEN
        List<Actividad> disponibilidades = actividadGetDisponibilidadOfActividadFromUsuarioService.getDisponibilidades("");

        //THEN

        //Todos los resultados tienen que tener al usuario en cuestion
        List<DisponibilidadesUsuario> disponibilidades1 = disponibilidades.get(0).getDisponibilidades();
        for (DisponibilidadesUsuario d : disponibilidades1) {
            if (!d.getUsuarios().contains(usuario1())) {
                fail();
            }
        }

        /*
        Combinaciones que deberian aparecer (min: 1, max: 4)
        1
        1 2
        1 3
        1 4
        1 5
        1 2 3
        1 2 4
        1 2 5
        1 3 4
        1 3 5
        1 4 5
        1 2 3 4
        1 2 3 5
        1 2 4 5
        1 3 4 5
        */
        assertEquals(disponibilidades1.size(), 15);

    }

    Optional<Usuario> usuario1WithActividad() {
        Usuario usuario = usuario1();
        usuario.getActividadesInscritas().add(actividad1());
        return Optional.of(usuario);
    }

    Usuario usuario1() {
        return new Usuario(
                "q", "email", "pass", "username", 0, null, null,
                List.of(
                        new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                ),
                new ArrayList<>()
        );
    }

    Usuario usuario2() {
        return new Usuario(
                "w", "email", "pass", "username", 0, null, null,
                List.of(
                        new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                ),
                List.of(

                )
        );
    }


    Usuario usuario3() {
        return new Usuario(
                "e", "email", "pass", "username", 0, null, null,
                List.of(
                        new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                ),
                List.of(

                )
        );
    }


    Usuario usuario4() {
        return new Usuario(
                "r", "email", "pass", "username", 0, null, null,
                List.of(
                        new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                ),
                List.of(

                )
        );
    }


    Usuario usuario5() {
        return new Usuario(
                "t", "email", "pass", "username", 0, null, null,
                List.of(
                        new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                ),
                List.of(

                )
        );
    }


    Actividad actividad1() {
        return new Actividad(
                "1", "nombre", "descripcion", 1, 4, false, null,
                List.of(usuario1(), usuario2(), usuario3(), usuario4(), usuario5()),
                List.of()
        );
    }

}