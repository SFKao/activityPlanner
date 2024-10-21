package net.sfkao.activityPlanner.mapper;

import net.sfkao.activityPlanner.actividad.domain.Actividad;
import net.sfkao.activityPlanner.actividad.domain.ActividadElastic;
import net.sfkao.activityPlanner.usuario.domain.dto.DisponibilidadDTO;
import net.sfkao.activityPlanner.usuario.domain.model.Disponibilidad;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

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

        TypeMap<Disponibilidad, DisponibilidadDTO> disponibilidadToDTOTypeMap = modelMapper.createTypeMap(Disponibilidad.class, DisponibilidadDTO.class);

        disponibilidadToDTOTypeMap.addMappings(
                mapper -> mapper.using(
                                (Converter<DayOfWeek, Integer>) mappingContext -> mappingContext.getSource().getValue())
                        .map(Disponibilidad::getDia, DisponibilidadDTO::setDia)
        );

        TypeMap<DisponibilidadDTO, Disponibilidad> disponibilidadDTOTodisponibilidadTypeMap = modelMapper.createTypeMap(DisponibilidadDTO.class, Disponibilidad.class);

        disponibilidadDTOTodisponibilidadTypeMap.addMappings(mapper -> mapper.using(
                (Converter<Integer, DayOfWeek>) mappingContext -> DayOfWeek.of(mappingContext.getSource())
        ).map(DisponibilidadDTO::getDia, Disponibilidad::setDia));

        return modelMapper;
    }

}
