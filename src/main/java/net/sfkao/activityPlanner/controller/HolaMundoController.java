package net.sfkao.activityPlanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoController {

    @GetMapping("/holaMundo")
    public String holaMundo(){
        return "Saludaciones";
    }

}
