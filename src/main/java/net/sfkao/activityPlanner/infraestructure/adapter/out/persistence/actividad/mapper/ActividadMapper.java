package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.mapper;

import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.entity.ActividadEntity;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.mapper.UsuarioMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("ActividadMapper")
@Mapper(uses = {UsuarioMapper.class})
public interface ActividadMapper {

    ActividadMapper INSTANCE = Mappers.getMapper(ActividadMapper.class);


    @Mapping(target = "usuariosInscritos", qualifiedByName = {"toEntityWithoutActividades"})
    ActividadEntity toEntity(Actividad actividad);


    @Mapping(target = "usuariosInscritos", qualifiedByName = {"fromEntityWithoutActividades"})
    Actividad fromEntity(ActividadEntity actividadEntity);

    @Named("toEntityWithoutUsuarios")
    @Mapping(target = "usuariosInscritos", expression = "java(new ArrayList<>())")
    ActividadEntity toEntityWithoutUsuarios(Actividad actividad);

    @Named("fromEntityWithoutUsuarios")
    @Mapping(target = "usuariosInscritos", expression = "java(new ArrayList<>())")
    Actividad fromEntityWithoutUsuarios(ActividadEntity actividadEntity);

}
