package net.sfkao.activityPlanner.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Range;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;


@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("actividad")
public class Actividad {

    @NonNull
    @Id
    @EqualsAndHashCode.Include
    private String id;

    @NonNull
    @Field(type = FieldType.Keyword)
    private String nombre;

    @Field(type = FieldType.Text)
    private String descripcion;

    @Field(type = FieldType.Integer_Range)
    private Range<Integer> jugadores;

    @NonNull
    private Boolean requierenTodos = false;

    private String imageURL;

    @NonNull
    @DBRef(lazy = true)
    List<Usuario> usuariosInscritos;

    public int getMinJugadores() {
        Optional<Integer> value = jugadores.getLowerBound().getValue();
        return value.orElse(1);
    }

    public int getMaxJugadores() {
        Optional<Integer> value = jugadores.getUpperBound().getValue();
        return value.orElse(-1);
    }

}
