package net.sfkao.activityPlanner.usuario.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cache.annotation.Cacheable;

import java.time.DayOfWeek;
import java.time.OffsetTime;

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

}
