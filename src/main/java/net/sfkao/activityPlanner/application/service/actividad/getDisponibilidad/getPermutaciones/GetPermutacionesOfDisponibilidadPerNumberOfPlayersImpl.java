package net.sfkao.activityPlanner.application.service.actividad.getDisponibilidad.getPermutaciones;

import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores;
import org.paukov.combinatorics3.Generator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetPermutacionesOfDisponibilidadPerNumberOfPlayersImpl implements GetPermutacionesOfDisponibilidadPerNumberOfPlayers {
    @Override
    public List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> getCombinacionesOfDisponibilidadesPerNumberOfPlayers(List<DisponibilidadesUsuario> disponibilidadesUsuarios, int minJugadores, int maxJugadores) {
        if (disponibilidadesUsuarios.size() < minJugadores) {
            return List.of();
        }

        List<PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores> combinacionesOfDisponibilidadesPerNumberOfPlayers = new ArrayList<>();

        for (int numJugadores = minJugadores; numJugadores <= maxJugadores; numJugadores++) {
            List<List<DisponibilidadesUsuario>> permutacionesPorElNumeroDeUsuarios = Generator.combination(disponibilidadesUsuarios)
                    .simple(numJugadores)
                    .stream().toList();
            permutacionesPorElNumeroDeUsuarios.forEach(p -> {
                PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores
                        permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores
                        = new PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores(p);
                combinacionesOfDisponibilidadesPerNumberOfPlayers.add(permutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores);
            });
        }


        return combinacionesOfDisponibilidadesPerNumberOfPlayers;
    }
}
