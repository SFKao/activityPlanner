package net.sfkao.activityPlanner.mapper;

import net.sfkao.activityPlanner.model.Actividad;
import net.sfkao.activityPlanner.model.elastic.ActividadElastic;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ActividadMapper {

    public Actividad fromElastic(ActividadElastic actividadElastic){
        return ModelMapperSingleton.getModelMapper().map(actividadElastic, Actividad.class);
    }
    public ActividadElastic toElastic(Actividad actividad){
        return ModelMapperSingleton.getModelMapper().map(actividad, ActividadElastic.class);
    }

}
