package net.sfkao.activityPlanner.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ActividadDTO {

    private String id;

    @NonNull
    private String nombre;

    private String descripcion;

    private int minJugadores;

    private int maxJugadores;

    @NonNull
    private Boolean requierenTodos = false;

    private String imageURL;

    List<UsuarioDTO> usuariosInscritos = new ArrayList<>();

}
