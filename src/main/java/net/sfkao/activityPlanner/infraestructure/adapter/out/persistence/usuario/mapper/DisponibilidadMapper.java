package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.mapper;

import net.sfkao.activityPlanner.domain.Disponibilidad;
import net.sfkao.activityPlanner.domain.dto.DisponibilidadDTO;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.entity.DisponibilidadEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DisponibilidadMapper {

    DisponibilidadMapper INSTANCE = Mappers.getMapper(DisponibilidadMapper.class);

    Disponibilidad fromEntity(DisponibilidadEntity disponibilidadEntity);

    DisponibilidadEntity toEntity(Disponibilidad disponibilidad);

    Disponibilidad fromDTO(DisponibilidadDTO disponibilidadDTO);

    DisponibilidadDTO toDTO(Disponibilidad disponibilidad);

}
