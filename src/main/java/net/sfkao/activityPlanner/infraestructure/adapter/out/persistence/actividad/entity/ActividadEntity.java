package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.entity.UsuarioEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("actividad")
public class ActividadEntity {

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
    @DocumentReference(lazy = true, lookup = """
                usuario.actividadesInscritas: {
                    $in: [new ObjectId($#{#id})]
                  }
                }
            """)
    @ReadOnlyProperty
    @JsonBackReference
    List<UsuarioEntity> usuariosInscritos = new ArrayList<>();


    public ActividadEntity(@NonNull String nombre, String descripcion, int minJugadores, int maxJugadores, @NonNull Boolean requierenTodos, String imageURL) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.minJugadores = minJugadores;
        this.maxJugadores = maxJugadores;
        this.requierenTodos = requierenTodos;
        this.imageURL = imageURL;
    }
}
