package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutaciones;

import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores;
import net.sfkao.activityPlanner.domain.Usuario;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetPermutacionesOfDisponibilidadPerNumberOfPlayersImplTest {

    GetPermutacionesOfDisponibilidadPerNumberOfPlayersImpl getPermutacionesOfDisponibilidadPerNumberOfPlayers = new GetPermutacionesOfDisponibilidadPerNumberOfPlayersImpl();

    @Test
    void notEnoughPlayers() {
        //GIVEN
        int minPlayers = 4;
        int maxPlayers = 10;
        //WHEN
        List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> combinacionesOfDisponibilidadesPerNumberOfPlayers = getPermutacionesOfDisponibilidadPerNumberOfPlayers.getCombinacionesOfDisponibilidadesPerNumberOfPlayers(List.of(
                new DisponibilidadesUsuario(
                        List.of(
                                new Disponibilidad(DayOfWeek.MONDAY, "13:30:00.000000+02:00", "21:30:00.000000+02:00")
                        ),
                        List.of(
                                new Usuario(
                                        "q", "email", "pass", "username", 0, null, null,
                                        List.of(
                                                new Disponibilidad(DayOfWeek.MONDAY, "13:30:00.000000+02:00", "21:30:00.000000+02:00")
                                        ),
                                        List.of()
                                )
                        )
                ),
                new DisponibilidadesUsuario(
                        List.of(
                                new Disponibilidad(DayOfWeek.MONDAY, "13:30:00.000000+02:00", "21:30:00.000000+02:00")
                        ),
                        List.of(
                                new Usuario(
                                        "q", "email", "pass", "username", 0, null, null,
                                        List.of(
                                                new Disponibilidad(DayOfWeek.MONDAY, "13:30:00.000000+02:00", "21:30:00.000000+02:00")
                                        ),
                                        List.of()
                                )
                        )
                )
        ), minPlayers, maxPlayers);

        //THEN
        assert (combinacionesOfDisponibilidadesPerNumberOfPlayers.isEmpty());

    }

    @Test
    void combinacion2Jugadores() {
        //GIVEN
        int minPlayers = 2;
        int maxPlayers = 4;
        //WHEN
        List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> combinacionesOfDisponibilidadesPerNumberOfPlayers = getPermutacionesOfDisponibilidadPerNumberOfPlayers.getCombinacionesOfDisponibilidadesPerNumberOfPlayers(List.of(
                new DisponibilidadesUsuario(
                        List.of(
                                new Disponibilidad(DayOfWeek.MONDAY, "13:30:00.000000+02:00", "21:30:00.000000+02:00")
                        ),
                        List.of(
                                new Usuario(
                                        "q", "email", "pass", "username", 0, null, null,
                                        List.of(
                                                new Disponibilidad(DayOfWeek.MONDAY, "13:30:00.000000+02:00", "21:30:00.000000+02:00")
                                        ),
                                        List.of()
                                )
                        )
                ),
                new DisponibilidadesUsuario(
                        List.of(
                                new Disponibilidad(DayOfWeek.MONDAY, "13:30:00.000000+02:00", "21:30:00.000000+02:00")
                        ),
                        List.of(
                                new Usuario(
                                        "q", "email", "pass", "username", 0, null, null,
                                        List.of(
                                                new Disponibilidad(DayOfWeek.MONDAY, "13:30:00.000000+02:00", "21:30:00.000000+02:00")
                                        ),
                                        List.of()
                                )
                        )
                ),
                new DisponibilidadesUsuario(
                        List.of(
                                new Disponibilidad(DayOfWeek.MONDAY, "13:30:00.000000+02:00", "21:30:00.000000+02:00")
                        ),
                        List.of(
                                new Usuario(
                                        "q", "email", "pass", "username", 0, null, null,
                                        List.of(
                                                new Disponibilidad(DayOfWeek.MONDAY, "13:30:00.000000+02:00", "21:30:00.000000+02:00")
                                        ),
                                        List.of()
                                )
                        )
                )
        ), minPlayers, maxPlayers);

        //THEN
        assertEquals(4, combinacionesOfDisponibilidadesPerNumberOfPlayers.size());

    }
}