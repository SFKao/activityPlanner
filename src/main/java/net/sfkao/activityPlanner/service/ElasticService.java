package net.sfkao.activityPlanner.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Log4j2
@Service
public class ElasticService {

    @Autowired
    ActividadService actividadService;

    @Autowired
    UsuarioService usuarioService;

    public void reindex(){
        log.info("Starting reindex");
        Date start = new Date();
        log.info("Reindexando actividad");
        actividadService.reindex();
        log.info("Reindexando usuarios");
        usuarioService.reindex();
        log.info("Termiando reindex en {} ms", (new Date().getTime() - start.getTime()));
    }

}
