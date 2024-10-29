package net.sfkao.activityPlanner.domain.dto;

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
public class DisponibilidadDTO {

    private int dia;

    private String horaInicio;

    private String horaFinal;

}
