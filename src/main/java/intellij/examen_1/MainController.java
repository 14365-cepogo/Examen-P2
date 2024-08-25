package intellij.examen_1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String showPrincipal() {
        return "index";
    }

    @RequestMapping("/home")
    public String showHome(Model model) {
        String cadena = "home";
        model.addAttribute("vista", cadena);
        return "index";
    }

}
