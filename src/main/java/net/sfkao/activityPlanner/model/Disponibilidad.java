package net.sfkao.activityPlanner.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.OffsetTime;

@Setter
@Getter
@ToString
@Document("disponibilidad")
public class Disponibilidad {

    @NonNull
    @Id
    private String id;

    @NonNull
    private DayOfWeek dia;

    @NonNull
    private OffsetTime horaInicio;

    @NonNull
    private OffsetTime horaFinal;

}
