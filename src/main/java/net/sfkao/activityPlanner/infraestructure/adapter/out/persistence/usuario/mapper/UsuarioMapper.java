package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.mapper;

import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.dto.UsuarioDTO;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario fromEntity(UsuarioEntity usuarioEntity);

    UsuarioEntity toEntity(Usuario usuario);

    Usuario fromDTO(UsuarioDTO usuarioDTO);

    UsuarioDTO toDTO(Usuario usuario);

}
