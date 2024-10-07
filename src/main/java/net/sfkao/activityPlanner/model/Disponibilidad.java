package net.sfkao.activityPlanner.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.OffsetTime;

@Setter
@Getter
@org.springframework.data.elasticsearch.annotations.Document(indexName = "disponibilidad")
@Document("disponibilidad")
public class Disponibilidad {

    @NonNull
    private DayOfWeek dia;

    @NonNull
    private OffsetTime horaInicio;

    @NonNull
    private OffsetTime horaFinal;

}
