package net.sfkao.activityPlanner.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PermutacionDeDisponibiliadesDeUsuarioDependiendoDelNumeroDeJugadores {

    List<DisponibilidadesUsuario> disponibilidadesUsuarios = new ArrayList<>();
}
