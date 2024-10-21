package net.sfkao.activityPlanner.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

@Controller()
public class HomeControllerImpl implements HomeController {

    @Override
    public String home(Model model, HttpSession session) {
        model.addAttribute("mensaje", "Saludaciones desde java");
        List<String> list = Arrays.asList("Hola", "que", "tal");
        model.addAttribute("list", list);

        return "home";
    }
}
