package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.DayOfWeek;

@Mapper
public interface DayOfWeekMapper {

    @Named("dayOfWeekToIndex")
    static int dayOfWeekToIndex(DayOfWeek dayOfWeek) {
        return dayOfWeek.getValue();
    }

    @Named("indexToDayOfWeek")
    static DayOfWeek indexToDayOfWeek(int index) {
        return DayOfWeek.of(index);
    }

}
