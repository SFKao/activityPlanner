package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.mapper;

import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.domain.dto.ActividadDTO;
import net.sfkao.activityPlanner.domain.dto.UsuarioDTO;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.entity.ActividadEntity;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("UsuarioMapper")
@Mapper(
        uses = {DisponibilidadMapper.class, DayOfWeekMapper.class}
)
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "actividadesInscritas", qualifiedByName = {"fromEntityWithoutUsuarios"})
    Usuario fromEntity(UsuarioEntity usuarioEntity);

    @Mapping(target = "actividadesInscritas", qualifiedByName = {"toEntityWithoutUsuarios"})
    UsuarioEntity toEntity(Usuario usuario);


    @Named("toEntityWithoutUsuarios")
    @Mapping(target = "usuariosInscritos", expression = "java(new ArrayList<>())")
    ActividadEntity toEntityWithoutUsuarios(Actividad actividad);

    @Named("fromEntityWithoutUsuarios")
    @Mapping(target = "usuariosInscritos", expression = "java(new ArrayList<>())")
    Actividad fromEntityWithoutUsuarios(ActividadEntity actividadEntity);

    @Mapping(target = "actividadesInscritas", qualifiedByName = {"fromDTOWithoutUsuarios"})
    Usuario fromDTO(UsuarioDTO usuarioDTO);

    @Mapping(target = "actividadesInscritas", qualifiedByName = {"toDTOWithoutUsuarios"})
    UsuarioDTO toDTO(Usuario usuario);

    @Named("toDTOWithoutUsuarios")
    @Mapping(target = "usuariosInscritos", expression = "java(new ArrayList<>())")
    ActividadDTO toDTOWithoutUsuarios(Actividad actividad);

    @Named("fromDTOWithoutUsuarios")
    @Mapping(target = "usuariosInscritos", expression = "java(new ArrayList<>())")
    Actividad fromDTOWithoutUsuarios(ActividadDTO actividadEntity);

}
