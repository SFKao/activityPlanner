package net.sfkao.activityPlanner.model.elastic;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Range;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.WriteOnlyProperty;

import java.util.Optional;

@Getter
@Setter
@Document(indexName = "document", createIndex = true)
public class ActividadElastic {


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

    public int getMinJugadores() {
        Optional<Integer> value = jugadores.getLowerBound().getValue();
        return value.orElse(1);
    }

    public int getMaxJugadores() {
        Optional<Integer> value = jugadores.getUpperBound().getValue();
        return value.orElse(-1);
    }

    @WriteOnlyProperty
    public String getMaxJugadoresString() {
        int maxJugadores = getMaxJugadores();
        return (maxJugadores == -1 ? "infinitos" : maxJugadores) + (maxJugadores == 1 ? "jugador" : "jugadores");
    }

    @WriteOnlyProperty
    public String getMinJugadoresString() {
        int minJugadores = getMinJugadores();
        return minJugadores + (minJugadores == 1 ? "jugador" : "jugadores");
    }

}
