package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DisponibilidadEntity {

    private int dia;

    private String horaInicio;

    private String horaFinal;

}
