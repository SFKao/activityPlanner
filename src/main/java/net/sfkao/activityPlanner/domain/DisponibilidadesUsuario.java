package net.sfkao.activityPlanner.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class DisponibilidadesUsuario {

    private List<Disponibilidad> horas = new ArrayList<>();

    private List<Usuario> usuarios = new ArrayList<>();

}
