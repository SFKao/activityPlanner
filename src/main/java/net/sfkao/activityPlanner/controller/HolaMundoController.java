package net.sfkao.activityPlanner.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class HolaMundoController {

    @GetMapping("/holaMundo")
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

}
