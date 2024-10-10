package net.sfkao.activityPlanner.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Range;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("actividad")
public class Actividad {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @NonNull
    @Field(type = FieldType.Keyword)
    private String nombre;

    @Field(type = FieldType.Text)
    private String descripcion;

    private int minJugadores;

    private int maxJugadores;

    @NonNull
    private Boolean requierenTodos = false;

    private String imageURL;

    @NonNull
    @DBRef(lazy = true)
    List<Usuario> usuariosInscritos = new ArrayList<>();


    public Actividad(@NonNull String nombre, String descripcion, int minJugadores, int maxJugadores, @NonNull Boolean requierenTodos, String imageURL) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.minJugadores = minJugadores;
        this.maxJugadores = maxJugadores;
        this.requierenTodos = requierenTodos;
        this.imageURL = imageURL;
    }

    public Range<Integer> getJugadores(){
        return Range.of(Range.Bound.inclusive(minJugadores), Range.Bound.inclusive(maxJugadores));
    }

    public void setJugadores(Range<Integer> range){
        this.minJugadores = range.getLowerBound().getValue().orElse(1);
        this.maxJugadores = range.getUpperBound().getValue().orElse(-1);
    }
}
