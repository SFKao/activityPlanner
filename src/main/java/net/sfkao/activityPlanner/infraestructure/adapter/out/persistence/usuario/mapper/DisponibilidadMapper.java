package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.mapper;

import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.dto.DisponibilidadDTO;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.entity.DisponibilidadEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DayOfWeekMapper.class)
public interface DisponibilidadMapper {

    DisponibilidadMapper INSTANCE = Mappers.getMapper(DisponibilidadMapper.class);


    @Mapping(source = "dia", target = "dia", qualifiedByName = "indexToDayOfWeek")
    Disponibilidad fromEntity(DisponibilidadEntity disponibilidadEntity);

    @Mapping(source = "dia", target = "dia", qualifiedByName = "dayOfWeekToIndex")
    DisponibilidadEntity toEntity(Disponibilidad disponibilidad);

    @Mapping(source = "dia", target = "dia", qualifiedByName = "indexToDayOfWeek")
    Disponibilidad fromDTO(DisponibilidadDTO disponibilidadDTO);

    @Mapping(source = "dia", target = "dia", qualifiedByName = "dayOfWeekToIndex")
    DisponibilidadDTO toDTO(Disponibilidad disponibilidad);

}
