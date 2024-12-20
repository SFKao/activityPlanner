package net.sfkao.activityPlanner.infraestructure.adapter.in.controller.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/")
public interface HomeController {

    @GetMapping(value = "/")
    String home(Model model, HttpSession session);

}
