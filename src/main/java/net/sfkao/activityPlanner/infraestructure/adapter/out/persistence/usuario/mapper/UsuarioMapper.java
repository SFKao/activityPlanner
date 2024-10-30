package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.mapper;

import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.dto.UsuarioDTO;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.mapper.ActividadMapper;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("UsuarioMapper")
@Mapper(
        uses = {DisponibilidadMapper.class, DayOfWeekMapper.class, ActividadMapper.class}
)
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "actividadesInscritas", qualifiedByName = {"fromEntityWithoutUsuarios"})
    Usuario fromEntity(UsuarioEntity usuarioEntity);

    @Mapping(target = "actividadesInscritas", qualifiedByName = {"toEntityWithoutUsuarios"})
    UsuarioEntity toEntity(Usuario usuario);

    @Named("fromEntityWithoutActividades")
    @Mapping(target = "actividadesInscritas", expression = "java(new ArrayList<>())")
    Usuario fromEntityWithoutActividades(UsuarioEntity usuarioEntity);

    @Named("toEntityWithoutActividades")
    @Mapping(target = "actividadesInscritas", expression = "java(new ArrayList<>())")
    UsuarioEntity toEntityWithoutActividades(Usuario usuario);

    Usuario fromDTO(UsuarioDTO usuarioDTO);

    UsuarioDTO toDTO(Usuario usuario);

}
