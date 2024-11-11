package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad;

import net.sfkao.activityPlanner.application.port.out.actividad.ActividadPersistencePort;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.calcularHoras.CalcularHorasParaLasPermutaciones;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.calcularHoras.CalcularHorasParaLasPermutacionesImpl;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getDisponibilidadesUsuario.ActividadGetDisponibilidadesUsuarios;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getDisponibilidadesUsuario.ActividadGetDisponibilidadesUsuariosImpl;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutaciones.GetPermutacionesOfDisponibilidadPerNumberOfPlayersImpl;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutacionesUsuario.GetPermutacionesDisponibilidadUsuariosActividad;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutacionesUsuario.GetPermutacionesDisponibilidadUsuariosActividadImpl;
import net.sfkao.activityPlanner.application.service.actividad.getDisponibilidadOfActividad.ActividadGetDisponibilidadServiceImpl;
import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.exception.ActividadNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActividadGetDisponibilidadServiceImplTest {

    @InjectMocks
    ActividadGetDisponibilidadServiceImpl actividadGetDisponibilidadService;

    @Mock
    ActividadPersistencePort actividadPersistencePort;

    @Spy
    GetPermutacionesDisponibilidadUsuariosActividad getPermutacionesDisponibilidadUsuariosActividad = new GetPermutacionesDisponibilidadUsuariosActividadImpl(
            new ActividadGetDisponibilidadesUsuariosImpl(), new GetPermutacionesOfDisponibilidadPerNumberOfPlayersImpl()
    );

    @Spy
    CalcularHorasParaLasPermutaciones calcularHorasParaLasIntersecciones = new CalcularHorasParaLasPermutacionesImpl();

    @Spy
    ActividadGetDisponibilidadesUsuarios actividadGetDisponibilidadesUsuarios = new ActividadGetDisponibilidadesUsuariosImpl();


    /**
     * Comprueba una interseccion
     */
    @Test
    void interseccionActividadInterseccionBasica() {

        //GIVEN
        when(actividadPersistencePort.findById(any())).thenReturn(actividadInterseccionBasica());


        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios;
        try {
            disponibilidadesUsuarios = actividadGetDisponibilidadService.getDisponibilidadActividad("a");
        } catch (ActividadNotFoundException e) {
            fail("No encontrado");
            return;
        }
        //THEN

        assert (disponibilidadesUsuarios.size() == 1);
        assert (disponibilidadesUsuarios.get(0).getUsuarios().size() == 2);
        assert (disponibilidadesUsuarios.get(0).getUsuarios().get(0).getId().equals("q") || disponibilidadesUsuarios.get(0).getUsuarios().get(1).getId().equals("q"));
        assert (disponibilidadesUsuarios.get(0).getUsuarios().get(0).getId().equals("w") || disponibilidadesUsuarios.get(0).getUsuarios().get(1).getId().equals("w"));

        assert (disponibilidadesUsuarios.get(0).getDisponibilidades().contains(new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:00:00.000000+02:00")));
        assert (disponibilidadesUsuarios.get(0).getDisponibilidades().get(0).getDia().equals(DayOfWeek.MONDAY));
    }

    Optional<Actividad> actividadInterseccionBasica() {
        Actividad a = new Actividad();
        a.setMinJugadores(2);
        a.setMaxJugadores(4);
        a.setId("a");
        a.setNombre("Actividad prueba");
        a.setDescripcion("aaa");
        a.setRequierenTodos(false);

        a.setUsuariosInscritos(List.of(
                new Usuario("q", "w@email.em", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                        ), List.of()),
                new Usuario("w", "w@email.em", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.MONDAY, "16:00:00.000000+02:00", "21:00:00.000000+02:00")
                        ), List.of())
        ));

        return Optional.of(a);
    }


    /**
     * Los dias de los usuarios son distintos
     */
    @Test
    void interseccionDiasDistintos() {

        //GIVEN
        when(actividadPersistencePort.findById(any())).thenReturn(actividadDiasDistintos());

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios;
        try {
            disponibilidadesUsuarios = actividadGetDisponibilidadService.getDisponibilidadActividad("a");
        } catch (ActividadNotFoundException e) {
            fail("No encontrado");
            return;
        }
        //THEN
        assert (disponibilidadesUsuarios.isEmpty());
    }

    Optional<Actividad> actividadDiasDistintos() {
        Actividad a = new Actividad();
        a.setMinJugadores(2);
        a.setMaxJugadores(4);
        a.setId("a");
        a.setNombre("Actividad prueba");
        a.setDescripcion("aaa");
        a.setRequierenTodos(false);

        a.setUsuariosInscritos(List.of(
                new Usuario("q", "w@email.em", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                        ), List.of()),
                new Usuario("w", "w@email.em", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.TUESDAY, "16:00:00.000000+02:00", "21:00:00.000000+02:00")
                        ), List.of())
        ));

        return Optional.of(a);
    }

    /**
     * Comprueba si la interseccion es en otro dia
     */
    @Test
    void interseccionActividadMiercoles() {

        //GIVEN
        when(actividadPersistencePort.findById(any())).thenReturn(actividadMiercoles());

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios;
        try {
            disponibilidadesUsuarios = actividadGetDisponibilidadService.getDisponibilidadActividad("a");
        } catch (ActividadNotFoundException e) {
            fail("No encontrado");
            return;
        }
        //THEN

        assert (disponibilidadesUsuarios.size() == 1);
        assert (disponibilidadesUsuarios.get(0).getUsuarios().size() == 2);
        assert (disponibilidadesUsuarios.get(0).getUsuarios().get(0).getId().equals("q") || disponibilidadesUsuarios.get(0).getUsuarios().get(1).getId().equals("q"));
        assert (disponibilidadesUsuarios.get(0).getUsuarios().get(0).getId().equals("w") || disponibilidadesUsuarios.get(0).getUsuarios().get(1).getId().equals("w"));

        assert (disponibilidadesUsuarios.get(0).getDisponibilidades().contains(new Disponibilidad(DayOfWeek.WEDNESDAY, "17:30:00.000000+02:00", "21:00:00.000000+02:00")));
    }

    Optional<Actividad> actividadMiercoles() {
        Actividad a = new Actividad();
        a.setMinJugadores(2);
        a.setMaxJugadores(4);
        a.setId("a");
        a.setNombre("Actividad prueba");
        a.setDescripcion("aaa");
        a.setRequierenTodos(false);

        a.setUsuariosInscritos(List.of(
                new Usuario("q", "w@email.em", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.WEDNESDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                        ), List.of()),
                new Usuario("w", "w@email.em", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.WEDNESDAY, "16:00:00.000000+02:00", "21:00:00.000000+02:00")
                        ), List.of())
        ));

        return Optional.of(a);
    }


    /**
     * Comprueba varias intersecciones, incluyendo ruido (disponibilidades que no chocan por dias distintos)
     */
    @Test
    void interseccionActividadVariasDisponibilidadesConRuido() {

        //GIVEN
        when(actividadPersistencePort.findById(any())).thenReturn(actividadVariasDisponibilidadesConRuido());

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios;
        try {
            disponibilidadesUsuarios = actividadGetDisponibilidadService.getDisponibilidadActividad("a");
        } catch (ActividadNotFoundException e) {
            fail("No encontrado");
            return;
        }
        //THEN

        assert (disponibilidadesUsuarios.size() == 1);
        assert (disponibilidadesUsuarios.get(0).getUsuarios().size() == 2);
        assert (disponibilidadesUsuarios.get(0).getUsuarios().get(0).getId().equals("q") || disponibilidadesUsuarios.get(0).getUsuarios().get(1).getId().equals("q"));
        assert (disponibilidadesUsuarios.get(0).getUsuarios().get(0).getId().equals("w") || disponibilidadesUsuarios.get(0).getUsuarios().get(1).getId().equals("w"));


        assert (disponibilidadesUsuarios.get(0).getDisponibilidades().contains(new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:00:00.000000+02:00")));
        assert (disponibilidadesUsuarios.get(0).getDisponibilidades().contains(new Disponibilidad(DayOfWeek.WEDNESDAY, "18:00:00.000000+02:00", "21:30:00.000000+02:00")));


    }

    Optional<Actividad> actividadVariasDisponibilidadesConRuido() {
        Actividad a = new Actividad();
        a.setMinJugadores(2);
        a.setMaxJugadores(4);
        a.setId("a");
        a.setNombre("Actividad prueba");
        a.setDescripcion("aaa");
        a.setRequierenTodos(false);

        a.setUsuariosInscritos(List.of(
                new Usuario("q", "w@email.em", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.MONDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00"),
                                new Disponibilidad(DayOfWeek.WEDNESDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00"),
                                new Disponibilidad(DayOfWeek.FRIDAY, "17:30:00.000000+02:00", "21:30:00.000000+02:00")
                        ), List.of()),
                new Usuario("w", "w@email.em", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.MONDAY, "16:00:00.000000+02:00", "21:00:00.000000+02:00"),
                                new Disponibilidad(DayOfWeek.WEDNESDAY, "18:00:00.000000+02:00", "22:00:00.000000+02:00"),
                                new Disponibilidad(DayOfWeek.THURSDAY, "20:00:00.000000+02:00", "21:00:00.000000+02:00")
                        ), List.of())
        ));

        return Optional.of(a);
    }

    /**
     * Comprueba si hay una interseccion grande con otras que son contenidas dentro
     */
    @Test
    void interseccionActividadDisponibilidadesDivididas() {

        //GIVEN
        when(actividadPersistencePort.findById(any())).thenReturn(actividadDisponibilidadesDivididas());

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios;
        try {
            disponibilidadesUsuarios = actividadGetDisponibilidadService.getDisponibilidadActividad("a");
        } catch (ActividadNotFoundException e) {
            fail("No encontrado");
            return;
        }
        //THEN

        assert (disponibilidadesUsuarios.size() == 1);
        assert (disponibilidadesUsuarios.get(0).getUsuarios().size() == 2);

        assert (disponibilidadesUsuarios.get(0).getDisponibilidades().contains(new Disponibilidad(DayOfWeek.FRIDAY, "16:00:00.000000+02:00", "21:00:00.000000+02:00")));
        assert (disponibilidadesUsuarios.get(0).getDisponibilidades().contains(new Disponibilidad(DayOfWeek.FRIDAY, "22:00:00.000000+02:00", "23:30:00.000000+02:00")));
    }

    Optional<Actividad> actividadDisponibilidadesDivididas() {
        Actividad a = new Actividad();
        a.setMinJugadores(2);
        a.setMaxJugadores(4);
        a.setId("a");
        a.setNombre("Actividad prueba");
        a.setDescripcion("aaa");
        a.setRequierenTodos(false);

        a.setUsuariosInscritos(List.of(
                new Usuario("q", "w@email.em", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "15:30:00.000000+02:00", "23:30:00.000000+02:00")
                        ), List.of()),
                new Usuario("w", "w@email.em", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "16:00:00.000000+02:00", "21:00:00.000000+02:00"),
                                new Disponibilidad(DayOfWeek.FRIDAY, "22:00:00.000000+02:00", "23:45:00.000000+02:00")
                        ), List.of())
        ));

        return Optional.of(a);
    }

    /**
     * Comprueba si el numero de jugadores es 2 pero hay 3 inscritos, no necesitan todos
     */
    @Test
    void interseccion2Jugadores3Inscritos() {

        //GIVEN
        when(actividadPersistencePort.findById(any())).thenReturn(actividad2Jugadores3Inscritos());

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios;
        try {
            disponibilidadesUsuarios = actividadGetDisponibilidadService.getDisponibilidadActividad("a");
        } catch (ActividadNotFoundException e) {
            fail("No encontrado");
            return;
        }
        //THEN

        assert (disponibilidadesUsuarios.size() == 3);
        assert (disponibilidadesUsuarios.get(0).getUsuarios().size() == 2);
        assert (disponibilidadesUsuarios.get(1).getUsuarios().size() == 2);
        assert (disponibilidadesUsuarios.get(2).getUsuarios().size() == 2);

        //Checkeo Q y W
        assert (disponibilidadesUsuarios.contains(new DisponibilidadesUsuario(
                List.of(
                        new Disponibilidad(DayOfWeek.FRIDAY, "16:00:00.000000+02:00", "21:00:00.000000+02:00"),
                        new Disponibilidad(DayOfWeek.FRIDAY, "22:00:00.000000+02:00", "23:30:00.000000+02:00")
                ),
                List.of(
                        new Usuario("q", "email"),
                        new Usuario("w", "email")
                )
        )));

        //Checkeo Q y E
        assert (disponibilidadesUsuarios.contains(new DisponibilidadesUsuario(
                List.of(
                        new Disponibilidad(DayOfWeek.FRIDAY, "16:00:00.000000+02:00", "21:30:00.000000+02:00")
                ),
                List.of(
                        new Usuario("q", "email"),
                        new Usuario("e", "email")
                )
        )));

        //Checkeo W y E
        assert (disponibilidadesUsuarios.contains(new DisponibilidadesUsuario(
                List.of(
                        new Disponibilidad(DayOfWeek.FRIDAY, "16:00:00.000000+02:00", "21:00:00.000000+02:00")
                ),
                List.of(
                        new Usuario("w", "email"),
                        new Usuario("e", "email")
                )
        )));

    }

    Optional<Actividad> actividad2Jugadores3Inscritos() {
        Actividad a = new Actividad();
        a.setMinJugadores(2);
        a.setMaxJugadores(2);
        a.setId("a");
        a.setNombre("Actividad prueba");
        a.setDescripcion("aaa");
        a.setRequierenTodos(false);

        a.setUsuariosInscritos(List.of(
                new Usuario("q", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "15:30:00.000000+02:00", "23:30:00.000000+02:00")
                        ), List.of()),
                new Usuario("w", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "16:00:00.000000+02:00", "21:00:00.000000+02:00"),
                                new Disponibilidad(DayOfWeek.FRIDAY, "22:00:00.000000+02:00", "23:45:00.000000+02:00")
                        ), List.of()),
                new Usuario("e", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "16:00:00.000000+02:00", "21:30:00.000000+02:00")
                        ), List.of())
        ));

        return Optional.of(a);
    }

    /**
     * Comprueba si el numero de jugadores es 2 a 4 pero hay 5 inscritos, no necesitan todos
     */
    @Test
    void interseccion2a4Jugadores5Inscritos() {

        //GIVEN
        when(actividadPersistencePort.findById(any())).thenReturn(actividad2a4Jugadores5Inscritos());

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios;
        try {
            disponibilidadesUsuarios = actividadGetDisponibilidadService.getDisponibilidadActividad("a");
        } catch (ActividadNotFoundException e) {
            fail("No encontrado");
            return;
        }
        //THEN
        assert (disponibilidadesUsuarios.contains(new DisponibilidadesUsuario(
                List.of(
                        new Disponibilidad(DayOfWeek.FRIDAY, "11:00:00.000000+02:00", "13:00:00.000000+02:00")
                ),
                List.of(
                        new Usuario("q", "email"),
                        new Usuario("w", "email")
                )
        )));

        assert (disponibilidadesUsuarios.contains(new DisponibilidadesUsuario(
                List.of(
                        new Disponibilidad(DayOfWeek.FRIDAY, "12:00:00.000000+02:00", "14:00:00.000000+02:00")
                ),
                List.of(
                        new Usuario("w", "email"),
                        new Usuario("r", "email")
                )
        )));

        assert (disponibilidadesUsuarios.stream().filter(d -> d.getUsuarios().equals(List.of(
                        new Usuario("q", "email"),
                        new Usuario("t", "email")
                )
        )).toList().isEmpty());

        assert (disponibilidadesUsuarios.stream().filter(d -> d.getUsuarios().size() == 5).toList().isEmpty());


        assert (disponibilidadesUsuarios.contains(new DisponibilidadesUsuario(
                List.of(
                        new Disponibilidad(DayOfWeek.FRIDAY, "12:00:00.000000+02:00", "13:00:00.000000+02:00")
                ),
                List.of(
                        new Usuario("q", "email"),
                        new Usuario("w", "email"),
                        new Usuario("e", "email")
                )
        )));

        assert (disponibilidadesUsuarios.contains(new DisponibilidadesUsuario(
                List.of(
                        new Disponibilidad(DayOfWeek.FRIDAY, "12:00:00.000000+02:00", "13:00:00.000000+02:00")
                ),
                List.of(
                        new Usuario("q", "email"),
                        new Usuario("w", "email"),
                        new Usuario("e", "email"),
                        new Usuario("r", "email")
                )
        )));


    }

    Optional<Actividad> actividad2a4Jugadores5Inscritos() {
        Actividad a = new Actividad();
        a.setMinJugadores(2);
        a.setMaxJugadores(4);
        a.setId("a");
        a.setNombre("Actividad prueba");
        a.setDescripcion("aaa");
        a.setRequierenTodos(false);

        a.setUsuariosInscritos(List.of(
                new Usuario("q", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("w", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "11:00:00.000000+02:00", "14:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("e", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "12:00:00.000000+02:00", "15:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("r", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "12:00:00.000000+02:00", "16:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("t", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "14:00:00.000000+02:00", "17:00:00.000000+02:00")
                        ), List.of())
        ));

        return Optional.of(a);
    }

    /**
     * Comprueba si el numero de jugadores es de 2 a infinitos, no rquieren todos
     */
    @Test
    void interseccion2aInfinitosJugadores4Inscritos() {

        //GIVEN
        when(actividadPersistencePort.findById(any())).thenReturn(actividad2aInfinitosJugadores5Inscritos());

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios;
        try {
            disponibilidadesUsuarios = actividadGetDisponibilidadService.getDisponibilidadActividad("a");
        } catch (ActividadNotFoundException e) {
            fail("No encontrado");
            return;
        }
        //THEN
        assert (disponibilidadesUsuarios.contains(new DisponibilidadesUsuario(
                List.of(
                        new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                ),
                List.of(
                        new Usuario("q", "email"),
                        new Usuario("w", "email")
                )
        )));

        assert (disponibilidadesUsuarios.contains(new DisponibilidadesUsuario(
                List.of(
                        new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                ),
                List.of(
                        new Usuario("w", "email"),
                        new Usuario("r", "email")
                )
        )));

        assert (disponibilidadesUsuarios.contains(new DisponibilidadesUsuario(
                List.of(
                        new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                ),
                List.of(
                        new Usuario("q", "email"),
                        new Usuario("w", "email"),
                        new Usuario("e", "email")
                )
        )));

        assert (disponibilidadesUsuarios.contains(new DisponibilidadesUsuario(
                List.of(
                        new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                ),
                List.of(
                        new Usuario("q", "email"),
                        new Usuario("w", "email"),
                        new Usuario("e", "email"),
                        new Usuario("r", "email")
                )
        )));

        assert (disponibilidadesUsuarios.contains(new DisponibilidadesUsuario(
                List.of(
                        new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                ),
                List.of(
                        new Usuario("q", "email"),
                        new Usuario("w", "email"),
                        new Usuario("e", "email"),
                        new Usuario("r", "email"),
                        new Usuario("t", "email")
                )
        )));


    }

    Optional<Actividad> actividad2aInfinitosJugadores5Inscritos() {
        Actividad a = new Actividad();
        a.setMinJugadores(2);
        a.setMaxJugadores(-1);
        a.setId("a");
        a.setNombre("Actividad prueba");
        a.setDescripcion("aaa");
        a.setRequierenTodos(false);

        a.setUsuariosInscritos(List.of(
                new Usuario("q", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("w", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("e", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("r", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("t", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of())
        ));

        return Optional.of(a);
    }

    /**
     * Comprueba si se requieren todos
     */
    @Test
    void interseccionRequierenTodos5Inscritos() {

        //GIVEN
        when(actividadPersistencePort.findById(any())).thenReturn(actividadRequierenTodos5Inscritos());

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios;
        try {
            disponibilidadesUsuarios = actividadGetDisponibilidadService.getDisponibilidadActividad("a");
        } catch (ActividadNotFoundException e) {
            fail("No encontrado");
            return;
        }
        //THEN

        assert (disponibilidadesUsuarios.size() == 1);

        assert (disponibilidadesUsuarios.contains(new DisponibilidadesUsuario(
                List.of(
                        new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                ),
                List.of(
                        new Usuario("q", "email"),
                        new Usuario("w", "email"),
                        new Usuario("e", "email"),
                        new Usuario("r", "email"),
                        new Usuario("t", "email")
                )
        )));


    }

    Optional<Actividad> actividadRequierenTodos5Inscritos() {
        Actividad a = new Actividad();
        a.setMinJugadores(2);
        a.setMaxJugadores(-1);
        a.setId("a");
        a.setNombre("Actividad prueba");
        a.setDescripcion("aaa");
        a.setRequierenTodos(true);

        a.setUsuariosInscritos(List.of(
                new Usuario("q", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("w", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("e", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("r", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("t", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of())
        ));

        return Optional.of(a);
    }

    /**
     * Comprueba si se requieren todos pero no es posible
     */
    @Test
    void interseccionRequierenTodos5InscritosImposible() {

        //GIVEN
        when(actividadPersistencePort.findById(any())).thenReturn(actividadRequierenTodos5InscritosImposible());

        //WHEN
        List<DisponibilidadesUsuario> disponibilidadesUsuarios;
        try {
            disponibilidadesUsuarios = actividadGetDisponibilidadService.getDisponibilidadActividad("a");
        } catch (ActividadNotFoundException e) {
            fail("No encontrado");
            return;
        }
        //THEN

        assert (disponibilidadesUsuarios.isEmpty());
    }

    Optional<Actividad> actividadRequierenTodos5InscritosImposible() {
        Actividad a = new Actividad();
        a.setMinJugadores(2);
        a.setMaxJugadores(-1);
        a.setId("a");
        a.setNombre("Actividad prueba");
        a.setDescripcion("aaa");
        a.setRequierenTodos(true);

        a.setUsuariosInscritos(List.of(
                new Usuario("q", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("w", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("e", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("r", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.FRIDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of()),
                new Usuario("t", "email", "null", "user1", 0, null, null,
                        List.of(
                                new Disponibilidad(DayOfWeek.TUESDAY, "10:00:00.000000+02:00", "13:00:00.000000+02:00")
                        ), List.of())
        ));

        return Optional.of(a);
    }


}