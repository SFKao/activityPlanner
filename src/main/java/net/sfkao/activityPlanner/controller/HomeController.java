package net.sfkao.activityPlanner.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller()
@RequestMapping("/")
public class HomeController {

    @GetMapping(value= "/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("mensaje", "Saludaciones desde java");
        List<String> list = Arrays.asList("Hola", "que", "tal");
        model.addAttribute("list", list);

        return "home";
    }


}
