package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.mapper;

import net.sfkao.activityPlanner.domain.DisponibilidadesUsuario;
import net.sfkao.activityPlanner.domain.dto.DisponibilidadesUsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DayOfWeekMapper.class)
public interface DisponibilidadesUsuarioMapper {

    DisponibilidadesUsuarioMapper INSTANCE = Mappers.getMapper(DisponibilidadesUsuarioMapper.class);

    DisponibilidadesUsuario fromDTO(DisponibilidadesUsuarioDTO disponibilidadesUsuarioDTO);

    DisponibilidadesUsuarioDTO toDTO(DisponibilidadesUsuario disponibilidadesUsuario);


}
