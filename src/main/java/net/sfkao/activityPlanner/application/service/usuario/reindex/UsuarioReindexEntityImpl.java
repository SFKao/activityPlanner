package net.sfkao.activityPlanner.application.service.usuario.reindex;

import net.sfkao.activityPlanner.application.port.out.usuario.UsuarioPersistencePort;
import net.sfkao.activityPlanner.infraestructure.adapter.out.elastic.SearcheableEntityService;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioReindexEntityImpl implements SearcheableEntityService {

    @Autowired
    UsuarioPersistencePort usuarioPersistencePort;

    @Override
    public void reindex() {
        usuarioPersistencePort.reindex();
    }

}
