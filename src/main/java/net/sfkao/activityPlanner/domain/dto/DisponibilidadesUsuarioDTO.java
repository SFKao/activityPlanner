package net.sfkao.activityPlanner.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DisponibilidadesUsuarioDTO {

    private List<DisponibilidadDTO> horas = new ArrayList<>();

    private List<UsuarioDTO> usuarios = new ArrayList<>();


}
