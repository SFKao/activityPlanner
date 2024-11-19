package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.calcularHoras;

import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores;
import net.sfkao.activityPlanner.domain.Usuario;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalcularHorasParaLasPermutacionesImplTest {

    CalcularHorasParaLasPermutacionesImpl calcularHorasParaLasPermutaciones = new CalcularHorasParaLasPermutacionesImpl();

    @Test
    void testEquals() {

        //GIVEN
        List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores = list1();

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios = calcularHorasParaLasPermutaciones.calcularHorasParaLasIntersecciones(permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores);

        //THEN
        assertEquals(1, disponibilidadesUsuarios.size());
        assert (disponibilidadesUsuarios.get(0).getHoras().contains(new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")));
        assertEquals(2, disponibilidadesUsuarios.get(0).getUsuarios().size());
    }

    List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> list1() {
        return List.of(
                new PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores(
                        List.of(
                                new DisponibilidadesUsuario(
                                        List.of(
                                                new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                        ),
                                        List.of(
                                                new Usuario(
                                                        "q", "email", "pass", "username", 0, null, null,
                                                        List.of(
                                                                new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                                        ),
                                                        List.of()
                                                )
                                        )
                                ),
                                new DisponibilidadesUsuario(
                                        List.of(
                                                new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                        ),
                                        List.of(
                                                new Usuario(
                                                        "w", "email", "pass", "username", 0, null, null,
                                                        List.of(
                                                                new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                                        ),
                                                        List.of()
                                                )
                                        )
                                )
                        )
                )
        );
    }

    @Test
    void testIntersection1On1() {

        //GIVEN
        List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores = list2();

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios = calcularHorasParaLasPermutaciones.calcularHorasParaLasIntersecciones(permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores);

        //THEN
        assertEquals(1, disponibilidadesUsuarios.size());
        assert (disponibilidadesUsuarios.get(0).getHoras().contains(new Disponibilidad(DayOfWeek.MONDAY, "18:30:00.000000+02:00", "21:30:00.000000+02:00")));
        assertEquals(2, disponibilidadesUsuarios.get(0).getUsuarios().size());
    }

    List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> list2() {
        return List.of(
                new PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores(
                        List.of(
                                new DisponibilidadesUsuario(
                                        List.of(
                                                new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                        ),
                                        List.of(
                                                new Usuario(
                                                        "q", "email", "pass", "username", 0, null, null,
                                                        List.of(
                                                                new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                                                        ),
                                                        List.of()
                                                )
                                        )
                                ),
                                new DisponibilidadesUsuario(
                                        List.of(
                                                new Disponibilidad(DayOfWeek.MONDAY, "18:30:00.000000+02:00", "22:30:00.000000+02:00")
                                        ),
                                        List.of(
                                                new Usuario(
                                                        "w", "email", "pass", "username", 0, null, null,
                                                        List.of(
                                                                new Disponibilidad(DayOfWeek.MONDAY, "18:30:00.000000+02:00", "22:30:00.000000+02:00")
                                                        ),
                                                        List.of()
                                                )
                                        )
                                )
                        )
                )
        );
    }

    @Test
    void testInterSection1On2() {

        //GIVEN
        List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores = list3();

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios = calcularHorasParaLasPermutaciones.calcularHorasParaLasIntersecciones(permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores);

        //THEN
        assertEquals(1, disponibilidadesUsuarios.size());
        assert (disponibilidadesUsuarios.get(0).getHoras().contains(new Disponibilidad(DayOfWeek.MONDAY, "13:30:00.000000+02:00", "16:00:00.000000+02:00")));
        assert (disponibilidadesUsuarios.get(0).getHoras().contains(new Disponibilidad(DayOfWeek.MONDAY, "19:30:00.000000+02:00", "21:30:00.000000+02:00")));
        assertEquals(2, disponibilidadesUsuarios.get(0).getUsuarios().size());
    }

    List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> list3() {
        return List.of(
                new PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores(
                        List.of(
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
                                                new Disponibilidad(DayOfWeek.MONDAY, "12:30:00.000000+02:00", "16:00:00.000000+02:00"),
                                                new Disponibilidad(DayOfWeek.MONDAY, "19:30:00.000000+02:00", "22:30:00.000000+02:00")
                                        ),
                                        List.of(
                                                new Usuario(
                                                        "w", "email", "pass", "username", 0, null, null,
                                                        List.of(
                                                                new Disponibilidad(DayOfWeek.MONDAY, "12:30:00.000000+02:00", "16:00:00.000000+02:00"),
                                                                new Disponibilidad(DayOfWeek.MONDAY, "19:30:00.000000+02:00", "22:30:00.000000+02:00")
                                                        ),
                                                        List.of()
                                                )
                                        )
                                )
                        )
                )
        );
    }

    @Test
    void testInterSection1On2ButDifferentDays() {

        //GIVEN
        List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores = list4();

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios = calcularHorasParaLasPermutaciones.calcularHorasParaLasIntersecciones(permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores);

        //THEN
        assertEquals(1, disponibilidadesUsuarios.size());
        assert (disponibilidadesUsuarios.get(0).getHoras().contains(new Disponibilidad(DayOfWeek.MONDAY, "13:30:00.000000+02:00", "16:00:00.000000+02:00")));
        assertEquals(2, disponibilidadesUsuarios.get(0).getUsuarios().size());
    }

    List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> list4() {
        return List.of(
                new PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores(
                        List.of(
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
                                                new Disponibilidad(DayOfWeek.MONDAY, "12:30:00.000000+02:00", "16:00:00.000000+02:00"),
                                                new Disponibilidad(DayOfWeek.TUESDAY, "19:30:00.000000+02:00", "22:30:00.000000+02:00")
                                        ),
                                        List.of(
                                                new Usuario(
                                                        "w", "email", "pass", "username", 0, null, null,
                                                        List.of(
                                                                new Disponibilidad(DayOfWeek.MONDAY, "12:30:00.000000+02:00", "16:00:00.000000+02:00"),
                                                                new Disponibilidad(DayOfWeek.TUESDAY, "19:30:00.000000+02:00", "22:30:00.000000+02:00")
                                                        ),
                                                        List.of()
                                                )
                                        )
                                )
                        )
                )
        );
    }

    @Test
    void testIntersectionDifferentDays() {

        //GIVEN
        List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores = list5();

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios = calcularHorasParaLasPermutaciones.calcularHorasParaLasIntersecciones(permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores);

        //THEN
        assertEquals(0, disponibilidadesUsuarios.size());
    }

    List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> list5() {
        return List.of(
                new PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores(
                        List.of(
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
                                                new Disponibilidad(DayOfWeek.WEDNESDAY, "12:30:00.000000+02:00", "16:00:00.000000+02:00"),
                                                new Disponibilidad(DayOfWeek.TUESDAY, "19:30:00.000000+02:00", "22:30:00.000000+02:00")
                                        ),
                                        List.of(
                                                new Usuario(
                                                        "w", "email", "pass", "username", 0, null, null,
                                                        List.of(
                                                                new Disponibilidad(DayOfWeek.WEDNESDAY, "12:30:00.000000+02:00", "16:00:00.000000+02:00"),
                                                                new Disponibilidad(DayOfWeek.TUESDAY, "19:30:00.000000+02:00", "22:30:00.000000+02:00")
                                                        ),
                                                        List.of()
                                                )
                                        )
                                )
                        )
                )
        );
    }

}