package net.sfkao.activityPlanner.disponibilidad.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
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
