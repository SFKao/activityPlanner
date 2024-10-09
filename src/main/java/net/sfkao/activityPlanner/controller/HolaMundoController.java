package net.sfkao.activityPlanner.controller;

import net.sfkao.activityPlanner.model.Usuario;
import net.sfkao.activityPlanner.repository.mongodb.ActividadRepository;
import net.sfkao.activityPlanner.repository.mongodb.DisponibilidadRepository;
import net.sfkao.activityPlanner.repository.mongodb.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class HolaMundoController {

    @Autowired
    UsuarioRepository usuarioRepository;


    @GetMapping(value= "/holaMundo")
    public String holaMundo() {
        return "Saludaciones";
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getJSON() {
        List<String> list = Arrays.asList("1", "2", "3");
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/jsonretorno", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getJSONRetorno() {
        Map<String, String> response = new HashMap<>();
        response.put("saludo", "saludos");
        response.put("despedida", "adiosito");
        return ResponseEntity.of(Optional.of(response));
    }

    @PostMapping(value= "/usuario")
    public ResponseEntity<?> saveUsuario(@RequestBody @NonNull Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioOptional.isPresent())
            return ResponseEntity.status(302).body("Ya existe un usuario con ese email");

        Usuario saved = usuarioRepository.save(usuario);
        return ResponseEntity.ok(saved);

    }


}
