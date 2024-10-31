package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.mapper;

import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.domain.Usuario;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.entity.ActividadEntity;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("ActividadMapper")
@Mapper
public interface ActividadMapper {

    ActividadMapper INSTANCE = Mappers.getMapper(ActividadMapper.class);


    @Mapping(target = "usuariosInscritos", qualifiedByName = {"toEntityWithoutActividades"})
    ActividadEntity toEntity(Actividad actividad);


    @Mapping(target = "usuariosInscritos", qualifiedByName = {"fromEntityWithoutActividades"})
    Actividad fromEntity(ActividadEntity actividadEntity);

    @Named("fromEntityWithoutActividades")
    @Mapping(target = "actividadesInscritas", expression = "java(new ArrayList<>())")
    Usuario fromEntityWithoutActividades(UsuarioEntity usuarioEntity);

    @Named("toEntityWithoutActividades")
    @Mapping(target = "actividadesInscritas", expression = "java(new ArrayList<>())")
    UsuarioEntity toEntityWithoutActividades(Usuario usuario);

}
