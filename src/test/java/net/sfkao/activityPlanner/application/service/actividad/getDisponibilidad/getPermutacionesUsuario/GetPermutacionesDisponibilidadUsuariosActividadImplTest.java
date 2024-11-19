package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutacionesUsuario;

import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getDisponibilidadesUsuario.ActividadGetDisponibilidadesUsuarios;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutaciones.GetPermutacionesOfDisponibilidadPerNumberOfPlayers;
import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores;
import net.sfkao.activityPlanner.domain.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPermutacionesDisponibilidadUsuariosActividadImplTest {

    @InjectMocks
    GetPermutacionesDisponibilidadUsuariosActividadImpl getPermutacionesDisponibilidadUsuariosActividad;

    @Mock
    ActividadGetDisponibilidadesUsuarios actividadGetDisponibilidadesUsuarios;

    @Mock
    GetPermutacionesOfDisponibilidadPerNumberOfPlayers getCombinacionesOfDisponibilidadesPerNumberOfPlayers;

    @Test
    void requierenTodos() {

        //GIVEN
        when(actividadGetDisponibilidadesUsuarios.getDisponibilidadesUsuarios(any())).thenReturn(disponibilidadesUsuarios1());

        //WHEN
        List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> permutacionesDisponibiliades = getPermutacionesDisponibilidadUsuariosActividad.getPermutacionesDisponibiliades(actividad1());

        //THEN
        assertEquals(permutacionesDisponibiliades.size(), 1);
        assertEquals(permutacionesDisponibiliades.get(0).getDisponibilidadesUsuarios(), disponibilidadesUsuarios1());

    }

    Actividad actividad1() {
        return new Actividad(
                "1", "nombre", "descripcion", 1, 4, true, null,
                List.of(
                        new Usuario("q", "email", "null", "user1", 0, null, null,
                                List.of(
                                        new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                ), List.of()),
                        new Usuario("w", "email", "null", "user1", 0, null, null,
                                List.of(
                                        new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                ), List.of()),
                        new Usuario("e", "email", "null", "user1", 0, null, null,
                                List.of(
                                        new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                ), List.of())
                ),
                List.of()
        );
    }

    List<DisponibilidadesUsuario> disponibilidadesUsuarios1() {
        return List.of(
                new DisponibilidadesUsuario(
                        new ArrayList<>(),
                        List.of(
                                new Usuario("q", "email", "null", "user1", 0, null, null,
                                        List.of(
                                                new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                        ), List.of()),
                                new Usuario("w", "email", "null", "user1", 0, null, null,
                                        List.of(
                                                new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                        ), List.of()),
                                new Usuario("e", "email", "null", "user1", 0, null, null,
                                        List.of(
                                                new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                        ), List.of())
                        )
                )
        );
    }


    @Test
    void noRequierenTodos() {
        //GIVEN
        Actividad actividad = actividad2();
        
        //WHEN
        getPermutacionesDisponibilidadUsuariosActividad.getPermutacionesDisponibiliades(actividad);

        //THEN
        verify(actividadGetDisponibilidadesUsuarios).getDisponibilidadesUsuarios(any());

    }

    Actividad actividad2() {
        return new Actividad(
                "1", "nombre", "descripcion", 2, 3, false, null,
                List.of(
                        new Usuario("q", "email", "null", "user1", 0, null, null,
                                List.of(
                                        new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                ), List.of()),
                        new Usuario("w", "email", "null", "user1", 0, null, null,
                                List.of(
                                        new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                ), List.of()),
                        new Usuario("e", "email", "null", "user1", 0, null, null,
                                List.of(
                                        new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                ), List.of())
                ),
                List.of()
        );
    }


}