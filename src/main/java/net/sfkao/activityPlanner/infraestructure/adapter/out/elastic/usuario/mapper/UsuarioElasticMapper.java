package net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.usuario.mapper;

import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.usuario.entity.UsuarioElastic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioElasticMapper {

    UsuarioElasticMapper INSTANCE = Mappers.getMapper(UsuarioElasticMapper.class);

    Usuario fromElastic(UsuarioElastic usuarioElastic);

    UsuarioElastic toElasitc(Usuario usuario);

}
