package net.sfkao.activityPlanner.mapper;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import net.sfkao.activityPlanner.actividad.domain.ActividadElastic;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Component;

@Component
public class MapperConfiguration {

    @Bean
    public ModelMapper getModelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<Actividad, ActividadElastic> actividadActividadElasticTypeMap = modelMapper.createTypeMap(Actividad.class, ActividadElastic.class);

        actividadActividadElasticTypeMap.addMapping(
                Actividad::getJugadores, (dest, v) -> dest.setJugadores((Range<Integer>) v)
        );

        TypeMap<ActividadElastic, Actividad> actividadElasticActividadTypeMap = modelMapper.createTypeMap(ActividadElastic.class, Actividad.class);

        actividadElasticActividadTypeMap.addMapping(
                ActividadElastic::getJugadores, (dest, v) -> dest.setJugadores((Range<Integer>) v)
        );
        
        return modelMapper;
    }

}
