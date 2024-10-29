package net.sfkao.activityPlanner.domain;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Actividad {

    @EqualsAndHashCode.Include
    private String id;

    @NonNull
    private String nombre;

    private String descripcion;

    private int minJugadores;

    private int maxJugadores;

    @NonNull
    private Boolean requierenTodos = false;

    private String imageURL;

    @NonNull
    List<Usuario> usuariosInscritos = new ArrayList<>();


    public Actividad(@NonNull String nombre, String descripcion, int minJugadores, int maxJugadores, @NonNull Boolean requierenTodos, String imageURL) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.minJugadores = minJugadores;
        this.maxJugadores = maxJugadores;
        this.requierenTodos = requierenTodos;
        this.imageURL = imageURL;
    }
}
