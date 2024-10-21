package net.sfkao.activityPlanner.usuario.application.reindex;

import net.sfkao.activityPlanner.elastic.domain.ReindexableEntityService;
import net.sfkao.activityPlanner.usuario.domain.model.UsuarioElastic;
import net.sfkao.activityPlanner.usuario.domain.repository.UsuarioElasticRepository;
import net.sfkao.activityPlanner.usuario.domain.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioReindexEntityImpl implements ReindexableEntityService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioElasticRepository usuarioElasticRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void reindex() {
        usuarioElasticRepository.deleteAll();
        usuarioRepository.findAll().forEach(usuario -> modelMapper.map(usuario, UsuarioElastic.class));
    }

}
