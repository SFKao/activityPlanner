package net.sfkao.activityPlanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElasticService {

    @Autowired
    ActividadService actividadService;

    @Autowired
    UsuarioService usuarioService;

    public void reindex(){
        actividadService.reindex();
        usuarioService.reindex();
    }

}
