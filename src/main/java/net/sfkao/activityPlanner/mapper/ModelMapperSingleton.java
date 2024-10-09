package net.sfkao.activityPlanner.mapper;

import net.sfkao.activityPlanner.model.Actividad;
import net.sfkao.activityPlanner.model.elastic.ActividadElastic;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Range;

public class ModelMapperSingleton {

    private static ModelMapper modelMapper;

    private ModelMapperSingleton() {
    }



    public static ModelMapper getModelMapper() {
        if (modelMapper == null) {

            modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            TypeMap<Actividad, ActividadElastic> actividadActividadElasticTypeMap = modelMapper.createTypeMap(Actividad.class, ActividadElastic.class);

            actividadActividadElasticTypeMap.addMapping(
                    Actividad::getRange, (dest, v) -> dest.setJugadores((Range<Integer>) v)
            );

            TypeMap<ActividadElastic, Actividad> actividadElasticActividadTypeMap = modelMapper.createTypeMap(ActividadElastic.class, Actividad.class);

            actividadElasticActividadTypeMap.addMapping(
                    ActividadElastic::getJugadores, (dest, v) -> dest.setRange((Range<Integer>) v)
            );

        }
        return modelMapper;
    }

}
