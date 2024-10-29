package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.mapper;

import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.entity.ActividadEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActividadMapper {

    ActividadMapper INSTANCE = Mappers.getMapper(ActividadMapper.class);

    ActividadEntity toEntity(Actividad actividad);

    Actividad fromEntity(ActividadEntity actividadEntity);

}
