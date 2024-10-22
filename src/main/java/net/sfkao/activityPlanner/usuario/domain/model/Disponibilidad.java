package net.sfkao.activityPlanner.usuario.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cache.annotation.Cacheable;

import java.time.DayOfWeek;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Disponibilidad {

    private DayOfWeek dia;

    private String horaInicio;

    private String horaFinal;

    @Cacheable
    public OffsetTime getHoraInicioAsTime() {
        return OffsetTime.parse(horaInicio);
    }

    @Cacheable
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
