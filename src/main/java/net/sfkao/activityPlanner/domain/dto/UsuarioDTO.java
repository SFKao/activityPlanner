package net.sfkao.activityPlanner.domain.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UsuarioDTO {

    private String email;
    private String pass;
    private String username;
    private Integer priority = 0;

    @NonNull
    private List<DisponibilidadDTO> horasDisponibles = new ArrayList<>();

    private List<ActividadDTO> actividadesInscritas = new ArrayList<>();

}
