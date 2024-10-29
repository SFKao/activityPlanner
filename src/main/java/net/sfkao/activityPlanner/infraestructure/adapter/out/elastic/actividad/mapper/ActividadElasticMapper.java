package net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.actividad.mapper;

import net.sfkao.activityPlanner.domain.Actividad;
import net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.actividad.entity.ActividadElastic;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Range;

@Mapper
public interface ActividadElasticMapper {

    ActividadElasticMapper INSTANCE = Mappers.getMapper(ActividadElasticMapper.class);

    ActividadElastic toElastic(Actividad actividad);

    Actividad fromElastic(ActividadElastic actividadElastic);

    @AfterMapping
    default void afterToElastic(Actividad actividad, @MappingTarget ActividadElastic actividadElastic) {
        actividadElastic.setJugadores(Range.of(Range.Bound.inclusive(actividad.getMinJugadores()), actividad.getMaxJugadores() != -1 ? Range.Bound.inclusive(actividad.getMaxJugadores()) : Range.Bound.unbounded()));
    }

    @AfterMapping
    default void afterFromElastic(ActividadElastic actividadElastic, @MappingTarget Actividad actividad) {
        actividad.setMinJugadores(actividadElastic.getJugadores().getLowerBound().getValue().orElse(1));
        actividad.setMaxJugadores(actividadElastic.getJugadores().getUpperBound().getValue().orElse(-1));
    }

}
