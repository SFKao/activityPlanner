package net.sfkao.activityPlanner.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Disponibilidad implements Serializable {
    @Serial
    private static final long serialVersionUID = -4982349419449606467L;

    private DayOfWeek dia;

    private String horaInicio;

    private String horaFinal;

    public OffsetTime getHoraInicioAsTime() {
        return OffsetTime.parse(horaInicio);
    }

    public OffsetTime getHoraFinalAsTime() {
        return OffsetTime.parse(horaFinal);
    }

    public void setHoraInicioAsTime(OffsetTime time) {
        horaInicio = time.format(DateTimeFormatter.ISO_OFFSET_TIME);
    }

    public void setHoraFinalAsTime(OffsetTime time) {
        horaFinal = time.format(DateTimeFormatter.ISO_OFFSET_TIME);
    }

}
