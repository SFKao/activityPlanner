package net.sfkao.activityPlanner.usuario.infraestructure.reindex;

import net.sfkao.activityPlanner.elastic.domain.ReindexableEntityService;
import net.sfkao.activityPlanner.usuario.domain.UsuarioElastic;
import net.sfkao.activityPlanner.usuario.domain.UsuarioElasticRepository;
import net.sfkao.activityPlanner.usuario.domain.UsuarioRepository;
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
